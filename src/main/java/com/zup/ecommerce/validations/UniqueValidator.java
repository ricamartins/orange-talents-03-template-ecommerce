package com.zup.ecommerce.validations;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

	@PersistenceContext
	private EntityManager manager;
	
	private Class<?> clazz;
	private String field;
	
	@Override
	public void initialize(Unique annotation) {
		this.clazz = annotation.clazz();
		this.field = annotation.field();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		try {
			Object resultado = manager.createQuery("select e from "+clazz.getSimpleName()+" e where "+field+"=:value")
					.setParameter("value", value)
					.getSingleResult();
			return false;
		} catch (NoResultException e) {
			return true;
		}
		
	}

}
