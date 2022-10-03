package fr.formation.flyweight;

import java.util.ArrayList;
import java.util.List;

public class Texte {
    private AttributFlyweight attributFlyweight = new AttributFlyweight();
    private List<Caractere> caracteres = new ArrayList<>();

    public void ajouter(Character codeClavier, int couleur, int taille, int police) {
        this.caracteres.add(new Caractere(codeClavier, attributFlyweight.getAttributCaractereN(couleur, taille, police)));
    }

    public void imprimer() {
        for (Caractere c : this.caracteres) {
            System.out.println(c.getCodeClavier() + " [" + c.getAttribut() + "]");
        }
    }
}
