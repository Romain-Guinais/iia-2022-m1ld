using commentaires_service.Models;
using Microsoft.EntityFrameworkCore;

namespace commentaires_service.Context;

public class CommentaireContext : DbContext
{
    public DbSet<Commentaire>? Commentaires { get; set; }

    public CommentaireContext(DbContextOptions<CommentaireContext> options) : base(options)
    {

    }
}