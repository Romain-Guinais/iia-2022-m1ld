package fr.formation.ordre4.spring.musique;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy // Tant qu'on a pas besoin de l'instance, SPRING ne l'instancie pas
//@Scope("prototype") // Scope de la classe => durée de vie
public class Guitariste implements IMusicien {
	private IInstrument instrument; // Dépendance
	
//	public Guitariste(IInstrument guitare) {
//		System.out.println("CREATION GUITARISTE");
//		this.instrument = guitare;
//	}
	
	public Guitariste(@Qualifier("ukulele") IInstrument instrument) {
		System.out.println("CREATION GUITARISTE");
		this.instrument = instrument;
	}
	
	public void jouer() {
		System.out.println("Le guitariste joue : %s".formatted(this.instrument.son()));
	}
}
