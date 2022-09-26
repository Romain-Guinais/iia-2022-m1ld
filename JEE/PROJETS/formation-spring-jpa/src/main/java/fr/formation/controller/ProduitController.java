package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;

@Controller
@RequestMapping("/produit")
public class ProduitController {
	@Autowired
	private ProduitRepository repoProduit;
	
	@GetMapping("/test")
	public String test() {
		Fournisseur monFournisseur = new Fournisseur();
		Produit monProduit = new Produit();
		
		monProduit.setNom("TEST");
		monProduit.setPrix(10);
		monProduit.setFournisseur(monFournisseur);
		
		monFournisseur.setNom("F1");
		
		this.repoProduit.save(monProduit);
		
		return "welcome";
	}
}
