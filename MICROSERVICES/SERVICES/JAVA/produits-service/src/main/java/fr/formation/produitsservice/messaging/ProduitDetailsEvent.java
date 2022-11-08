package fr.formation.produitsservice.messaging;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitDetailsEvent {
    private int commentaireId;
    private int produitId;
}
