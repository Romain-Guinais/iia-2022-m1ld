package fr.formation.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import fr.formation.model.Fournisseur;

@SpringBootTest
public class FournisseurRepositoryTest {
	@Autowired
	private IFournisseurRepository repoFournisseur;
	
	@Test
	public void testFindAll() {
		List<Fournisseur> fournisseurs = this.repoFournisseur.findAll();
		
		Assertions.assertNotNull(fournisseurs);
		Assertions.assertTrue(fournisseurs.size() > 0);
		Assertions.assertEquals(1, fournisseurs.get(0).getId());
	}
	
	@Test
	@Transactional
	public void testFindById() {
		Optional<Fournisseur> fournisseur = this.repoFournisseur.findById(1);

		Assertions.assertNotNull(fournisseur);
		Assertions.assertTrue(fournisseur.isPresent());
		Assertions.assertEquals(1, fournisseur.get().getId());
		Assertions.assertNotEquals(0, fournisseur.get().getProduits().size());
	}
	
	@Test
	public void testFindByIdFetchingProduits() {
		Optional<Fournisseur> fournisseur = this.repoFournisseur.findByIdFetchingProduits(3);

		Assertions.assertNotNull(fournisseur);
		Assertions.assertTrue(fournisseur.isPresent());
		Assertions.assertEquals(3, fournisseur.get().getId());
		Assertions.assertNotEquals(0, fournisseur.get().getProduits().size());
	}
	
	@Test
	public void shouldAdd() {
		Fournisseur fournisseur = new Fournisseur();
		String randomName = UUID.randomUUID().toString();

		fournisseur.setNom(randomName);
		
		Assertions.assertEquals(0, fournisseur.getId());
		
		this.repoFournisseur.save(fournisseur);

		Assertions.assertNotEquals(0, fournisseur.getId());
	}
	
	@Test
	public void shouldUpdate() {
		Fournisseur fournisseur = this.repoFournisseur.findById(1).get();
		String randomName = UUID.randomUUID().toString();
		
		fournisseur.setNom(randomName);
		this.repoFournisseur.save(fournisseur);
		
		fournisseur = this.repoFournisseur.findById(1).get();
		
		Assertions.assertEquals(randomName, fournisseur.getNom());
	}
	
	@Test
	public void shouldDelete() {
		this.repoFournisseur.deleteById(4);
		
		Optional<Fournisseur> fournisseur = this.repoFournisseur.findById(4);

		Assertions.assertNotNull(fournisseur);
		Assertions.assertTrue(fournisseur.isEmpty());
	}
	
	@Test
	public void shouldThrowOnDelete() {
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> this.repoFournisseur.deleteById(3));
	}
}
