package fr.formation.adapter;

import fr.formation.factory.ServiceFactory;
import fr.formation.service.IProduitService;

public class Application {
    public static void main(String[] args) {
        IProduitService srvProduit = ServiceFactory.createProduitService(1);
        IProduitServiceCible client = new ProduitServiceAdapte(srvProduit);

        System.out.println(client.getAll());
    }
}
