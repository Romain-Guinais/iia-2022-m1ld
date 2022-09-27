package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id")
	private int id;

	@Column(name = "cli_nom", length = 50, nullable = false)
	private String nom;
	
	@OneToMany(mappedBy = "client")
	private List<Commande> commandes;
}
