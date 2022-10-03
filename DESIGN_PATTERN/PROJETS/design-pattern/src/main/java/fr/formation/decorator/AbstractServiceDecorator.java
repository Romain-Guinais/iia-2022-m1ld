package fr.formation.decorator;

import fr.formation.service.IProduitService;

public abstract class AbstractServiceDecorator implements IProduitService {
    protected IProduitService srvProduit;

    public AbstractServiceDecorator(IProduitService srvProduit) {
        this.srvProduit = srvProduit;
    }
}
