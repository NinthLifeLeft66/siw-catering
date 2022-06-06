package it.uniroma3.siw.catering.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Credentials {
	
	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(mappedBy = "credentials", cascade = CascadeType.ALL)
	private User user;
	
	@Column(nullable = false, unique = true)
	@Size(min = 3, max = 20)
	private String username;
	
	@Column(nullable = false)
	@Size(min = 8, max = 255)
	private String password;
	
	@Column(nullable = false)
	private String role;
	
	public Credentials() {
		role = DEFAULT_ROLE;
	}
	
}
