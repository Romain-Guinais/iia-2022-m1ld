package fr.formation.ordre4.spring.musique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Pianiste implements IMusicien {
	@Autowired // On demande à SPRING de nous injecter la référence de l'instrument ..
	@Qualifier("piano")
	private IInstrument instrument; // Dépendance

//	@Autowired // OU BIEN ICI
	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}
	
	public void jouer() {
		System.out.println("Le pianiste joue : %s".formatted(this.instrument.son()));
	}
}
