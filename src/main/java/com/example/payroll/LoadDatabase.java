package com.example.payroll;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", Roles.MIDDLE, 1000, new Date())));
      
      Calendar date = Calendar.getInstance();
      
      date.set(1995, 2, 22);
      log.info("Preloading " + repository.save(new Employee("Frodo Baggins", Roles.SENIOR, 1200, date.getTime())));
      
      date.set(2019, 4, 27);
      log.info("Preloading " + repository.save(new Employee("Samwise Gamgee", Roles.JUNIOR, 700, date.getTime())));
      
      date.set(2020, 7, 17);
      log.info("Preloading " + repository.save(new Employee("Merry Brandybuck", Roles.SENIOR, 1050, date.getTime())));
      
      date.set(2021, 3, 07);
      log.info("Preloading " + repository.save(new Employee("Pippin Took", Roles.JUNIOR, 1600, date.getTime())));
      
      date.set(2022, 1, 18);
      log.info("Preloading " + repository.save(new Employee("Aragorn", Roles.MIDDLE, 2000, date.getTime())));
      
    };
  }
}