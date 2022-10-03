package fr.formation.factory;

import fr.formation.service.IProduitService;

public class Application {
    public static void main(String[] args) {
        IProduitService srvProduit = ServiceFactory.createProduitService(1);

        srvProduit.findAll();
    }
}
