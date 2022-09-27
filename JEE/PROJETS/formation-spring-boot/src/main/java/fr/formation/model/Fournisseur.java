package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fournisseur")
@Getter @Setter
public class Fournisseur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fou_id")
	private int id;
	
	@Column(name = "fou_nom", length = 100, nullable = false)
	private String nom;
	
	@OneToMany(mappedBy = "fournisseur")
	private List<Produit> produits;
}
