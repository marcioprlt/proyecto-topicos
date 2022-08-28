package com.example.payroll.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payroll.enums.Roles;
import com.example.payroll.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByRole(Roles role);
	Employee save(Employee employee);
}
