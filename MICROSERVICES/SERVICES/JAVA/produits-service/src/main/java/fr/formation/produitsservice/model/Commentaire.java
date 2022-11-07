package fr.formation.produitsservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commentaire")
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private int id;

    @Column(name = "com_texte", length = 500, nullable = false)
    private String texte;
    
    @Column(name = "com_note", nullable = false)
    private int note;
    
    @ManyToOne
    @JoinColumn(name = "com_produit_id", nullable = false)
    private Produit produit;
}
