package br.com.api.validations.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.api.domain.Planet;
import br.com.api.dto.PlanetDto;
import br.com.api.exceptions.FieldMessage;
import br.com.api.repositories.PlanetsRepository;
import br.com.api.validations.CustomValidationPlanetDto;

public class CustomValidationPlanetDtoValidator implements ConstraintValidator<CustomValidationPlanetDto, PlanetDto> {

	private static final String VALIDATION_ERROR_MESSAGE = "O planeta já existe.";
	private static final String FIELD = "name";
	
	@Autowired
	private PlanetsRepository planetsRepository;
	
	/**
	 * Valida unicidade do planeta pelo nome.
	 */
	@Override
	public boolean isValid(final PlanetDto dto, final ConstraintValidatorContext context) {
		List<FieldMessage> errorList = new ArrayList<>();
		
		Optional<Planet> planet = planetsRepository.findByName(dto.getName());
		
		if (planet.isPresent()) {
			errorList.add(new FieldMessage(FIELD, VALIDATION_ERROR_MESSAGE));
		}
		
		errorList
			.stream()
			.forEach(error -> {
				context
					.disableDefaultConstraintViolation();
				context
					.buildConstraintViolationWithTemplate(error.getMessage())
					.addPropertyNode(error.getFieldName())
					.addConstraintViolation();
		});
		
		return errorList.isEmpty();
	}

}
