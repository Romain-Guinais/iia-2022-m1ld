namespace commentaires_service.Controllers.Request;

public class CommentaireRequest
{
    public string? Texte { get; set; }
    public int Note { get; set; }
    public int ProduitId { get; set; }
}