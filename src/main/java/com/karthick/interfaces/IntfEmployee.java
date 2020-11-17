package com.karthick.interfaces;

import java.util.List;

import com.karthick.entity.Employee;

public interface IntfEmployee {
	
	public boolean addEmployee(Employee employee);
	
	public Employee getEmployeeById(int employeeId);
	
	public boolean updateEmployee(Employee employee);
	
	public boolean deleteEmployee(int employeeId);
	
	public List<Employee> getEmployess();

}
