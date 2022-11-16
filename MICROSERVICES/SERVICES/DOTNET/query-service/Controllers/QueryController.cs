using commentaires_service.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using query_service.Controllers.Response;

namespace query_service.Controllers
{
    public class QueryController : Controller
    {
        private readonly ILogger<QueryController> _logger;
        private readonly QueryContext _queryContext;
        private readonly IHttpClientFactory _httpClientFactory;
        private readonly HttpClient _httpClient;
        private readonly RabbitTemplate _rabbitTemplate;

        public QueryController(ILogger<QueryController> logger,
                                    QueryContext queryContext,
                                    IHttpClientFactory httpClientFactory,
                                    RabbitTemplate rabbitTemplate)
        {
            _logger = logger;
            _queryContext = queryContext;
            _httpClientFactory = httpClientFactory;
            _httpClient = httpClientFactory.CreateClient("produits-service");
            _rabbitTemplate = rabbitTemplate;
        }


        [HttpGet("{id}")]
        public async Task<CommentaireResponse> FindById([FromRoute] int id)
        {
            Commentaire commentaire = this._queryContext.Commentaires.First(c => c.Id == id);

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

    }
}
