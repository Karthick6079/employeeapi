package com.karthick;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.karthick.entity.Employee;

@SpringBootApplication
public class EmployeesapiApplication {

	public static List<Employee> empList;
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeesapiApplication.class, args);
	}

}
