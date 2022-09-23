package fr.formation.ordre4.spring.musique;

public class Pianiste implements IMusicien {
	private IInstrument instrument; // Dépendance
	
	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}
	
	public void jouer() {
		System.out.println("Le pianiste joue : %s".formatted(this.instrument.son()));
	}
}
