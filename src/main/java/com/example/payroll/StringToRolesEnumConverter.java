package com.example.payroll;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRolesEnumConverter implements Converter<String, Roles> {

	@Override
	public Roles convert(String source) {
		try {
			return source.isEmpty() ? null : Roles.valueOf(source.trim().toUpperCase());
		} catch (Exception e) {
			throw new Error("ugh");
		}
	}
}
