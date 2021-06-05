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

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.repository.CollezioneRepository;
import it.uniroma3.siw.spring.repository.CuratoreRepository;

@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Autowired
	private CuratoreRepository curatoreRepository;

	public List<Collezione> findAll() {
		
		return (List<Collezione>) this.collezioneRepository.findAll();
	}
	
	@Transactional
	public Collezione inserisci(Collezione collezione) {
		return collezioneRepository.save(collezione);
	}

	@Transactional
	public Collezione saveCollezioneToDB(MultipartFile file, String nome, String descrizione, String nomeCuratore,
			String cognomeCuratore) {
		Collezione c = new Collezione();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
			System.out.println("not a a valid file");
		}

		try {
			c.setFoto(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Curatore cu;
		
		try {
			cu = this.curatoreRepository.findByNomeAndCognome(nomeCuratore,cognomeCuratore).get(0);
		}
		catch (Exception e) {
			cu = null;
		}
		c.setNome(nome);
		c.setDescrizione(descrizione);
		c.setCuratore(cu);
		
		this.inserisci(c);
		return c;
	}

	@Transactional
	public Collezione collezionePerId(Long id) {
		 
		Optional<Collezione> optional = collezioneRepository.findById(id);
	        if (optional.isPresent())
	            return optional.get();
	        else 
	            return null;
	}

}
