package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController{
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String prova() {
		return "Collezioni.html";
	}
}
