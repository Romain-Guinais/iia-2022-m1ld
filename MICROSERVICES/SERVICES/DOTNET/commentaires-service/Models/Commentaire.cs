using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace commentaires_service.Controllers;


[Table("commentaire")]
public class Commentaire
{
    [Key]
    [Column("com_id")]
    public int Id { get; set; }

    [Column("com_texte")]
    public string Texte { get; set; } = "";

    [Column("com_note")]
    public int Note { get; set; }

    [ForeignKey("com_produit_id")]
    public virtual Produit? Produit { get; set; }
}
