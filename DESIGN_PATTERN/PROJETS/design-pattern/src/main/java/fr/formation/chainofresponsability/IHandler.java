package fr.formation.chainofresponsability;

import fr.formation.model.Produit;

public interface IHandler {
    public IHandler chain(IHandler handler); // setSuivant
    public Produit handle(int id);
}
