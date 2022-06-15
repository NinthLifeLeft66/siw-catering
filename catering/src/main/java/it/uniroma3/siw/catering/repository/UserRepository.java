package it.uniroma3.siw.catering.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.User;


public interface UserRepository extends CrudRepository<User, String> {
	
	public Optional<User> findByoAuthUniqueIdentifier(String oAuthUniqueIdentifier);

}
