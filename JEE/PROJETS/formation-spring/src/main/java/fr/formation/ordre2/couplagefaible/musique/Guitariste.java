package fr.formation.ordre2.couplagefaible.musique;

public class Guitariste implements IMusicien {
	private IInstrument instrument = new Ukulele(); // Dépendance
	
	public void jouer() {
		System.out.println("Le guitariste joue : %s".formatted(this.instrument.son()));
	}
}
