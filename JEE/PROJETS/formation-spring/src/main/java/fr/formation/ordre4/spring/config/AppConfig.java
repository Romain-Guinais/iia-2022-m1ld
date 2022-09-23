package fr.formation.ordre4.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // Classe de configuration SPRING
@ComponentScan("fr.formation.ordre4.spring")
@PropertySource("classpath:/musique.properties")
public class AppConfig {

//	@Bean // On demande à SPRING de gérer cette instance
//	public IInstrument guitare() {
//		return new Guitare();
//	}
//
//	@Bean
//	public IMusicien guitariste(IInstrument guitare) {
//		return new Guitariste(guitare);
//	}
}
