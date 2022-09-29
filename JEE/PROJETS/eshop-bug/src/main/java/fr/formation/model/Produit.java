package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produit")
@Getter @Setter
public class Produit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private int id;
    
    @Column(name = "pro_lib", length = 100, nullable = false)
    private String nom;
    
    @Column(name = "pro_prix", nullable = false)
    private float prix;

    @ManyToOne
    @JoinColumn(name = "pro_fournisseur_id")
    private Fournisseur fournisseur;
}
