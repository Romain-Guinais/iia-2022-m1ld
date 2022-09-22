package fr.formation.ordre1.couplagefort;

import fr.formation.ordre1.couplagefort.musique.Guitariste;
import fr.formation.ordre1.couplagefort.musique.Pianiste;

public class Application {
	public static void main(String[] args) {
		Guitariste guitariste = new Guitariste();
		Pianiste pianiste = new Pianiste();

		System.out.println(" -- Couplage fort --");
		faireJouer(guitariste);
		faireJouer(pianiste);
	}
	
	public static void faireJouer(Guitariste guitariste) {
		guitariste.jouer();
	}
	
	public static void faireJouer(Pianiste pianiste) {
		pianiste.jouer();
	}
}
