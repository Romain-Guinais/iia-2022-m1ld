package fr.formation.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Fournisseur {
    private int id;
    private String nom;
    private List<Produit> produits;
}
