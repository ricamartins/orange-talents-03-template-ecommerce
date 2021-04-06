package com.zup.ecommerce.validations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(MultipartFile[].class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors())
			return;

		MultipartFile[] files = (MultipartFile[]) target;
		
		List<String> filenames = Stream.of(files)
				.filter(f -> f.isEmpty())
				.map(f -> f.getOriginalFilename())
				.collect(Collectors.toList());
		
		if (!filenames.isEmpty())
			errors.rejectValue("file", null, "Imagens vazias: "+filenames.toString());
	}

}
