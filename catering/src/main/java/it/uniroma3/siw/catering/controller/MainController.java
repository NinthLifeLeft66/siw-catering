package it.uniroma3.siw.catering.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.model.User;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.CredentialsService;
import it.uniroma3.siw.catering.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home(@AuthenticationPrincipal OidcUser oidcUser, Model model) {
		
		User currentUser = userService.getCurrentUser(oidcUser);
		
		Collection<Buffet> elencoBuffet = buffetService.findAll();
		Collection<Chef> elencoChef = chefService.findAll();
		
		model.addAttribute("elencoBuffet", elencoBuffet);
		model.addAttribute("elencoChef", elencoChef);		
		model.addAttribute("username", currentUser);

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
