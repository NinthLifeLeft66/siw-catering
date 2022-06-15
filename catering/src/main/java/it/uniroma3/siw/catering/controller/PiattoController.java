package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private BuffetService buffetService;
		
	@GetMapping("/admin/buffet/{buffetId}/piatto/new")
	public String getCreatePiattoView(@PathVariable("buffetId") Long buffetId, 
			Model model) {
		
		Buffet buffet = buffetService.findById(buffetId);
		
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("buffet", buffet);
		
		return "crea-piatto";
	}
	
	@PostMapping("/admin/buffet/{buffetId}/piatto")
	public String createPiatto(@Valid @ModelAttribute("piatto") Piatto piatto ,
			BindingResult bindingResult, 
			@PathVariable("buffetId") Long buffetId) {
		
		if(!bindingResult.hasErrors()) {
			
			Buffet buffet = buffetService.findById(buffetId);
			
			Piatto savedPiatto = piattoService.save(piatto);
			buffet.getPiatti().add(savedPiatto);
			
			buffetService.save(buffet);
			
			Chef chef = buffet.getChef();
			
			return "redirect:/chef/" + chef.getId();
		}
		
		return "crea-piatto";
	}
}
