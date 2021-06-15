package it.uniroma3.siw.spring.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.ArtistaRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository; 
	
	@Transactional
	public Artista inserisci(Artista artista) {
		return artistaRepository.save(artista);
	}
	
	
	
	@Transactional
	public Artista saveArtistaToDB(MultipartFile file,String nome, String cognome, String dataDiNascita, String dataDiMorte,
			                   String luogoDiNascita,String luogoDiMorte, String nazionalita){
	
		Artista a = new Artista();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
			System.out.println("not a a valid file");
		}

		try {
			a.setFoto(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}


		a.setNome(nome);
		a.setCognome(cognome);
		a.setDataDiNascita(dataDiNascita);
		a.setDataDiMorte(dataDiMorte);
		a.setLuogoDiNascita(luogoDiNascita);
		a.setLuogoDiMorte(luogoDiMorte);
		a.setNazionalita(nazionalita);
		
		this.inserisci(a);
		return a;
	}
	
	@Transactional
	public List<Artista> artistaPerNomeECognome (String nome, String cognome) {
		return artistaRepository.findByNomeAndCognome(nome, cognome);
	}

	@Transactional
	public List<Artista> tutti() {
		return (List<Artista>) artistaRepository.findAll();
	}

	@Transactional
	public Artista artistaPerId(Long id) {
		Optional<Artista> optional = artistaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Artista artista) {
		List<Artista> studenti = this.artistaRepository.findByNomeAndCognome(artista.getNome(), artista.getCognome());
		if (studenti.size() > 0)
			return true;
		else 
			return false;
	}

	public List<Artista> findAll() {
		return this.artistaRepository.findAll();
	}
}
