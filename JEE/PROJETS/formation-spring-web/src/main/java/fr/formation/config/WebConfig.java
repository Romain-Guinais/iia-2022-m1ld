package fr.formation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc // Activer la délégation des requêtes vers les @Controller
public class WebConfig {

}
