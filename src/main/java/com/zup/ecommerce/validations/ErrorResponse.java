package com.zup.ecommerce.validations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorResponse {

	private Map<String, List<String>> errors = new HashMap<>();
	
	public Map<String, List<String>> getErrors() {
		return errors;
	}
	
	public void addError(String field, String message) {
		List<String> fieldErrors = errors.get(field);

		if (fieldErrors == null)
			fieldErrors = new ArrayList<>(Arrays.asList(message));
		else
			fieldErrors.add(message);
		
		errors.put(field, fieldErrors);
	}
}
