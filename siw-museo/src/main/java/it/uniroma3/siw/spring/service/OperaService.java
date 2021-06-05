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
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.ArtistaRepository;
import it.uniroma3.siw.spring.repository.CollezioneRepository;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {

	@Autowired
	private OperaRepository operaRepository; 
	
	@Autowired
	private CollezioneRepository collezioneRepository; 
	
	@Autowired
	private ArtistaRepository artistaRepository; 
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}
	
	@Transactional
	public Opera saveOperaToDB(MultipartFile file,String titolo, String descrizione, String anno, String nomeCollezione,
			                   String nomeArtista, String cognomeArtista){
	
		Opera o = new Opera();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")){
			System.out.println("not a a valid file");
		}
		
		try {
			o.setFoto(Base64.getEncoder().encodeToString(file.getBytes()));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Collezione c;
		
		try {
			c = this.collezioneRepository.findByNome(nomeCollezione).get(0);
		}
		catch (Exception e) {
			c = null;
		}
		
		Artista a;
		
		try {
			a = this.artistaRepository.findByNomeAndCognome(nomeArtista,cognomeArtista).get(0);
		}
		catch (Exception e) {
			a = null;
		}
		
		o.setTitolo(titolo);
		o.setAnno(anno);
		o.setDescrizione(descrizione);
		o.setCollezione(c);
		o.setAutore(a);
		
		this.inserisci(o);
		return o;
	}
	
	@Transactional
	public List<Opera> operePerIdCollezione(Long id){
		Optional<Collezione> c = this.collezioneRepository.findById(id);
		return (List<Opera>) this.operaRepository.findByCollezione(c.get());
	}
	
	@Transactional
	public List<Opera> operePerIdArtista(Long id){
		Optional<Artista> a = this.artistaRepository.findById(id);
		return (List<Opera>) this.operaRepository.findByAutore(a.get());
	}

	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else 
            return null;
	}
}
