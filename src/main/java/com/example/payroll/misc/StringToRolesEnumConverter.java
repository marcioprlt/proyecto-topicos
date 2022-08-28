package com.example.payroll.misc;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.payroll.enums.Roles;

@Component
public class StringToRolesEnumConverter implements Converter<String, Roles> {

	@Override
	public Roles convert(String source) {
		try {
			return source.isEmpty() ? null : Roles.valueOf(source.trim().toUpperCase());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role doesn't exist, try: junior, middle or senior");
		}
	}
}
