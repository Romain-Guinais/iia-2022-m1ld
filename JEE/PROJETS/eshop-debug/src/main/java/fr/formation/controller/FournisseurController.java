package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.request.FournisseurRequest;
import fr.formation.service.FournisseurService;

@Controller
@RequestMapping("/fournisseur")
public class FournisseurController {
    private static final String REDIRECT_URL = "redirect:/fournisseur";

    @Autowired
    private FournisseurService srvFournisseur;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("fournisseurs", this.srvFournisseur.findAll());
        
        return "fournisseur/list";
    }

    @GetMapping("/details")
    public String findById(@RequestParam int id, Model model) {
        model.addAttribute("fournisseur", this.srvFournisseur.findDetailedById(id));
        
        return "fournisseur/details";
    }

    @GetMapping("/ajouter")
    public String add() {
        return "fournisseur/edit";
    }

    @GetMapping("/modifier")
    public String edit(@RequestParam int id, Model model) {
        model.addAttribute("fournisseur", this.srvFournisseur.findById(id));

        return "fournisseur/edit";
    }

    @PostMapping({ "/ajouter", "/modifier" })
    public String save(FournisseurRequest fournisseurRequest) {
        this.srvFournisseur.save(fournisseurRequest);

        return REDIRECT_URL;
    }

    @GetMapping("/supprimer")
    public String deleteById(@RequestParam int id) {
        this.srvFournisseur.deleteById(id);

        return REDIRECT_URL;
    }

    @ModelAttribute("pageActive")
    public String getPageActive() {
        return "fournisseur";
    }
}
