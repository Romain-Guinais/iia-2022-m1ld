package fr.formation.queryservice.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ProduitCommentaireResponse {
    private int note;
    private String texte;
}
