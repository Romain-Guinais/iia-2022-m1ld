using commentaires_service.Context;
using commentaires_service.Controllers.Request;
using commentaires_service.Messaging;
using commentaires_service.Models;
using commentaires_service.Models.Enums;
using Microsoft.AspNetCore.Mvc;
using Polly;
using Steeltoe.Messaging.RabbitMQ.Core;

namespace commentaires_service.Controllers;

[ApiController]
[Route("")]
public class CommentaireApiController : ControllerBase
{
    private readonly ILogger<CommentaireApiController> _logger;
    private readonly CommentaireContext _commentaireContext;
    private readonly RabbitTemplate _rabbitTemplate;

    public CommentaireApiController(ILogger<CommentaireApiController> logger,
                                    CommentaireContext commentaireContext,
                                    RabbitTemplate rabbitTemplate)
    {
        _logger = logger;
        _commentaireContext = commentaireContext;
        _rabbitTemplate = rabbitTemplate;
    }

    [HttpPost]
    public IActionResult Add(CommentaireRequest commentaireRequest)
    {
        Commentaire commentaire = new Commentaire
        {
            Texte = commentaireRequest.Texte,
            NoteQualite = this.validateNote(commentaireRequest.NoteQualite),
            NoteRapport = this.validateNote(commentaireRequest.NoteRapport),
            NoteFacilite = this.validateNote(commentaireRequest.NoteFacilite),
            ProduitId = commentaireRequest.ProduitId,
            Etat = CommentaireEtat.PENDING
        };

        this._commentaireContext.Commentaires.Add(commentaire);
        this._commentaireContext.SaveChanges();

        _rabbitTemplate.ConvertAndSend("ms.produit", "produit.details", new ProduitDetailsCommand
        {
            CommentaireId = commentaire.Id,
            ProduitId = commentaireRequest.ProduitId
        });

        return Ok(commentaire.Id);
    }

    [HttpPut("{id}")]
    public IActionResult Edit([FromRoute] int id, CommentaireRequest commentaireRequest)
    {
        Commentaire commentaire = this._commentaireContext.Commentaires.First(c => c.Id == id);

        if (commentaire == null)
        {
            return StatusCode(StatusCodes.Status404NotFound);
        }

        commentaire.Texte = commentaireRequest.Texte;
        commentaire.NoteQualite = this.validateNote(commentaireRequest.NoteQualite);
        commentaire.NoteRapport = this.validateNote(commentaireRequest.NoteRapport);
        commentaire.NoteFacilite = this.validateNote(commentaireRequest.NoteFacilite);

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
