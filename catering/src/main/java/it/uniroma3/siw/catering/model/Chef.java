package it.uniroma3.siw.catering.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private String nazionalita;
	
	@OneToMany(mappedBy = "chef", cascade = CascadeType.REMOVE)
	private Collection<Buffet> buffetProposti;
	
	public Chef() {
		buffetProposti = new ArrayList<>();
	}
	
	public void addBuffet(Buffet buffet) {
		buffetProposti.add(buffet);
	}
	
	public void removeMatch(Buffet buffet) {
		buffetProposti.remove(buffet);
	}
}
