package com.example.payroll.misc;

public class EmployeeNotFoundException extends RuntimeException {

	EmployeeNotFoundException(Long id) {
	    super("Could not find employee " + id);
	}
}
