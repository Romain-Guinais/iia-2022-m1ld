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
		
		IMusicien guitariste2 = ctx.getBean("guitariste", IMusicien.class);
		guitariste2.jouer();
		
		IMusicien pianiste = ctx.getBean("pianiste", IMusicien.class);
		pianiste.jouer();
		
		if (guitariste == guitariste2) {
			System.out.println("C PAREIL");
		}
		
		else {
			System.out.println("C PAS PAREIL");
		}
		// On ferme de contexte de SPRING
		ctx.close();
	}
}
