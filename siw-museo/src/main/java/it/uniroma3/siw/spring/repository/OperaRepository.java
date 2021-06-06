package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

public interface OperaRepository extends CrudRepository<Opera, Long> {

	public List<Opera> findByTitolo(String titolo);

	public Optional<Opera> findById(Long id);
	
	public List<Opera> findAll();

	public List<Opera> findByCollezione(Collezione collezione);

	public List<Opera> findByAutore(Artista artista);
	
	public List<Opera> findByTitoloAndAnno(String titolo, String anno);

}