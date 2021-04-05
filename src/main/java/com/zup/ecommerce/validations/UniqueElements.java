package com.zup.ecommerce.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy=UniqueElementsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueElements {

	String message() default "Elementos da lista devem ser únicos";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
