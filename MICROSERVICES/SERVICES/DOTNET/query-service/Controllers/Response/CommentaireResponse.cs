namespace query_service.Controllers.Response;

public class CommentaireResponse
{
    public int Id { get; set; }
    public string? Texte { get; set; }
    public int Note { get; set; }
    public string ProduitNom { get; set; } = "";
}