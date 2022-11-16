package fr.formation.queryservice.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ProduitResponse {
    private int id;
    private String nom;
    private Double prix;
    private int note;
}
