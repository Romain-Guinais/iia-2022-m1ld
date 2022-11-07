using commentaires_service.Context;
using Microsoft.EntityFrameworkCore;

namespace commentaires_service.Startup;

public static class DbContextStartup
{
    public static IServiceCollection UseDbContextConfiguration(this IServiceCollection services, ConfigurationManager configManager)
    {
        services.AddDbContext<CommentaireContext>(options =>
            options.UseNpgsql(configManager.GetConnectionString("CommentaireContext"))
        );


        return services;
    }
}