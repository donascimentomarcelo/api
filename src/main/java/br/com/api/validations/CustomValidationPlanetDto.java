package br.com.api.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.api.validations.validator.CustomValidationPlanetDtoValidator;

@Constraint(validatedBy = CustomValidationPlanetDtoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidationPlanetDto {

	public static final String VALIDATION_ERROR = "validation error.";

	String message() default VALIDATION_ERROR;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
