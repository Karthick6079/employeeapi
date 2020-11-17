package com.karthick.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.interfaces.IntfEmployee;
import com.karthick.dao.EmployeeDAO;
import com.karthick.entity.Employee;

@Service
public class EmployeeService implements IntfEmployee {

	EmployeeDAO employeeDAO;
	
	
	@Autowired
	public EmployeeService(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		return employeeDAO.addEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		return employeeDAO.getEmployee(employeeId);
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		return false;
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		return false;
	}

	@Override
	public List<Employee> getEmployess() {
		return employeeDAO.fetchAll();
	}

}
