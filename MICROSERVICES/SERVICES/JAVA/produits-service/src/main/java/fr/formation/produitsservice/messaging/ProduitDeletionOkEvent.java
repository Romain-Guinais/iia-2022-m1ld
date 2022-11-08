package fr.formation.produitsservice.messaging;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitDeletionOkEvent {
    private int produitId;
}
