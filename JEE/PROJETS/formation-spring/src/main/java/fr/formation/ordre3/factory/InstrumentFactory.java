package fr.formation.ordre3.factory;

import fr.formation.ordre3.factory.musique.IInstrument;
import fr.formation.ordre3.factory.musique.Piano;
import fr.formation.ordre3.factory.musique.Ukulele;

public class InstrumentFactory {
	private InstrumentFactory() { }
	
	public static IInstrument guitare() {
		return new Ukulele();
	}
	
	public static IInstrument piano() {
		return new Piano();
	}
}
