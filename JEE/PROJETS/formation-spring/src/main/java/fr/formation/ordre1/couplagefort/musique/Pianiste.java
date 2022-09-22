package fr.formation.ordre1.couplagefort.musique;

public class Pianiste implements IMusicien {
	private Piano piano = new Piano(); // Dépendance
	
	public void jouer() {
		System.out.println("Le pianiste joue : %s".formatted(this.piano.son()));
	}
}
