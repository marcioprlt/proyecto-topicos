package com.example.payroll.misc;

import java.time.Duration;
import java.util.Date;

public class DateCalculations {

	public static boolean withinRange(Date date, int a, int b) {
		
		Date now = new Date();
		Duration d = Duration.between(date.toInstant(), now.toInstant());
		Long days = d.toDays();
		return (days >= a*365 && days <= b*365) ? true : false;
	}
	
	public static boolean olderThan(Date date, int year) {
		Date now = new Date();
		Duration d = Duration.between(date.toInstant(), now.toInstant());
		Long days = d.toDays();
		return (days >= year*365) ? true : false;
	}
}
