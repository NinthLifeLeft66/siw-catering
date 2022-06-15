package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Transactional
	public void save(Buffet buffet) {
		buffetRepository.save(buffet);
	}
	
	public Buffet findById(Long buffetId) {
		return buffetRepository.findById(buffetId).get();
	}
	
	public Collection<Buffet> findAll() {
		
		Collection<Buffet> result = new ArrayList<>();
		
		buffetRepository.findAll().forEach(result::add);
		
		return result;
	}
	
	public void deleteById(Long id) {
		buffetRepository.deleteById(id);
	}
	
}
