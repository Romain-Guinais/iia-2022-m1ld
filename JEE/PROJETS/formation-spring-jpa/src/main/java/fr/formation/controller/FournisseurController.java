package fr.formation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.model.Fournisseur;
import fr.formation.repo.IFournisseurRepository;
import fr.formation.request.FournisseurRequest;

@Controller
@RequestMapping("/fournisseur")
public class FournisseurController {
	@Autowired
	private IFournisseurRepository repoFournisseur;
	
	@GetMapping
	public String findAll(Model model) {
		model.addAttribute("fournisseurs", this.repoFournisseur.findAll());
		
		return "fournisseur/list";
	}
	
	@GetMapping("/ajouter")
	public String add() {
		return "fournisseur/edit";
	}
	
	@PostMapping("/ajouter")
	public String add(FournisseurRequest requestFournisseur) {
		Fournisseur fournisseur = new Fournisseur();
		
		fournisseur.setNom(requestFournisseur.getNom());
		
		this.repoFournisseur.save(fournisseur);

		return "redirect:/fournisseur";
	}
	
	@GetMapping("/modifier")
	public String edit(@RequestParam int id, Model model) {
		Optional<Fournisseur> optFournisseur = this.repoFournisseur.findById(id);
		
		if (optFournisseur.isPresent()) {
			model.addAttribute("fournisseur", optFournisseur.get());
			return "fournisseur/edit";
		}
		
		return "redirect:/fournisseur";
	}
	
	@PostMapping("/modifier")
	public String edit(@RequestParam int id, FournisseurRequest requestFournisseur) {
		Optional<Fournisseur> optFournisseur = this.repoFournisseur.findById(id);
		
		if (optFournisseur.isPresent()) {
			optFournisseur.get().setNom(requestFournisseur.getNom());
			this.repoFournisseur.save(optFournisseur.get());
		}
		
		return "redirect:/fournisseur";
	}
	
	@GetMapping("/supprimer")
	public String deleteById(@RequestParam int id) {
		this.repoFournisseur.deleteById(id);
		
		return "redirect:/fournisseur";
	}
}
