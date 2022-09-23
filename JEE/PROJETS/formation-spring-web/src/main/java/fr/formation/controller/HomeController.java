package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.request.UtilisateurRequest;

@Controller // On demande à SPRING de la manager en tant que Controller Web
public class HomeController {
//	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	@GetMapping("/welcome")
	public String welcome(@RequestParam(required = false) String username, @RequestParam(required = false) Integer id, Model model) {
		model.addAttribute("utilisateurSession", username);
		System.out.println(id);
		
		return "welcome";
//		return "/WEB-INF/views/welcome.jsp";
	}
	
	@GetMapping("/welcome-binding")
	public String welcomeBinding(UtilisateurRequest utilisateurRequest) {
		System.out.println(utilisateurRequest.getUsername());
		System.out.println(utilisateurRequest.getId());
		
		return "welcome";
	}
}
