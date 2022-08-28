package com.example.payroll.models;

import java.util.Date;

//import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.payroll.enums.Roles;

@Entity
public class Employee {

  private @Id @GeneratedValue Long id;
  private String name;
  private Roles role;
  private int salary;
  private Date startDate;

  Employee() {}

  public Employee(String name, Roles role, int salary, Date startDate) {

    this.name = name;
    this.role = role;
    this.salary = salary;
    this.startDate = startDate;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Roles getRole() {
    return this.role;
  }

  public int getSalary() {
	return salary;
  }

  public Date getStartDate() {
	return startDate;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(Roles role) {
    this.role = role;
  }
  
  public void setSalary(int salary) {
	  this.salary = salary;
  }
  
  public void setStartDate(Date startDate) {
	  this.startDate = startDate;
  }
}