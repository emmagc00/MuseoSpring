package it.uniroma3.siw.spring.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@MappedSuperclass
@NoArgsConstructor
public @Data abstract class Persona {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NonNull
	private String nome;
	@NonNull
	private String cognome;

	private String dataDiNascita;

	private String luogoDiNascita;

	

}
