package it.uniroma3.siw.catering.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@Service
public class PiattoService {
	
	@Autowired
	private PiattoRepository piattoRepository;
	
	@Transactional
	public Piatto save(Piatto piatto) {
		Piatto savedPiatto = piattoRepository.save(piatto);
		return savedPiatto;
	}
	
	public Piatto findById(Long piattoId) {
		return piattoRepository.findById(piattoId).get();
	}
	
	public void deleteById(Long id) {
		piattoRepository.deleteById(id);
	}
}
