package it.uniroma3.siw.catering.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Buffet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 3, max = 30)
	private String nome;
	
	@Column(nullable = false)
	@Size(min = 3, max = 30)
	private String descrizione;
	
	@ManyToOne
	private Chef chef;
	
	@ManyToMany
	private Collection<Piatto> piatti;
	
	public Buffet() {
		piatti= new ArrayList<>();
	}
}
