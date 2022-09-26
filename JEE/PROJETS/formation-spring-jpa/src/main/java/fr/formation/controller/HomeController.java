package fr.formation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.request.UtilisateurRequest;

@Controller // On demande à SPRING de la manager en tant que Controller Web
public class HomeController {
//	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	@GetMapping("/welcome")
	public String welcome(HttpSession session) {
		if (session.getAttribute("utilisateurSession") == null) {
			return "redirect:/login";
		}
		
		return "welcome";
//		return "/WEB-INF/views/welcome.jsp";
	}
	
	@GetMapping("/welcome-binding")
	public String welcomeBinding(UtilisateurRequest utilisateurRequest) {
		System.out.println(utilisateurRequest.getUsername());
		System.out.println(utilisateurRequest.getId());
		System.out.println(utilisateurRequest.getDemo().getDemo());
		
		return "welcome";
	}
	
	@GetMapping("/welcome/{username}")
	public String welcomePathVariable(@PathVariable String username, Model model) {
		model.addAttribute("utilisateurSession", username);
		
		return "welcome";
	}
}
