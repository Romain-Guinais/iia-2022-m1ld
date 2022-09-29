package fr.formation.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitRequest {
    private Integer id;
    private String nom;
    private float prix;
    private Integer fournisseurId;
}
