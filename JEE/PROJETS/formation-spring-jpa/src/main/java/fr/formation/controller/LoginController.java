package fr.formation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login") // Préfixer tous les mappings des méthodes de cette classe de "/login"
public class LoginController {
	@GetMapping
	public String login() {
		return "login";
	}
	
	@PostMapping
	public String login(@RequestParam String username, HttpSession session, Model model) {
		if (username.isBlank()) {
			model.addAttribute("error", true);
			return "login";
		}
		
		session.setAttribute("utilisateurSession", username);
		
		return "redirect:/welcome";
	}
}
