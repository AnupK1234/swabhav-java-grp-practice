package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataUtil {

	public static List<Employee> getEmp() {
		List<Employee> employees = new ArrayList<>();

		employees.add(new Employee("Shreyas", "Iyer", 4, 4500.00));

		return employees;
	}

}