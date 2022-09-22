package fr.formation.ordre2.couplagefaible;

import fr.formation.ordre2.couplagefaible.musique.Guitariste;
import fr.formation.ordre2.couplagefaible.musique.IMusicien;
import fr.formation.ordre2.couplagefaible.musique.Pianiste;

public class Application {
	public static void main(String[] args) {
		IMusicien guitariste = new Guitariste();
		IMusicien pianiste = new Pianiste();

		System.out.println(" -- Couplage faible --");
		faireJouer(guitariste);
		faireJouer(pianiste);
	}
	
	public static void faireJouer(IMusicien musicien) {
		musicien.jouer();
	}
}
