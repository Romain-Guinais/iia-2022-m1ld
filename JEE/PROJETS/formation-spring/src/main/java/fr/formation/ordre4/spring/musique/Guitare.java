package fr.formation.ordre4.spring.musique;

public class Guitare implements IInstrument {
	public Guitare() {
		System.out.println("CREATION DE LA GUITARE");
	}
	
	public String son() {
		return "GLINK GLINK GLINK";
	}
}
