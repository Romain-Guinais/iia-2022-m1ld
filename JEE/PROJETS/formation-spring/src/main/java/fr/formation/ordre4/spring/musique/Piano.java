package fr.formation.ordre4.spring.musique;

import org.springframework.stereotype.Component;

@Component
public class Piano implements IInstrument {
	public String son() {
		return "PLAK PLAK PLAK";
	}
}
