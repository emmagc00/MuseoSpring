package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Curatore;

@Controller
public class FragmentsController{

	@RequestMapping(value="/artisti", method=RequestMethod.GET)
	public String listaArtisti() {
		return "artisti.html";
	}
	
	@RequestMapping(value="/collezioni", method=RequestMethod.GET)
	public String listaCollezioni() {
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
	

}
