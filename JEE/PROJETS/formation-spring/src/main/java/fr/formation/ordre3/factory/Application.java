package fr.formation.ordre3.factory;

import fr.formation.ordre3.factory.musique.IMusicien;

public class Application {
	public static void main(String[] args) {
		IMusicien guitariste = MusicienFactory.guitariste();
		IMusicien pianiste = MusicienFactory.pianiste();

		System.out.println(" -- Couplage faible + Factory --");
		faireJouer(guitariste);
		faireJouer(pianiste);
	}
	
	public static void faireJouer(IMusicien musicien) {
		musicien.jouer();
	}
}
