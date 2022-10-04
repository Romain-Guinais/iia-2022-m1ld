package fr.formation.visitor;

public class DocumentFauteFilter implements IFilter {
    @Override
    public void visit(IDocument document) {
        System.out.println("[FILTRE FAUTES] Document visité !");
    }
    
    @Override
    public void visit(DocumentPdf docPdf) {
        System.out.println("[FILTRE FAUTES] PDf visité !");
    }
    
    @Override
    public void visit(DocumentHtml docHtml) {
        System.out.println("[FILTRE FAUTES] HTML visité !");
    }
}
