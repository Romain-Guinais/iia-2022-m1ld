namespace commentaires_service.Messaging;

public class CommentaireCreatedCommand
{
    public int CommentaireId { get; set; }
    public string Texte { get; set; } = "";
    public int NoteQualite { get; set; }
    public int NoteRapport { get; set; }
    public int NoteFacilite { get; set; }
    public int ProduitId { get; set; }
}