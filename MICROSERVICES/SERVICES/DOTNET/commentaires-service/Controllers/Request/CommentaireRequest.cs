namespace commentaires_service.Controllers.Request;

public class CommentaireRequest
{
    public string? Texte { get; set; }
    public int NoteQualite { get; set; }
    public int NoteRapport { get; set; }
    public int NoteFacilite { get; set; }
    public int ProduitId { get; set; }
}