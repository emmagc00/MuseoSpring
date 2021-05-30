package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Opera;

@Component
public class OperaValidator implements Validator{
	
	private static final Logger logger = LoggerFactory.getLogger(OperaValidator.class);

	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "anno", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Opera.class.equals(clazz);
	}


}
