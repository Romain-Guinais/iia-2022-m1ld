package fr.formation.builder;

public class ProduitBuilderDirecteur {
    private ProduitBuilderDirecteur() {

    }
    
    public static ProduitBuilder createFoodProduct(ProduitBuilder builder) {
        builder.reset();
        return builder
            .withNom("Food")
            .withPrixAchat(200)
            .withFournisseur("MIAM");
    }
}
