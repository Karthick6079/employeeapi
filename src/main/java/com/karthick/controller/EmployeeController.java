package com.karthick.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.EmployeesapiApplication;
import com.karthick.entity.Employee;
import com.karthick.entity.Response;
import com.karthick.services.EmployeeService;
import com.karthick.util.EmployeeUtil;

@RestController
@RequestMapping("/employee/")
public class EmployeeController {
	
	
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeUtil employeeUtil;
	
	
	public EmployeeController(EmployeeUtil employeeUtil) {
		this.employeeUtil = employeeUtil;
	}


	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}





	@GetMapping("{id}/get")
	public Employee getEmployee(@PathVariable("id") Integer empId, @RequestHeader("authorization") String authorization) {
		System.out.println("Inside GetMapping");
		return employeeService.getEmployeeById(empId);
	}
	
	
	@GetMapping("fetchAll")
	public List<Employee> fetchAll(){
		List<Employee> list = null;
		if(EmployeesapiApplication.empList == null) {
			list = employeeService.getEmployess();
			EmployeesapiApplication.empList = list;
			return list;
		}else {
			list = EmployeesapiApplication.empList;
		}
		return list;
	}
	
	@PostMapping("add")
	public Response addEmployee(@RequestBody String requestPayload) {
		JSONObject jsonObject = new JSONObject(requestPayload);
		Employee employee  =(Employee) employeeUtil.convertObjectFromJSON(jsonObject.toString(), Employee.class);
		if(employeeService.addEmployee(employee))
			return new Response(200, "A new employee added.");
		return new Response(500, "Error in employee addtion.");
	}
	
	
	/*
	 * //public Employee update(int )
	 * 
	 * @PostMapping public boolean add(Employee e) {
	 * 
	 * }
	 */

}
