package fr.formation.builder;

import fr.formation.model.Produit;

public class Application {
    public static void main(String[] args) {
        Produit produit = new ProduitBuilder()
            .withId(-3)
            .withNom("Le nom")
            .withPrixAchat(10)
            .withPrixVente(50)
            .withFournisseur("Amazon")
            .build();
        
        System.out.println(produit.toString());

        System.out.println("-- Exemple avec Directeur --");


        Produit produit2 = ProduitBuilderDirecteur
            .createFoodProduct(new ProduitBuilder())
            .withNom("Sushi")
            .build();
        
        System.out.println(produit2.toString());
    }
}
