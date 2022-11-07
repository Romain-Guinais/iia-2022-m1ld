package fr.formation.produitsservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produit")
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private int id;

    @Column(name = "pro_nom", length = 150, nullable = false)
    private String nom;
    
    @Column(name = "pro_prix", nullable = false)
    private Double prix;
    
    @Column(name = "pro_notable", nullable = false)
    private boolean notable;
}
