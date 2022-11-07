package fr.formation.produitsservice.api.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProduitDetailsResponse {
    private int id;
    private String nom;
    private Double prix;
    private int note;
    private List<CommentaireResponse> commentaires;
}
