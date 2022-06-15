package it.uniroma3.siw.catering.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
	@Autowired
	private CredentialsRepository credentialsRepository;

	@Transactional
	public Credentials save(Credentials credentials) {
		return credentialsRepository.save(credentials);
	}

	public Credentials findById(Long id) {
		return credentialsRepository.findById(id).get();
	}

	public Credentials findByUsername(String username) {
		Optional<Credentials> credentials = credentialsRepository.findByUsername(username);

		if (credentials.isPresent()) {
			return credentials.get();
		}

		return null;
	}
	
}
