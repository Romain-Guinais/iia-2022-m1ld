package fr.formation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.formation.exception.InvalidArgsException;
import fr.formation.exception.InvalidEntityException;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;
import fr.formation.repo.IProduitRepository;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceTest {
	@Mock
	private IProduitRepository repoProduit;
	
	@InjectMocks
	private ProduitService srvProduit;
	
	@Test
	public void shouldReturnProduitById() throws Exception {
		Mockito.when(this.repoProduit.findById(1)).thenReturn(Optional.of(new Produit()));
		
		Assertions.assertNotNull(this.srvProduit.findById(1));
		
		Mockito.verify(this.repoProduit, Mockito.times(1)).findById(1);
	}
	
	@Test
	public void shouldThrowExceptionOnFindById() {
		Assertions.assertThrows(InvalidArgsException.class, () -> this.srvProduit.findById(0));
		
		Mockito.verify(this.repoProduit, Mockito.never()).findById(0);
	}
	
	@Test
	public void shouldReturnProduitByNom() throws Exception {
		Mockito.when(this.repoProduit.findByNom(Mockito.anyString())).thenReturn(Optional.of(new Produit()));
		
		Assertions.assertNotNull(this.srvProduit.findByNom("test"));
		
		Mockito.verify(this.repoProduit, Mockito.times(1)).findByNom(Mockito.anyString());
	}
	
	@Test
	public void shouldThrowExceptionOnFindByNom() {
		Assertions.assertThrows(InvalidArgsException.class, () -> this.srvProduit.findByNom(null));
		Assertions.assertThrows(InvalidArgsException.class, () -> this.srvProduit.findByNom(""));
		
		Mockito.verify(this.repoProduit, Mockito.never()).findById(0);
	}
	
	@Test
	public void shouldThrowExceptionOnSaveWhenNullOrEmptyNom() {
		Produit produit = this.generateProduit();
		
		produit.setNom(null);
		Assertions.assertThrows(InvalidEntityException.class, () -> srvProduit.save(produit));
		
		produit.setNom("");
		Assertions.assertThrows(InvalidEntityException.class, () -> srvProduit.save(produit));

		Mockito.verify(this.repoProduit, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	public void shouldThrowExceptionOnSaveWhenWrongPrix() {
		Produit produit = this.generateProduit();
		
		produit.setPrix(-1);
		Assertions.assertThrows(InvalidEntityException.class, () -> srvProduit.save(produit));
		
		Mockito.verify(this.repoProduit, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	public void shouldThrowExceptionOnSaveWhenNullOrEmptyFournisseur() {
		Produit produit = this.generateProduit();
		
		produit.getFournisseur().setId(0);
		Assertions.assertThrows(InvalidEntityException.class, () -> srvProduit.save(produit));
		
		produit.setFournisseur(null);
		Assertions.assertThrows(InvalidEntityException.class, () -> srvProduit.save(produit));
		
		Mockito.verify(this.repoProduit, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	public void shouldSave() {
		Produit produit = this.generateProduit();
		
		Assertions.assertDoesNotThrow(() -> srvProduit.save(produit));

		Mockito.verify(this.repoProduit, Mockito.times(1)).save(Mockito.any());
	}
	
	@Test
	public void shouldReturnList() {
		List<Produit> laListe = new ArrayList<>();
		
		laListe.add(new Produit());
		
		Mockito.when(this.repoProduit.findAll()).thenReturn(laListe);
		Assertions.assertEquals(this.srvProduit.findAll(), laListe);
		Mockito.verify(this.repoProduit, Mockito.times(1)).findAll();
	}
	
	@Test
	public void shouldReturnListIfNull() {
		Mockito.when(this.repoProduit.findAll()).thenReturn(null);
		Assertions.assertNotNull(this.srvProduit.findAll());
		Mockito.verify(this.repoProduit, Mockito.times(1)).findAll();
	}
	
	@Test
	public void shouldDeleteById() throws Exception {
		this.repoProduit.deleteById(1);
		Mockito.verify(this.repoProduit, Mockito.times(1)).deleteById(1);
	}
	
	@Test
	public void shouldThrowExceptionOnDeleteById() {
		Assertions.assertThrows(InvalidArgsException.class, () -> this.srvProduit.deleteById(0));
		
		Mockito.verify(this.repoProduit, Mockito.never()).deleteById(0);
	}
	
	private Produit generateProduit() {
		Produit produit = new Produit();
		
		produit.setNom("un nom");
		produit.setPrix(1);
		
		produit.setFournisseur(new Fournisseur());
		produit.getFournisseur().setId(1);
		
		return produit;
	}
}
