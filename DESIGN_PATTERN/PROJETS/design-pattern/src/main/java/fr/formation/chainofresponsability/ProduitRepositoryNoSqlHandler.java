package fr.formation.chainofresponsability;

import fr.formation.builder.ProduitBuilder;
import fr.formation.model.Produit;

public class ProduitRepositoryNoSqlHandler extends AbstractHandler {
    @Override
	public Produit handle(int id) {
		if (id == 2) {
			return new ProduitBuilder()
                .withId(id)
				.withNom("NoSQL")
				.build();
		}

		return super.handle(id);
	}
}
