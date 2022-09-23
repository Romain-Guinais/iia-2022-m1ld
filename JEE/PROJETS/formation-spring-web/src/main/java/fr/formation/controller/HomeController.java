package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // On demande à SPRING de la manager en tant que Controller Web
public class HomeController {
//	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	@GetMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("utilisateurSession", "test model Spring");
		
		return "welcome";
//		return "/WEB-INF/views/welcome.jsp";
	}
}
