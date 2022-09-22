package fr.formation.ordre3.factory;

import fr.formation.ordre3.factory.musique.Guitariste;
import fr.formation.ordre3.factory.musique.IMusicien;
import fr.formation.ordre3.factory.musique.Pianiste;

public class MusicienFactory {
	private MusicienFactory() { }
	
	public static IMusicien guitariste() {
		return new Guitariste(InstrumentFactory.guitare());
	}
	
	public static IMusicien pianiste() {
		Pianiste pianiste = new Pianiste();
		
		pianiste.setInstrument(InstrumentFactory.piano());
		
		return pianiste;
	}
}
