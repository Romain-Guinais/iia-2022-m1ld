using commentaires_service.Context;
using commentaires_service.Controllers.Request;
using commentaires_service.Controllers.Response;
using Microsoft.AspNetCore.Mvc;
using Polly;

namespace commentaires_service.Controllers;

[ApiController]
[Route("")]
public class CommentaireApiController : ControllerBase
{
    private readonly ILogger<CommentaireApiController> _logger;
    private readonly CommentaireContext _commentaireContext;
    private readonly IHttpClientFactory _httpClientFactory;
    private readonly HttpClient _httpClient;

    public CommentaireApiController(ILogger<CommentaireApiController> logger,
                                    CommentaireContext commentaireContext,
                                    IHttpClientFactory httpClientFactory)
    {
        _logger = logger;
        _commentaireContext = commentaireContext;
        _httpClientFactory = httpClientFactory;
        _httpClient = httpClientFactory.CreateClient("produits-service");
    }

    [HttpGet("{id}")]
    public async Task<CommentaireResponse> FindById([FromRoute] int id)
    {
        Commentaire commentaire = this._commentaireContext.Commentaires.First(c => c.Id == id);

        var fallbackForAnyException = Policy<string>
            .Handle<Exception>()
            .FallbackAsync(async (ct) => "- Inconnu -");
        
        // string produitNom = await _httpClient.GetStringAsync($"/info/{ commentaire.ProduitId }/nom");
        
        string produitNom = await fallbackForAnyException.ExecuteAsync(async () => {
            return await _httpClient.GetStringAsync($"/info/{ commentaire.ProduitId }/nom");
        });

        return new CommentaireResponse
        {
            Id = commentaire.Id,
            Texte = commentaire.Texte,
            Note = (commentaire.NoteQualite + commentaire.NoteRapport + commentaire.NoteFacilite) / 3,
            ProduitNom = produitNom
        };
    }

    [HttpGet("note/{produitId}")]
    public int NoteByProduitId([FromRoute] int produitId)
    {
         double produitNoteAverage = this._commentaireContext.Commentaires
            .Where(c => c.ProduitId == produitId)
            .ToList()
            .DefaultIfEmpty(new Commentaire())
            .Average(c => (c.NoteQualite + c.NoteRapport + c.NoteFacilite) / 3);

        return (int)Math.Floor(produitNoteAverage);
    }

    [HttpGet("by-produit/{produitId}")]
    public List<CommentaireResponse> FindAllByProduitId([FromRoute] int produitId)
    {
        List<Commentaire> commentaires = this._commentaireContext.Commentaires.Where(c => c.ProduitId == produitId).ToList();
        List<CommentaireResponse> response = new List<CommentaireResponse>();

        foreach (Commentaire commentaire in commentaires) {
            response.Add(new CommentaireResponse
            {
                Id = commentaire.Id,
                Texte = commentaire.Texte,
                Note =  (commentaire.NoteQualite + commentaire.NoteRapport + commentaire.NoteFacilite) / 3
            });
        }

        return response;
    }

    [HttpPost]
    public async Task<IActionResult> Add(CommentaireRequest commentaireRequest)
    {
        Boolean notable = await this.isNotable(commentaireRequest.ProduitId);

        if (!notable)
        {
            return StatusCode(StatusCodes.Status400BadRequest);
        }

        Commentaire commentaire = new Commentaire
        {
            Texte = commentaireRequest.Texte,
            NoteQualite = this.validateNote(commentaireRequest.NoteQualite),
            NoteRapport = this.validateNote(commentaireRequest.NoteRapport),
            NoteFacilite = this.validateNote(commentaireRequest.NoteFacilite),
            ProduitId = commentaireRequest.ProduitId
        };

        this._commentaireContext.Commentaires.Add(commentaire);
        this._commentaireContext.SaveChanges();

        return Ok(commentaire.Id);
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> Edit([FromRoute] int id, CommentaireRequest commentaireRequest)
    {
        Commentaire commentaire = this._commentaireContext.Commentaires.First(c => c.Id == id);
        Boolean notable = await this.isNotable(commentaireRequest.ProduitId);

        if (commentaire == null)
        {
            return StatusCode(StatusCodes.Status404NotFound);
        }

        if (!notable)
        {
            return StatusCode(StatusCodes.Status400BadRequest);
        }

        commentaire.Texte = commentaireRequest.Texte;
        commentaire.NoteQualite = this.validateNote(commentaireRequest.NoteQualite);
        commentaire.NoteRapport = this.validateNote(commentaireRequest.NoteRapport);
        commentaire.NoteFacilite = this.validateNote(commentaireRequest.NoteFacilite);
        commentaire.ProduitId = commentaireRequest.ProduitId;

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

    private async Task<Boolean> isNotable(int produitId) {
        var fallbackForAnyException = Policy<Boolean>
            .Handle<Exception>()
            .FallbackAsync(async (ct) => false);
        
        // return await _httpClient.GetFromJsonAsync<Boolean>($"/info/{ commentaireRequest.ProduitId }/notable");
        
        return await fallbackForAnyException.ExecuteAsync(async () => {
            return await _httpClient.GetFromJsonAsync<Boolean>($"/info/{ produitId }/notable");
        });
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
