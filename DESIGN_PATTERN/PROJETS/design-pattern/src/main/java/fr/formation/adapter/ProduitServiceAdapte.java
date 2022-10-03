package fr.formation.adapter;

import fr.formation.service.IProduitService;

public class ProduitServiceAdapte implements IProduitServiceCible {
    private IProduitService srvProduit;
    
    public ProduitServiceAdapte(IProduitService srvProduit) {
        this.srvProduit = srvProduit;
    }

    // On adapte : getAll au lieu de findAll
    @Override
    public String getAll() {
        this.srvProduit.findAll(); // On appelle la méthode
        // On transforme si nécessaire ...
        return "json";
    }
}
