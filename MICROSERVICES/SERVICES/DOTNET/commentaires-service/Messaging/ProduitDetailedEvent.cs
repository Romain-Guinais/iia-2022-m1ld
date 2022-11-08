namespace commentaires_service.Messaging;

public class ProduitDetailedEvent
{
    public int CommentaireId { get; set; }
    public int ProduitId { get; set; }
    public Boolean Notable { get; set; }
}