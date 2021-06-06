package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;

@Controller
public class FragmentsController{
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private ArtistaService artistaService;

	@RequestMapping(value="/artisti", method=RequestMethod.GET)
	public String listaArtisti(Model model) {
		model.addAttribute("artisti", this.artistaService.findAll());
		return "artisti.html";
	}
	
	@RequestMapping(value="/collezioni", method=RequestMethod.GET)
	public String listaCollezioni(Model model) {
		model.addAttribute("collezioni", this.collezioneService.findAll());
		return "Collezioni.html";
	}
	
	
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public String informazioni() {
		return "Informazioni.html";
	}
	
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
		return "index.html";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		return "index.html";
	}
	

}