package fr.formation.produitsservice.messaging;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProduitCreatedCommand {
    private int produitId;
    private String nom;
    private Double prix;
}
