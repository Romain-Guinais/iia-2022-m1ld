package fr.formation.produitsservice.messaging;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProduitDeletionCommand {
    private int produitId;
}
