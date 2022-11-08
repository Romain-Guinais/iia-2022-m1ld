package fr.formation.produitsservice.messaging;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProduitDetailedCommand {
    private int commentaireId;
    private int produitId;
    private boolean notable;
}
