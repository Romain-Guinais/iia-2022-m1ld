package fr.formation.produitsservice.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProduitResponse {
    private int id;
    private String nom;
    private Double prix;
    private int note;
}
