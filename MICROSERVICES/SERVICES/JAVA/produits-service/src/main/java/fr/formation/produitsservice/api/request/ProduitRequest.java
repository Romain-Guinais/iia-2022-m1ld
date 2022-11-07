package fr.formation.produitsservice.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitRequest {
    private String nom;
    private Double prix;
    private Boolean notable;
}
