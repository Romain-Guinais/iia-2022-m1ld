package fr.formation.service;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.formation.exception.EntityNotFoundException;
import fr.formation.model.Fournisseur;

@SpringBootTest
public class FournisseurServiceIntegrationTest {
	@Autowired
	private FournisseurService srvFournisseur;
	
	@Test
	public void shouldReturnFournisseurById() throws Exception {
		Fournisseur fournisseur = this.srvFournisseur.findById(1);
		
		Assertions.assertNotNull(fournisseur);
		Assertions.assertThrows(
			LazyInitializationException.class,
			() -> fournisseur.getProduits().size()
		);
	}
	
	@Test
	public void shouldReturnDetailedFournisseurById() throws Exception {
		Fournisseur fournisseur = this.srvFournisseur.findDetailedById(1);

		Assertions.assertNotNull(this.srvFournisseur.findById(1));
		Assertions.assertEquals(true, fournisseur.getProduits().size() > 0);
	}
	
	@Test
	public void shouldThrowExceptionOnFindById() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> this.srvFournisseur.findById(10));
	}
}
