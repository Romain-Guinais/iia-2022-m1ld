package fr.formation.composite;

import fr.formation.builder.ProduitBuilder;

public class Application {
    public static void main(String[] args) {
        PackComposite pack1 = new PackComposite();
        PackComposite pack2 = new PackComposite();

        pack1.ajouter(new ProduitBuilder().withPrixVente(10).build());
        pack1.ajouter(new ProduitBuilder().withPrixVente(20).build());
        
        pack2.ajouter(pack1);
        pack2.ajouter(new ProduitBuilder().withPrixVente(100).build());

        System.out.println("Total du pack 1");
        System.out.println(pack1.getPrixVente());

        System.out.println("Total du pack 2");
        System.out.println(pack2.getPrixVente());
    }
}
