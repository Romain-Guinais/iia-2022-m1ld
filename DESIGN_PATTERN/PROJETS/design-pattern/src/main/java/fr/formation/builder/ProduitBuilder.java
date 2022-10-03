package fr.formation.builder;

import fr.formation.model.Produit;

public class ProduitBuilder {
    private Produit produit;

    public ProduitBuilder() {
        this.produit = new Produit();
    }

    public ProduitBuilder reset() {
        this.produit = new Produit();
        return this;
    }

    public Produit build() {
        return this.produit;
    }

    public ProduitBuilder withId(int id) {
        if (id > 0) {
            this.produit.setId(id);
        }

        return this;
    }

    public ProduitBuilder withNom(String nom) {
        if (nom != null && !nom.isBlank()) {
            this.produit.setNom(nom);
        }

        return this;
    }

    public ProduitBuilder withPrixAchat(float prixAchat) {
        this.produit.setPrixAchat(prixAchat);
        return this;
    }

    public ProduitBuilder withPrixVente(float prixVente) {
        this.produit.setPrixVente(prixVente);
        return this;
    }

    public ProduitBuilder withStock(int stock) {
        this.produit.setStock(stock);
        return this;
    }

    public ProduitBuilder withFournisseur(String fournisseur) {
        this.produit.setFournisseur(fournisseur);
        return this;
    }
}
