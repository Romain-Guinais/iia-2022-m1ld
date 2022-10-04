package fr.formation.visitor;

public interface IDocument {
    // Méthode "accept", on accepte un Visitor
    public void filter(IFilter filter);
}
