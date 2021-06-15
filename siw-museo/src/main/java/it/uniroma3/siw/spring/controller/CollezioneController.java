package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class CollezioneController {

	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private CollezioneValidator collezioneValidator;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/addCollezione", method = RequestMethod.GET)
	public String addOpera(Model model) {
		logger.debug("addCollezione");
		return "admin/inserimentoCollezione.html";
	}

	@RequestMapping(value = "/addCollezione", method = RequestMethod.POST)
	public String saveOpera(@ModelAttribute("collezione") Collezione c,
			@RequestParam("file") MultipartFile file,
			@RequestParam("nome") String nome,
			@RequestParam("descrizione") String descrizione,
			@RequestParam("nomeCuratore") String nomeCuratore,
			@RequestParam("cognomeCuratore") String cognomeCuratore,
			Model model, BindingResult bindingResult)
	{
		c.setNome(nome);
		c.setDescrizione(descrizione);
		c.setFoto(file.toString());
		
		this.collezioneValidator.validate(c, bindingResult);
		if (!bindingResult.hasErrors()) { 
			this.collezioneService.saveCollezioneToDB(file, nome, descrizione, nomeCuratore, cognomeCuratore);
			return "admin/HomeLogin.html";
		}
		return "admin/inserimentoCollezione.html";
		
		
	}
	
	@RequestMapping(value="/removeCollezione", method = RequestMethod.GET)
	public String removeCollezione(Model model) {
		logger.debug("removeCollezione");
		return "admin/cancellazioneCollezione.html";
	}
	
	@RequestMapping(value = "/removeCollezione", method = RequestMethod.POST)
	public String removeCollezione(@RequestParam("nome") String nome, Model model)
	{
		Collezione c;
		try {
			c = this.collezioneService.collezionePerNome(nome).get(0);
			this.collezioneService.rimuovi(c);
			logger.debug("collezione rimossa dal DB");
		} catch (Exception e) {
			
		}
		return "admin/HomeLogin.html";
	}
	
	@RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("id") Long id, Model model) {

        model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
        model.addAttribute("opere", this.operaService.operePerIdCollezione(id));
        return "PaginaCollezione";

    }
	
}
