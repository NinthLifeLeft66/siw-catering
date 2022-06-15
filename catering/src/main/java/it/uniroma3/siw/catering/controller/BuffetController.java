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
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/buffet/{buffetId}")
	public String getBuffet(@PathVariable("buffetId") Long buffetId,
			Model model) {
		
		Buffet buffet = buffetService.findById(buffetId);
		
		model.addAttribute("buffet", buffet);
		
		return "buffet";
	}
	
	@GetMapping("/admin/chef/{chefId}/buffet/new")
	public String getViewCreateBuffet(@PathVariable("chefId") Long chefId,
			Model model) {
		
		model.addAttribute("buffet", new Buffet());
		
		Chef chef = chefService.findById(chefId);
		model.addAttribute("chef", chef);
		
		return "crea-buffet";
	}
	
	@PostMapping("/admin/chef/{chefId}/buffet")
	public String createBuffet(@Valid @ModelAttribute("buffet") Buffet buffet,
			BindingResult bindingResult,
			@PathVariable("chefId") Long chefId) {
		
		if(!bindingResult.hasErrors()) {
			
			Chef chef = chefService.findById(chefId);
			
			buffet.setChef(chef);
			
			buffetService.save(buffet);
			chefService.save(chef);
			
			return "redirect:/admin";
		}
		
		return "crea-buffet";
	}
	
	@GetMapping("/admin/buffet/{buffetId}")
	public String deleteBuffet(@PathVariable("buffetId") Long buffetId) {
		
		Buffet buffet = buffetService.findById(buffetId);
		
		Chef chef = buffet.getChef();
		chef.getBuffetProposti().remove(buffet);
		
		buffetService.deleteById(buffetId);
		
		return "redirect:/admin";
		
	}
	
}
