package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // On demande à SPRING de la manager en tant que Controller Web
public class HomeController {
	@RequestMapping("/welcome")
	public String welcome() {
		return "/WEB-INF/views/welcome.jsp";
	}
}
