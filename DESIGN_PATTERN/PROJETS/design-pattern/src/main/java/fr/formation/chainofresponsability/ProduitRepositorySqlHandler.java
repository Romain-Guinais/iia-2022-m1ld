package fr.formation.chainofresponsability;

import fr.formation.builder.ProduitBuilder;
import fr.formation.model.Produit;

public class ProduitRepositorySqlHandler extends AbstractHandler {
    @Override
	public Produit handle(int id) {
		if (id == 1) {
			return new ProduitBuilder()
                .withId(id)
				.withNom("SQL")
				.build();
		}

		return super.handle(id);
	}
}
