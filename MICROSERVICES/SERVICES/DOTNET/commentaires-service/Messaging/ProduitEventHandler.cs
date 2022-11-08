using commentaires_service.Context;
using Steeltoe.Messaging.RabbitMQ.Attributes;
using Steeltoe.Messaging.RabbitMQ.Core;
using Steeltoe.Messaging.RabbitMQ.Extensions;

namespace commentaires_service.Messaging;

public class ProduitEventHandler
{
    private readonly IServiceProvider _services;

    public ProduitEventHandler(IServiceProvider services)
    {
        _services = services;
    }

    [RabbitListener(Binding = "produitDeletion")]
    public void on(ProduitDeletionEvent evt)
    {
        RabbitTemplate rabbitTemplate = _services.GetRabbitTemplate();

        using (var scope = _services.CreateScope())
        {
            var commentaireContext = scope.ServiceProvider.GetService<CommentaireContext>();
            int count = commentaireContext.Commentaires.Count(c => c.ProduitId == evt.ProduitId);

            if (count == 0) {
                rabbitTemplate.ConvertAndSend("ms.produit", "produit.deletion.ok", new ProduitDeleteOkCommand
                {
                    ProduitId = evt.ProduitId
                });
            }

            else {
                rabbitTemplate.ConvertAndSend("ms.produit", "produit.deletion.ko", new ProduitDeleteKoCommand
                {
                    ProduitId = evt.ProduitId
                });
            }
        }
    }
}