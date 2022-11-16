package fr.formation.queryservice.messaging;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.formation.queryservice.model.Produit;
import fr.formation.queryservice.repo.IProduitRepository;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ProduitEventHandler {
    private final IProduitRepository repoProduit;

    @Bean
    public Consumer<ProduitCreatedEvent> produitCreated() {
        return evt -> {
            Produit produit = Produit.builder()
                .id(evt.getProduitId())
                .nom(evt.getNom())
                .prix(evt.getPrix())
                .note(-1)
                .build();

            this.repoProduit.save(produit);
        };
    }
}
