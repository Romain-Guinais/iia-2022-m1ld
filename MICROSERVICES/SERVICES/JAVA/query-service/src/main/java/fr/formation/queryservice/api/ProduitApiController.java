package fr.formation.queryservice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.queryservice.api.response.ProduitCommentaireResponse;
import fr.formation.queryservice.api.response.ProduitDetailsResponse;
import fr.formation.queryservice.api.response.ProduitResponse;
import fr.formation.queryservice.exception.EntityNotFoundException;
import fr.formation.queryservice.model.Produit;
import fr.formation.queryservice.repo.ICommentaireRepository;
import fr.formation.queryservice.repo.IProduitRepository;

@RestController
@RequestMapping("/produit")
public class ProduitApiController {
    @Autowired
    private IProduitRepository repoProduit;

    @Autowired
    private ICommentaireRepository repoCommentaire;

    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.repoProduit
            .findAll()
            .stream()
            .map(p -> ProduitResponse.builder()
                .id(p.getId())
                .nom(p.getNom())
                .prix(p.getPrix())
                .note(p.getNote())
                .build()
            )
            .toList();
    }

    @GetMapping("/{id}")
    public ProduitDetailsResponse findById(@PathVariable int id) {
        Produit produit = this.repoProduit.findById(id).orElseThrow(EntityNotFoundException::new);

        return ProduitDetailsResponse.builder()
            .id(produit.getId())
            .nom(produit.getNom())
            .prix(produit.getPrix())
            .note(produit.getNote())
            .commentaires(
                this.repoCommentaire.findByProduitId(id)
                .stream()
                .map(c -> ProduitCommentaireResponse.builder()
                    .texte(c.getTexte())
                    .note(c.getNote())
                    .build())
                .toList()
            )
            .build();
    }
}
