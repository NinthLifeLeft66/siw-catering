package it.uniroma3.siw.catering.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.CredentialsService;

@Controller
public class MainController {
	
	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private ChefService chefService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
		Collection<Chef> elencoChef = chefService.findAll();
		
		model.addAttribute("elencoChef", elencoChef);		
		model.addAttribute("username", credentials.getUsername());

		return "home";
		
	}
	
	@GetMapping("/admin")
	public String adminHome(Model model) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
		Collection<Chef> elencoChef = chefService.findAll();
		
		model.addAttribute("username", credentials.getUsername());
		model.addAttribute("elencoChef", elencoChef);
		
		return "admin-home";
		
	}
}
