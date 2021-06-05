package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.OperaValidator;
import it.uniroma3.siw.spring.model.Collezione;
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
	public String addOpera(Model model) {
		logger.debug("addOpera");
		return "inserimentoOpera.html";
	}

	@RequestMapping(value = "/addOpera", method = RequestMethod.POST)
	public String saveOpera(@RequestParam("file") MultipartFile file,
			@RequestParam("titolo") String titolo,
			@RequestParam("descrizione") String descrizione,
			@RequestParam("anno") String anno,
			@RequestParam("nomeArtista") String nomeArtista,
			@RequestParam("cognomeArtista") String cognomeArtista,
			@RequestParam("nomeCollezione") String nomeCollezione,
			Model model)
	{
		Opera o = this.operaService.saveOperaToDB(file, titolo, descrizione, anno, nomeCollezione, nomeArtista, cognomeArtista);
		return "HomeLogin.html";
	}
	
	@RequestMapping(value="/opera/{id}", method = RequestMethod.GET)
	public String getOpera(@PathVariable("id") Long id, Model model) {
		model.addAttribute("opera", this.operaService.operaPerId(id));
		return "opera.html";
	}
	
	
}