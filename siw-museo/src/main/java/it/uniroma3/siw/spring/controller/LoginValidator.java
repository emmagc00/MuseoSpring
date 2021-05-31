package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Curatore;

@Component
public class LoginValidator implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matricola", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Curatore.class.equals(aClass);
	}
	


}