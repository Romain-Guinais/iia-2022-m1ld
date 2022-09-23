package fr.formation.ordre4.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.formation.ordre4.spring.musique.IInstrument;
import fr.formation.ordre4.spring.musique.IMusicien;
import fr.formation.ordre4.spring.musique.Guitare;
import fr.formation.ordre4.spring.musique.Guitariste;

@Configuration // Classe de configuration SPRING
public class AppConfig {

	@Bean // On demande à SPRING de gérer cette instance
	public IInstrument guitare() {
		return new Guitare();
	}

	@Bean
	public IMusicien guitariste(IInstrument guitare) {
		return new Guitariste(guitare);
	}
}
