using commentaires_service.Context;
using commentaires_service.Controllers.Request;
using commentaires_service.Controllers.Response;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace commentaires_service.Controllers;

[ApiController]
[Route("")]
public class CommentaireApiController : ControllerBase
{
    private readonly ILogger<CommentaireApiController> _logger;
    private readonly CommentaireContext _commentaireContext;

    public CommentaireApiController(ILogger<CommentaireApiController> logger,
                                    CommentaireContext commentaireContext)
    {
        _logger = logger;
        _commentaireContext = commentaireContext;
    }

    [HttpGet("{id}")]
    public CommentaireResponse FindById([FromRoute] int id)
    {
        Commentaire commentaire = this._commentaireContext.Commentaires
            .Include(c => c.Produit)
            .First(c => c.Id == id);

        return new CommentaireResponse
        {
            Id = commentaire.Id,
            Texte = commentaire.Texte,
            Note = commentaire.Note,
            ProduitNom = commentaire.Produit.Nom
        };
    }

    [HttpPost]
    public IActionResult Add(CommentaireRequest commentaireRequest)
    {
        Produit produit = this._commentaireContext.Produits.First(p => p.Id == commentaireRequest.ProduitId);
        
        if (!produit.Notable)
        {
            return StatusCode(StatusCodes.Status400BadRequest);
        }

        Commentaire commentaire = new Commentaire
        {
            Texte = commentaireRequest.Texte,
            Note = this.validateNote(commentaireRequest.Note),
            Produit = produit
        };

        this._commentaireContext.Commentaires.Add(commentaire);
        this._commentaireContext.SaveChanges();

        return Ok(commentaire.Id);
    }

    [HttpPut("{id}")]
    public IActionResult Edit([FromRoute] int id, CommentaireRequest commentaireRequest)
    {
        Commentaire commentaire = this._commentaireContext.Commentaires.First(c => c.Id == id);
        Produit produit = this._commentaireContext.Produits.First(p => p.Id == commentaireRequest.ProduitId);

        if (commentaire == null)
        {
            return StatusCode(StatusCodes.Status404NotFound);
        }

        if (!produit.Notable)
        {
            return StatusCode(StatusCodes.Status400BadRequest);
        }

        commentaire.Texte = commentaireRequest.Texte;
        commentaire.Note = this.validateNote(commentaireRequest.Note);
        commentaire.Produit = produit;

        this._commentaireContext.SaveChanges();

        return Ok(commentaire.Id);
    }

    [HttpDelete("{id}")]
    public IActionResult DeleteById([FromRoute] int id)
    {
        try
        {
            this._commentaireContext.Commentaires.Remove(new Commentaire() { Id = id });
            this._commentaireContext.SaveChanges();

            return Ok(true);
        }

        catch
        {
            return StatusCode(StatusCodes.Status500InternalServerError);
        }
    }

    private int validateNote(int note) {
        if (note < 0) {
            return 0;
        }

        if (note > 5) {
            return 5;
        }

        return note;
    }
}
