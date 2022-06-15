package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	private ChefRepository chefRepository;
	
	public void save(Chef chef) {
		chefRepository.save(chef);
	}
	
	public Chef findById(Long chefId) {
		return chefRepository.findById(chefId).get();
	}
	
	public Collection<Chef> findAll(){
		
		Collection<Chef> result = new ArrayList<>();
		chefRepository.findAll().forEach(result::add);
		
		return result;
	}
	
	public void deleteById(Long id) {
		chefRepository.deleteById(id);
	}
}
