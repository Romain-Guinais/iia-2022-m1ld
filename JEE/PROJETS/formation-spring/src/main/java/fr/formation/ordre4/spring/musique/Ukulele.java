package fr.formation.ordre4.spring.musique;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Ukulele implements IInstrument {
	@Value("${musique.instrument.son.ukulele}")
	private String melodie;
	
	public String son() {
		return this.melodie;
	}
}
