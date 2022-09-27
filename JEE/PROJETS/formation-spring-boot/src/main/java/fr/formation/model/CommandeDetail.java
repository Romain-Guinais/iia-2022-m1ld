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
@Table(name = "achat")
@Getter @Setter
public class CommandeDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ach_id")
	private int id;
	
	@Column(name = "ach_prix", nullable = false)
	private float prix;
	
	@Column(name = "ach_quantite")
	private int quantite;
	
	@ManyToOne
	@JoinColumn(name = "ach_commande_id", nullable = false)
	private Commande commande;

	@ManyToOne
	@JoinColumn(name = "ach_produit_id", nullable = false)
	private Produit produit;
}
