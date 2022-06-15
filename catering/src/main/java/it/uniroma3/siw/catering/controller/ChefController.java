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

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/chef/{chefId}")
	public String getChef(@PathVariable("chefId") Long chefId,
			Model model) {
		
		Chef chef = chefService.findById(chefId);

		model.addAttribute("chef", chef);
		
		return "chef";
	}
	
	@GetMapping("/chef/{chefId}/profilo")
	public String getChefProfilo(@PathVariable("chefId") Long chefId,
			Model model) {
		
		Chef chef = chefService.findById(chefId);

		model.addAttribute("chef", chef);
		
		return "profilo-chef";
	}
	
	@GetMapping("/admin/chef/{chefId}/profilo")
	public String getAdminChefProfilo(@PathVariable("chefId") Long chefId,
			Model model) {
		
		Chef chef = chefService.findById(chefId);

		model.addAttribute("chef", chef);
		
		return "admin-profilo-chef";
	}
	
	@GetMapping("/admin/chef/new")
	public String getCreateChefView(Model model) {
		
		model.addAttribute("chef", new Chef());
		
		return "registra-chef";
	}
	
	@PostMapping("/admin/chef")
	public String createChef(@Valid @ModelAttribute("chef") Chef chef,
			BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			return "redirect:/admin";
		}
		
		return "registra-chef";
	}
	
	@GetMapping("/admin/chef/{chefId}/rimuovi")
	public String deleteChef(@PathVariable("chefId") Long chefId) {
		
		chefService.deleteById(chefId);
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/chef/{chefId}/profilo/aggiorna")
	public String getAggiornaChefProfiloView(@PathVariable("chefId") Long chefId,
			Model model) {
		
		model.addAttribute("chef", chefService.findById(chefId));
		
		return "aggiorna-profilo-chef";
	}
	
	@PostMapping("/admin/chef/{chefId}/profilo/aggiona")
	public String aggiornaIngrediente(@Valid @ModelAttribute("chef") Chef chef, 
			BindingResult ingredienteBindingResult) {
		
		if (!ingredienteBindingResult.hasErrors()) {
			chefService.save(chef);
			return "redirect:/admin";
		}
		
		return "aggiorna-profilo-chef";
		
	}
}
