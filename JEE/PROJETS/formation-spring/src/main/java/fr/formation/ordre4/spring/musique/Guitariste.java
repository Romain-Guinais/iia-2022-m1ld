package fr.formation.ordre4.spring.musique;

import org.springframework.stereotype.Component;

@Component
public class Guitariste implements IMusicien {
	private IInstrument instrument; // Dépendance
	
	public Guitariste(IInstrument instrument) {
		this.instrument = instrument;
	}
	
	public void jouer() {
		System.out.println("Le guitariste joue : %s".formatted(this.instrument.son()));
	}
}
