using commentaires_service.Messaging;
using Steeltoe.Connector.RabbitMQ;
using Steeltoe.Messaging.RabbitMQ.Config;
using Steeltoe.Messaging.RabbitMQ.Extensions;

namespace commentaires_service.Startup;

public static class RabbitMqStartup
{
    public static IServiceCollection UseRabbitConfiguration(this IServiceCollection services, ConfigurationManager configManager)
    {
        // Configuration générale
        services.AddRabbitMQConnection(configManager);
        services.AddRabbitServices(true);
        services.AddRabbitAdmin();
        services.AddRabbitTemplate();

        // Configuration de la queue "produit en suppression" pour le service commentaire
        services.AddRabbitQueue("ms.produit.deletion.commentaire");

        // Liaison de cette queue à l'Exchange ms.produit
        services.AddRabbitBinding("produitDeletion", Binding.DestinationType.QUEUE, (p, b) => {
            var binding = b as QueueBinding;

            binding.Exchange = "ms.produit";
            binding.Destination = "ms.produit.deletion.commentaire";
            binding.RoutingKey = "produit.deletion.askfor"; // On écoute les demandes de suppression
        });

        // Configuration du service qui captera les évènements reçus
        services.AddSingleton<ProduitEventHandler>();
        services.AddRabbitListeners<ProduitEventHandler>();

        return services;
    }
}