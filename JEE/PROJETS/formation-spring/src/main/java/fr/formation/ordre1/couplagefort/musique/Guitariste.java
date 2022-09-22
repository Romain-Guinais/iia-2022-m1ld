package fr.formation.ordre1.couplagefort.musique;

public class Guitariste implements IMusicien {
	private Guitare guitare = new Guitare(); // Dépendance
	
	public void jouer() {
		System.out.println("Le guitariste joue : %s".formatted(this.guitare.son()));
	}
}
