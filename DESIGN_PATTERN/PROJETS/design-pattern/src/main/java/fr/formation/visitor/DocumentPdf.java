package fr.formation.visitor;

public class DocumentPdf implements IDocument {
    @Override
    public void filter(IFilter filter) {
       filter.visit(this);
    }
}
