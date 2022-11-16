package fr.formation.queryservice.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class CommentaireResponse {
    private int note;
    private String produitNom;
    private String texte;
}
