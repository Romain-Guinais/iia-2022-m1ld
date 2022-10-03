package fr.formation.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Produit {
    private int id;
    private String nom;
    private float prixAchat;
    private float prixVente;
    private int stock;
    private String fournisseur;

    @Setter(AccessLevel.NONE)
    private String reference;
    
    public class Builder {
        private Produit produit;

        public Builder() {
            this.produit = new Produit();
        }

        public Builder reset() {
            this.produit = new Produit();
            return this;
        }

        public Produit build() {
            return this.produit;
        }

        public Builder withId(int id) {
            if (id > 0) {
                this.produit.setId(id);
            }

            return this;
        }

        public Builder withNom(String nom) {
            if (nom != null && !nom.isBlank()) {
                this.produit.setNom(nom);
            }

            return this;
        }

        public Builder withPrixAchat(float prixAchat) {
            this.produit.setPrixAchat(prixAchat);
            return this;
        }

        public Builder withPrixVente(float prixVente) {
            this.produit.setPrixVente(prixVente);
            return this;
        }

        public Builder withStock(int stock) {
            this.produit.setStock(stock);
            return this;
        }

        public Builder withFournisseur(String fournisseur) {
            this.produit.setFournisseur(fournisseur);
            return this;
        }

        // Utilité d'un Builder en classe interne
        public Builder withReference(String reference) {
            this.produit.reference = reference;
            return this;
        }
    }
}
