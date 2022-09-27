package fr.formation.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity // Classe d'entité
@Table(name = "[produit]") // Les [] pour envelopper le nom de la table, et éviter les pb liés aux mot-clés par exemple
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id")
	private int id;
	
	@Column(name = "pro_nom", length = 100, nullable = false)
	private String nom;
	
	@Column(name = "pro_prix", nullable = false)
	private float prix;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "pro_fournisseur_id", nullable = false)
	private Fournisseur fournisseur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Produit() { }

	public Produit(String nom) {
		this.nom = nom;
	}
}
