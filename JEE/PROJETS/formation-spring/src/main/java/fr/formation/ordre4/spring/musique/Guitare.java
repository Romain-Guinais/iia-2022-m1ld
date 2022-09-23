package fr.formation.ordre4.spring.musique;

import org.springframework.stereotype.Component;

@Component // On demande à SPRING d'instancier cette classe et de la manager
public class Guitare implements IInstrument {
	public Guitare() {
		System.out.println("CREATION DE LA GUITARE");
	}
	
	public String son() {
		return "GLINK GLINK GLINK";
	}
}
