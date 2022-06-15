package it.uniroma3.siw.catering.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Transactional
	public Ingrediente save(Ingrediente ingrediente) {
		Ingrediente savedIngrediente = ingredienteRepository.save(ingrediente);
		return savedIngrediente;
	}
	
	public Ingrediente findById(Long ingredienteId) {
		return ingredienteRepository.findById(ingredienteId).get();
	}
	
	public void deleteById(Long id) {
		ingredienteRepository.deleteById(id);
	}
}
