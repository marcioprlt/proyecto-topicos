package com.example.payroll.controller;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.payroll.enums.Roles;
import com.example.payroll.misc.DateCalculations;
import com.example.payroll.misc.StringToRolesEnumConverter;
import com.example.payroll.models.Employee;
import com.example.payroll.models.Raise;
import com.example.payroll.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	private final EmployeeRepository repository;

	  EmployeeController(EmployeeRepository repository) {
	    this.repository = repository;
	  }
	  
	  @GetMapping("/employees")
	  List<Employee> all() {
	    return repository.findAll();
	  }
	  
	  @GetMapping("/employees/byrole/{role}")
	  List<Employee> byRole(@PathVariable("role") Roles role) {
	    
	    return repository.findByRole(role);
	  }
	  
	  @GetMapping("/employees/byspan/{span}")
	  List<Employee> bySpan(@PathVariable("span") String span) {
		  
		  //check if span passed in path has the correct format
		  boolean matches = Pattern.compile("^\\d+-\\d+$").matcher(span).find();
		  if (!matches) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Year span format incorrect, use n-n format");
		  
		  
		  List<Employee> list = repository.findAll();
		  List<Employee> newList = new ArrayList<Employee>();
		  String[] years = span.split("-");
		  int a = Integer.parseInt(years[0]);
		  int b = Integer.parseInt(years[1]);
		  
		  for (Employee e : list) {
			  
			  if (DateCalculations.withinRange(e.getStartDate(), a, b))
				  newList.add(e);
		  }
		  
		  return newList;
	  }
	  
	  @PostMapping(value = "/employees/post", consumes = "application/json")
	  String aumento(@RequestBody Raise raise) {
		  //deal with role
		  var enumConverter = new StringToRolesEnumConverter();
		  Roles role = enumConverter.convert(raise.getRole());
		  
		  //deal with raise
		  if (raise.getRaise() < 0.1f || raise.getRaise() > 50)
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Raise value must be between 0.1 and 50");
		  
		  //deal with time
		  if (raise.getYears() < 0)
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time value must be positive");
		  
		  //process
		  List<Employee> list = repository.findByRole(role);
		  String response = "";
		  
		  for (Employee e : list) {
			  
			  if (!DateCalculations.olderThan(e.getStartDate(), raise.getYears())) continue;
				  
			  float newSalary = e.getSalary() * (1 + raise.getRaise() / 100f);
			  e.setSalary((int)newSalary);
			  
			  repository.save(e);
			  response += e.getName() + ", ";
		  }

		  return (response.isEmpty()) ? "None updated" : (response + "updated");
	  }
	  
}
