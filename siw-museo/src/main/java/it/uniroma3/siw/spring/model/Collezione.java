package it.uniroma3.siw.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Data;
import lombok.NonNull;

@Entity
public @Data class Collezione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NonNull
	private String nome;
	private String descrizione;
	@Lob
	private String foto;
	
	/*ASSOCIAZIONI*/
	@ManyToOne
	private Curatore curatore;
	
	@OneToMany(mappedBy = "collezione")
	@OrderBy("titolo asc") //ordina in base all'attributo title di artPiece
	private List<Opera> opere;
	
	/*COSTRUTTORI*/
	public Collezione() {
		this.opere = new ArrayList<Opera>();
		
	}

}

