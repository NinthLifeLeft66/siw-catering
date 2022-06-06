package it.uniroma3.siw.catering.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Chef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 3, max = 30)
	private String nome;
	
	@Column(nullable = false)
	@Size(min = 3, max = 30)
	private String cognome;
	
	@Column(nullable = false)
	@Size(min = 3, max = 30)
	private String nazionalità;
}
