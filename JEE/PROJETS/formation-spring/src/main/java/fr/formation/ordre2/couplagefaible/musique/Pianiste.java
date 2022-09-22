package fr.formation.ordre2.couplagefaible.musique;

public class Pianiste implements IMusicien {
	private IInstrument instrument = new Piano(); // Dépendance
	
	public void jouer() {
		System.out.println("Le pianiste joue : %s".formatted(this.instrument.son()));
	}
}
