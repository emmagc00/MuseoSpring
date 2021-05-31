package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//import it.uniroma3.siw.spring.model.Artista;
//import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class OperaController {

	@Autowired
	private OperaService operaService;

	@Autowired
	private OperaValidator operaValidator;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/addOpera", method = RequestMethod.GET)
	public String addPersona(Model model) {
		logger.debug("addOpera");
		model.addAttribute("opera", new Opera());
		return "inserimentoOpera.html";
	}

	@RequestMapping(value = "/addOpera", method = RequestMethod.POST)
	public String saveOpera(@RequestParam("file") MultipartFile file,
			@RequestParam("titolo") String titolo,
			@RequestParam("descrizione") String descrizione,
			@RequestParam("anno") String anno,
			//@RequestParam("artista") Artista autore,
			//@RequestParam("collezione") Collezione collezione,
			Model model)
	{
		Opera o = this.operaService.saveOperaToDB(file, titolo, descrizione, anno);
		//model.addAttribute("opera", o);
		return "HomeLogin.html";
	}
}

