package fr.formation.state.with;

import lombok.Setter;

public class Document {
    @Setter
    private IDocumentEtat etat = new DocumentEtatFerme(this);

    public void ouvrir() {
        this.etat.ouvrir();
    }

    public void enregistrer() {
        this.etat.enregistrer();
    }

    public void fermer() {
        this.etat.fermer();
    }
}
