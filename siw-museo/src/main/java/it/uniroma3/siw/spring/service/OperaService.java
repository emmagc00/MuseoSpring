package it.uniroma3.siw.spring.service;

import java.io.IOException;
import java.util.Base64;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {

	@Autowired
	private OperaRepository operaRepository; 
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}
	
	@Transactional
	public Opera saveOperaToDB(MultipartFile file,String titolo,
	String descrizione, String anno){
	Opera o = new Opera();
	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	if(fileName.contains(".."))
	{
	System.out.println("not a a valid file");
	}
	try {
	o.setFoto(Base64.getEncoder().encodeToString(file.getBytes()));
	} catch (IOException e) {
	e.printStackTrace();
	}
	o.setTitolo(titolo);
	o.setAnno(anno);
	o.setDescrizione(descrizione);
	//o.setArtista(art);
	//o.setCollezione(col);
	this.inserisci(o);
	return o;
	}
}
