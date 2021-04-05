package com.zup.ecommerce.validations;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zup.ecommerce.controllers.dto.CaracteristicaRequest;

public class UniqueElementsValidator implements ConstraintValidator<UniqueElements, Collection<CaracteristicaRequest>> {

	@Override
	public boolean isValid(Collection<CaracteristicaRequest> collection, ConstraintValidatorContext context) {

		if (collection == null || collection.size() < 2)
			return true;
		
		Set<Object> set = new HashSet<>();
		List<Object> duplicates = collection.stream().filter(o -> !set.add(o)).collect(Collectors.toList());
		
		System.out.println("tá vazio?: "+duplicates.isEmpty());
		if (duplicates.isEmpty())
			return true;
		
		return false;
	}

}
