package fr.formation.queryservice.messaging;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentaireCreatedEvent {
    private int commentaireId;
    private String texte;
    private int noteQualite;
    private int noteRapport;
    private int noteFacilite;
    private int produitId;
}
