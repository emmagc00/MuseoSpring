package it.uniroma3.siw.spring.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class ArtistaController {


	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private OperaService operaService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/addArtista", method = RequestMethod.GET)
	public String addOpera(Model model) {
		logger.debug("addArtista");
		return "inserimentoArtista.html";
	}

	@RequestMapping(value = "/addArtista", method = RequestMethod.POST)
	public String saveOpera(@RequestParam("file") MultipartFile file,
			@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,
			@RequestParam("dataDiNascita") @DateTimeFormat(iso = ISO.DATE) LocalDate dataDiNascita,
			@RequestParam("dataDiMorte") @DateTimeFormat(iso = ISO.DATE) LocalDate dataDiMorte,
			@RequestParam("luogoDiNascita") String luogoDiNascita,
			@RequestParam("luogoDiMorte") String luogoDiMorte,
			@RequestParam("nazionalita") String nazionalita,
			Model model)
	{
		Artista a = this.artistaService.saveArtistaToDB(file, nome, cognome, dataDiNascita, dataDiMorte,
                 luogoDiNascita, luogoDiMorte,  nazionalita);
		return "HomeLogin.html";
	}
	
	@RequestMapping(value="/artista/{id}", method = RequestMethod.GET)
	public String getArtista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.artistaService.artistaPerId(id));
		model.addAttribute("opere", this.operaService.operePerIdArtista(id));
		return "artista.html";
	}
}
