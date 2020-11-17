package com.karthick.services;

import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.karthick.EmployeesapiApplication;
import com.karthick.entity.Employee;

public class EmployeeOperations {

	/*
	 * This method used to search employee based on given input
	 * 
	 * 
	 */
	
	public Employee searchByFirstName(String firstName) {

		if (!EmployeesapiApplication.empList.isEmpty() && "".equals(firstName) && !firstName.isEmpty()) {
			Employee emp = (Employee) EmployeesapiApplication.empList.stream()
					.filter(e -> firstName.equalsIgnoreCase(e.getFirstName())).collect(Collectors.toList());
			return emp;
		}

		return null;

	}
	
	/* This method is used to sorted employee list based on their first name
	 * 
	 * 
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List<Employee> sortedByFirstName() {
		List<Employee> empList;
		if (!EmployeesapiApplication.empList.isEmpty()) {
			empList = (List<Employee>) EmployeesapiApplication.empList.stream()
					.sorted((obj1, obj2) -> obj1.getFirstName().compareTo(obj2.getFirstName()));
			return empList;
		}

		return null;

	}
	
	
	/*Find max salary employee
	 * 
	 * 
	 * 
	 * 
	 */
	
	public OptionalLong maxSalaryEmployee() {
		
		if (!EmployeesapiApplication.empList.isEmpty()) {
			
			  OptionalLong max = EmployeesapiApplication.empList.stream().mapToLong(Employee::getSalary).max(); 
			  return max;
			 
			
			//int sum = EmployeesapiApplication.empList.stream().reduce(0, (sum1,emp) -> sum1+emp.getSalary())Integer::sum1);
		}
		
		Long summingSalaries
        = EmployeesapiApplication.empList.stream().collect(Collectors.summingLong(Employee::getSalary));
		return null;
	}
	

}
