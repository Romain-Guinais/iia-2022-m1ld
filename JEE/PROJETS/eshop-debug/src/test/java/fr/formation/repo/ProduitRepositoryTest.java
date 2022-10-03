package fr.formation.repo;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

@SpringBootTest
public class ProduitRepositoryTest {
	@Autowired
	private IProduitRepository repoProduit;
	
	@Test
	public void testFindAll() {
		List<Produit> produits = this.repoProduit.findAll();
		
		Assertions.assertNotNull(produits);
		Assertions.assertTrue(produits.size() > 0);
		Assertions.assertEquals(1, produits.get(0).getId());
	}
	
	@Test
	public void testFindById() {
		Optional<Produit> produit = this.repoProduit.findById(1);

		Assertions.assertNotNull(produit);
		Assertions.assertTrue(produit.isPresent());
		Assertions.assertEquals(1, produit.get().getId());
	}
	
	@Test
	public void testFindByNom() {
		Optional<Produit> optProduit = this.repoProduit.findByNom("P1");

		Assertions.assertNotNull(optProduit);
		Assertions.assertTrue(optProduit.isPresent());
		Assertions.assertEquals(2, optProduit.get().getId());
	}
	
	@Test
	public void testFindByPrixBetween() {
		List<Produit> produits = this.repoProduit.findByPriceBetween(50, 250);

		Assertions.assertNotNull(produits);
		Assertions.assertEquals(2, produits.size());
		Assertions.assertEquals(1, produits.get(0).getId());
	}
	
	@Test
	public void shouldAdd() {
		Produit produit = generateProduit();
		String produitNom = produit.getNom();
		
		Assertions.assertEquals(0, produit.getId());
		
		this.repoProduit.save(produit);

		Assertions.assertNotEquals(0, produit.getId());

		produit = this.repoProduit.findById(produit.getId()).get();
		Assertions.assertEquals(produitNom, produit.getNom());
	}
	
	@Test
	public void shouldUpdate() {
		Produit produit = this.repoProduit.findById(1).get();
		String produitNom = produit.getNom();
		
		produit.setNom("new nom");
		
		this.repoProduit.save(produit);
		
		produit = this.repoProduit.findById(1).get();
		
		Assertions.assertNotEquals(produitNom, produit.getNom());
	}
	
	@Test
	public void shouldDelete() {
		this.repoProduit.deleteById(3);
		
		Optional<Produit> produit = this.repoProduit.findById(3);

		Assertions.assertNotNull(produit);
		Assertions.assertTrue(produit.isEmpty());
	}
	
	private static Produit generateProduit() {
		Produit produit = new Produit();
		
		generateDataForProduit(produit);
		return produit;
	}
	
	private static void generateDataForProduit(Produit produit) {
		Random r = new Random();
		
		produit.setNom(UUID.randomUUID().toString());
		produit.setFournisseur(new Fournisseur());
		produit.getFournisseur().setId(1);
		produit.setPrix(r.nextFloat() * 1000 + 500);
	}
}
