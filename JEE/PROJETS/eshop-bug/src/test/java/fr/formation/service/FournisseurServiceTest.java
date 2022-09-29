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
import fr.formation.repo.IFournisseurRepository;

@ExtendWith(MockitoExtension.class)
public class FournisseurServiceTest {
	@Mock
	private IFournisseurRepository repoFournisseur;
	
	@InjectMocks
	private FournisseurService srvFournisseur;
	
	@Test
	public void shouldReturnFournisseurById() throws Exception {
		Mockito.when(this.repoFournisseur.findById(1)).thenReturn(Optional.of(new Fournisseur()));
		
		Assertions.assertNotNull(this.srvFournisseur.findById(1));
		
		Mockito.verify(this.repoFournisseur, Mockito.times(1)).findById(1);
	}
	
	@Test
	public void shouldThrowExceptionOnFindById() {
		Assertions.assertThrows(InvalidArgsException.class, () -> this.srvFournisseur.findById(0));
		
		Mockito.verify(this.repoFournisseur, Mockito.never()).findById(0);
	}
	
	@Test
	public void shouldThrowExceptionOnDetailedFindById() {
		Assertions.assertThrows(InvalidArgsException.class, () -> this.srvFournisseur.findDetailedById(0));
		
		Mockito.verify(this.repoFournisseur, Mockito.never()).findById(0);
	}
	
	@Test
	public void shouldThrowExceptionOnSaveWhenNullOrEmptyNom() {
		Fournisseur fournisseur = this.generateFournisseur();
		
		fournisseur.setNom(null);
		Assertions.assertThrows(InvalidEntityException.class, () -> srvFournisseur.save(fournisseur));
		
		fournisseur.setNom("");
		Assertions.assertThrows(InvalidEntityException.class, () -> srvFournisseur.save(fournisseur));

		Mockito.verify(this.repoFournisseur, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	public void shouldSave() {
		Fournisseur produit = this.generateFournisseur();
		
		Assertions.assertDoesNotThrow(() -> srvFournisseur.save(produit));

		Mockito.verify(this.repoFournisseur, Mockito.times(1)).save(Mockito.any());
	}
	
	@Test
	public void shouldReturnList() {
		List<Fournisseur> laListe = new ArrayList<>();
		
		laListe.add(new Fournisseur());
		
		Mockito.when(this.repoFournisseur.findAll()).thenReturn(laListe);
		Assertions.assertEquals(this.srvFournisseur.findAll(), laListe);
		Mockito.verify(this.repoFournisseur, Mockito.times(1)).findAll();
	}
	
	@Test
	public void shouldReturnListIfNull() {
		Mockito.when(this.repoFournisseur.findAll()).thenReturn(null);
		Assertions.assertNotNull(this.srvFournisseur.findAll());
		Mockito.verify(this.repoFournisseur, Mockito.times(1)).findAll();
	}
	
	@Test
	public void shouldDeleteById() throws Exception {
		this.repoFournisseur.deleteById(1);
		Mockito.verify(this.repoFournisseur, Mockito.times(1)).deleteById(1);
	}
	
	@Test
	public void shouldThrowExceptionOnDeleteById() {
		Assertions.assertThrows(InvalidArgsException.class, () -> this.srvFournisseur.deleteById(0));
		
		Mockito.verify(this.repoFournisseur, Mockito.never()).deleteById(0);
	}
	
	private Fournisseur generateFournisseur() {
		Fournisseur fournisseur = new Fournisseur();
		
		fournisseur.setNom("un nom");
		
		return fournisseur;
	}
}
