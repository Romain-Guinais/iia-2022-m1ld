package fr.formation.ordre4.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.formation.ordre4.spring.musique.IInstrument;
import fr.formation.ordre4.spring.musique.IMusicien;
import fr.formation.ordre4.spring.config.AppConfig;

public class Application {

	public static void main(String[] args) {
		// Chargement du contexte de SPRING
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		// On extrait la Guitare du contexte de SPRING pour la manipuler
		IMusicien guitariste = ctx.getBean("guitariste", IMusicien.class);
		guitariste.jouer();
		
		// On ferme de contexte de SPRING
		ctx.close();
	}
}
