package com.aurionpro.servlet;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.Employee.Employee;

public class EmloyeeUtil {
	
	public static List<Employee> getEmp(){
		List<Employee> emps = new ArrayList<>();
		emps.add(new Employee("Nilesh", "Gawli", 2, "nileshgawli@gmail.com"));
		
		return emps;
	}

}
