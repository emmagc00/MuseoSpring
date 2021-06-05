package it.uniroma3.siw.spring.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;

@Controller
public class ArtistaController {


	@Autowired
	private ArtistaService artistaService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/addArtista", method = RequestMethod.GET)
	public String addOpera(Model model) {
		logger.debug("addArtista");
		return "InserimentoArtista.html";
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
}
