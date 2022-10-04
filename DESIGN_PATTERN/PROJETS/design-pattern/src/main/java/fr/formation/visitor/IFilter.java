package fr.formation.visitor;

// Notre visitor
public interface IFilter {
    // Exemples de visites
    public void visit(IDocument document);
    public void visit(DocumentPdf docPdf);
    public void visit(DocumentHtml docHtml);
}
