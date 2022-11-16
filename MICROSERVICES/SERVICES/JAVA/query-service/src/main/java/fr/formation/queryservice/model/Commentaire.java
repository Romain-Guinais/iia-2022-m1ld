package fr.formation.queryservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("commentaire")
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Commentaire {
    @Id
    private int id;
    private String texte;
    private int produitId;
    private int noteQualite;
    private int noteRapport;
    private int noteFacilite;

    public int getNote() {
        return Math.round((this.noteQualite + this.noteRapport + this.noteFacilite) / 3f);
    }
}