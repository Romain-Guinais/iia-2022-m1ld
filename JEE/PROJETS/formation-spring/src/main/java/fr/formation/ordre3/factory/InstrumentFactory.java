package fr.formation.ordre3.factory;

import fr.formation.ordre3.factory.musique.Guitare;
import fr.formation.ordre3.factory.musique.IInstrument;
import fr.formation.ordre3.factory.musique.Piano;

public class InstrumentFactory {
	private InstrumentFactory() { }
	
	public static IInstrument guitare() {
		return new Guitare();
	}
	
	public static IInstrument piano() {
		return new Piano();
	}
}
