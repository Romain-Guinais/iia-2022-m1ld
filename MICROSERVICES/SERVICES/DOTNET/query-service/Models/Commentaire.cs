using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using commentaires_service.Models.Enums;

namespace commentaires_service.Models;


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

    [Column("com_etat")]
    public CommentaireEtat Etat { get; set; }

    [Column("com_produit_id")]
    public int ProduitId { get; set; }
}
