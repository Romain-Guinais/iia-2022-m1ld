package fr.formation.queryservice.messaging;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitCreatedEvent {
    private int produitId;
    private String nom;
    private Double prix;
}
