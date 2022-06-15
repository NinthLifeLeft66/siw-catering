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
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class IngredienteController {
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping("/admin/buffet/{buffetId}/piatto/{piattoId}/ingrediente/new")
	public String getCreateIngredienteView(@PathVariable("buffetId") Long buffetId, 
			@PathVariable("piattoId") Long piattoId, 
			Model model) {
		
		Buffet buffet = buffetService.findById(buffetId);
		Piatto piatto = piattoService.findById(piattoId);
		
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("buffet", buffet);
		model.addAttribute("piatto", piatto);
		
		return "crea-ingrediente";
	}
	
	@PostMapping("/admin/buffet/{buffetId}/piatto/{piattoId}/ingrediente")
	public String createIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente ,
			BindingResult bindingResult, 
			@PathVariable("buffetId") Long buffetId, 
			@PathVariable("piattoId") Long piattoId) {
		
		if(!bindingResult.hasErrors()) {
			
			Buffet buffet = buffetService.findById(buffetId);
			Piatto piatto = piattoService.findById(piattoId);
			
			Ingrediente savedIngrediente = ingredienteService.save(ingrediente);
			piatto.getIngredienti().add(savedIngrediente);
			
			piattoService.save(piatto);
			
			Chef chef = buffet.getChef();
			
			return "redirect:/chef/" + chef.getId();
		}
		
		return "crea-ingrediente";
	}
	
	@GetMapping("/buffet/{buffetId}/ingrediente/{ingredienteId}")
	public String getIngredienteView(@PathVariable("buffetId") Long buffetId,
			@PathVariable("ingredienteId") Long ingredienteId, 
			Model model) {
		
		Ingrediente ingrediente = ingredienteService.findById(ingredienteId);
		Buffet buffet = buffetService.findById(buffetId);
		
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("buffet", buffet);
		
		return "ingrediente";
	}
	
	@GetMapping("/admin/ingrediente/{ingredienteId}/aggiorna")
	public String getAggiornaIngredienteView(@PathVariable("ingredienteId") Long ingredienteId,
			Model model) {
		
		model.addAttribute("ingrediente", ingredienteService.findById(ingredienteId));
		
		return "aggiorna-ingrediente";
		
	}
	
	@PostMapping("/admin/ingrediente/{ingredienteId}/aggiona")
	public String aggiornaIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, 
			BindingResult ingredienteBindingResult) {
		
		if (!ingredienteBindingResult.hasErrors()) {
			ingredienteService.save(ingrediente);
			return "redirect:/admin";
		}
		
		return "aggiorna-ingrediente";
		
	}
	
	@GetMapping("/admin/buffet/{buffetId}/piatto/{piattoId}/ingrediente/{ingredienteId}/rimuovi")
	public String deleteIngrediente(@PathVariable("ingredienteId") Long ingredienteId,
			@PathVariable("piattoId") Long piattoId, 
			@PathVariable("buffetId") Long buffetId)
	{
		Ingrediente ingrediente = ingredienteService.findById(ingredienteId);
		
		Chef chef = buffetService.findById(buffetId).getChef();
		
		Piatto piatto = piattoService.findById(piattoId);
		piatto.getIngredienti().remove(ingrediente);
		
		ingredienteService.deleteById(ingredienteId);
		
		return "redirect:/chef/" + chef.getId();
		
	}
}
