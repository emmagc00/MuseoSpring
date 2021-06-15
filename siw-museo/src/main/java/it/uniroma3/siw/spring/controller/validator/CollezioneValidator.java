package it.uniroma3.siw.spring.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Collezione;


@Component
public class CollezioneValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "foto", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Collezione.class.equals(aClass);
	}
}
