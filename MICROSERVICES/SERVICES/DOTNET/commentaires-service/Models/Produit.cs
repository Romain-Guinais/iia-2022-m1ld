using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace commentaires_service.Controllers;


[Table("produit")]
public class Produit
{
    [Key]
    [Column("pro_id")]
    public int Id { get; set; }

    [Column("pro_nom")]
    public string Nom { get; set; } = "";

    [Column("pro_prix")]
    public Double Prix { get; set; }

    [Column("pro_notable")]
    public Boolean Notable { get; set; }
}
