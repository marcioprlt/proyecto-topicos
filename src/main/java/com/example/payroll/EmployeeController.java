package com.example.payroll;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	  
	  /*@GetMapping("/employees/{id}")
	  Employee one(@PathVariable Long id) {
	    
	    return repository.findById(id)
	      .orElseThrow(() -> new EmployeeNotFoundException(id));
	  }*/
	  
	  @GetMapping("/get/{role}")
	  List<Employee> byRole(@PathVariable("role") Roles role) {
	    
	    return repository.findByRole(role);
	  }
	  
	  @GetMapping("/getall/{span}")
	  List<Employee> byYear(@PathVariable("span") String span) {
		  List<Employee> list = repository.findAll();
		  List<Employee> newList = new ArrayList<Employee>();
		  String[] years = span.split("-");
		  int a = Integer.parseInt(years[0]);
		  int b = Integer.parseInt(years[1]);
		  
		  for (Employee e : list) {
			 
			  Date now = new Date();
			  Duration d = Duration.between(e.getStartDate().toInstant(), now.toInstant());
			  Long days = d.toDays();
			  
			  if (days > a*365 && days < b*365) {
				  newList.add(e);
			  }
		  }
		  
		  return newList;
	  }
	  
	  /*@PostMapping("/post")
	  String aumento(@RequestBody Map<String, String> raise) {
		  //List<Employee> list = repository.findByRole(raise.role);
		  for (Employee e : list) {
			  e.setSalary((int)(e.getSalary() * raise.raise));
		  }
		  return "Hello " + raise.get("raise");
	  }*/
	  
}
