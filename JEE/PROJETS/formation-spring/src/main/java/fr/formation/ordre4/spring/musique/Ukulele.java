package fr.formation.ordre4.spring.musique;

import org.springframework.stereotype.Component;

@Component
public class Ukulele implements IInstrument {
	public String son() {
		return "BLIM BLIM BLIM";
	}
}
