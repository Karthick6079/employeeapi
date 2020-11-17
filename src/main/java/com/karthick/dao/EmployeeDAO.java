package com.karthick.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.karthick.entity.Employee;

@Repository("employeeDAO")
public class EmployeeDAO {

	
	
	@Value("${DBconnection.url}")
	private String url;

	@Value("${DBconnection.userName}")
	private String userName;

	@Value("${DBconnection.password}")
	private String password;

	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public boolean addEmployee(Employee emp) {
		Connection connection = null;
		boolean isEmpAdded = false;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO employees(employee_id,first_name,last_name,email,phone_number,date_of_joining,job_id,salary,manager_id,department_id) "
							+ "VALUES (NEXTVAL('employee_id_seq'),?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, emp.getFirstName());
			preparedStatement.setString(2, emp.getLastName());
			preparedStatement.setString(3, emp.getEmail());
			preparedStatement.setString(4, emp.getPhoneNumber());
			preparedStatement.setDate(5, new Date(emp.getDataOfJoining().getTime()));
			preparedStatement.setInt(6, emp.getJobId());
			preparedStatement.setLong(7, emp.getSalary());
			preparedStatement.setInt(8, emp.getManagerId());
			preparedStatement.setInt(9, emp.getDepartmentId());
			preparedStatement.executeUpdate();
			isEmpAdded = true;
		} catch (Exception e) {
			isEmpAdded = false;
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return isEmpAdded;
	}

	
	public List<Employee> fetchAll() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		List<Employee> empList = null;
		try {
			query = "SELECT \"employee_id\",\"first_name\",\"last_name\",\"email\",\"phone_number\",\"job_id\",\"salary\",\"manager_id\",\"department_id\",\"date_of_joining\" FROM EMPLOYEES";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			empList = formEmployee(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingResources(connection, ps, rs);
		}
		return empList;
	}

	
	
	public Employee getEmployee(int empId) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		List<Employee> empList = null;
		Employee emp = null;
		try {
			query = "SELECT \"employee_id\",\"first_name\",\"last_name\",\"email\",\"phone_number\",\"job_id\",\"salary\",\"manager_id\",\"department_id\",\"date_of_joining\" FROM EMPLOYEES where employee_id = ?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			empList = formEmployee(rs);
			emp = empList.size() > 0 ? empList.get(0): null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingResources(connection, ps, rs);
		}
		return emp;
	}

	public List<Employee> formEmployee(ResultSet rs) {
		Employee emp = null;
		List<Employee> empList = null;
		try {
			empList = new LinkedList<Employee>();
			while (rs.next()) {
				emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setJobId(rs.getInt("job_id"));
				emp.setSalary((long) (rs.getDouble("salary")));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDataOfJoining(rs.getDate("date_of_joining"));
				emp.setDepartmentId(rs.getInt("department_id"));
				empList.add(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;

	}

	private void closingResources(Connection connection, PreparedStatement ps, ResultSet rs) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
