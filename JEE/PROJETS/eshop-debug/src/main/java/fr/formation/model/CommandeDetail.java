package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commande_detail")
@Getter @Setter
public class CommandeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cdet_id")
    private int id;
    
    @Column(name = "cdet_date", nullable = false)
    private float prix;
    
    @Column(name = "cdet_quantite", nullable = false)
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "cdet_commande_id", nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "cdet_produit_id", nullable = false)
    private Produit produit;
}
