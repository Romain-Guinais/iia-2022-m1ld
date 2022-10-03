package fr.formation.factory;

import fr.formation.service.IProduitService;
import fr.formation.service.ProduitService;
import fr.formation.service.ProduitServiceV2;

public class ServiceFactory {
    private ServiceFactory() {

    }

    public static IProduitService createProduitService(int version) {
        // On injecte également les dépendances du ProduitService (connexion DB, ou repository, etc.)
        if (version == 2) {
            return new ProduitServiceV2();
        }
        
        return new ProduitService();
    }
}
