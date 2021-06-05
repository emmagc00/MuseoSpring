package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=true)
public @Data class Artista extends Persona{
	
	private LocalDate dataDiMorte;
	private String luogoDiMorte;
	private String nazionalita;
	@Lob
	private String foto;
	
	/*ASSOCIAZIONI*/
	@OneToMany(mappedBy = "autore", cascade=CascadeType.REMOVE)
	@OrderBy("anno desc") //dall'opera pi� recente
	private List<Opera> opereCreate;
	
	/*COSTRUTTORI*/
	public Artista() {
		this.opereCreate = new ArrayList<Opera>();
		
	}

}
