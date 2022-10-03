package fr.formation.composite;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class PackComposite implements Composant {
    private Composant parent;
    private List<Composant> enfants = new ArrayList<>();

    public void ajouter(Composant enfant) {
        this.enfants.add(enfant);
    }

    public void retirer(Composant enfant) {
        this.enfants.remove(enfant);
    }

    @Override
    public float getPrixVente() {
        return this.enfants.stream()
            .map(Composant::getPrixVente) // Transforme le composant en float (prix vente)
            .reduce(0f, (soustotal, prix) -> soustotal + prix); // réduit la liste des prix à un prix total
    }
}
