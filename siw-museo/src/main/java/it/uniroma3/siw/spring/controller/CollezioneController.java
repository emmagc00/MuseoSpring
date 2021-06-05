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

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class CollezioneController {

	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private OperaService operaService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/addCollezione", method = RequestMethod.GET)
	public String addOpera(Model model) {
		logger.debug("addCollezione");
		return "inserimentoCollezione.html";
	}

	@RequestMapping(value = "/addCollezione", method = RequestMethod.POST)
	public String saveOpera(@RequestParam("file") MultipartFile file,
			@RequestParam("nome") String nome,
			@RequestParam("descrizione") String descrizione,
			@RequestParam("nomeCuratore") String nomeCuratore,
			@RequestParam("cognomeCuratore") String cognomeCuratore,
			Model model)
	{
		Collezione c = this.collezioneService.saveCollezioneToDB(file, nome, descrizione, nomeCuratore, cognomeCuratore);
		return "HomeLogin.html";
	}
	
	@RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("id") Long id, Model model) {

        model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
        model.addAttribute("opere", this.operaService.operePerIdCollezione(id));
        return "PaginaCollezione";

    }
	
}