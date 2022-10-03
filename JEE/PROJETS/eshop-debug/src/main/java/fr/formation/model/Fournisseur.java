package fr.formation.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fournisseur")
@PrimaryKeyJoinColumn(name = "fou_id")
@Getter @Setter
public class Fournisseur extends Personne {
    @OneToMany(mappedBy = "fournisseur")
    private List<Produit> produits;
}
