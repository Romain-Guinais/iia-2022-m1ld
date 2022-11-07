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

    [Column("com_note_qualite")]
    public int NoteQualite { get; set; }

    [Column("com_note_rapport")]
    public int NoteRapport { get; set; }

    [Column("com_note_facilite")]
    public int NoteFacilite { get; set; }

    [Column("com_produit_id")]
    public int ProduitId { get; set; }
}
