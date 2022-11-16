package fr.formation.queryservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.queryservice.api.response.CommentaireResponse;
import fr.formation.queryservice.exception.EntityNotFoundException;
import fr.formation.queryservice.model.Commentaire;
import fr.formation.queryservice.model.Produit;
import fr.formation.queryservice.repo.ICommentaireRepository;
import fr.formation.queryservice.repo.IProduitRepository;

@RestController
@RequestMapping("/commentaire")
public class CommentaireApiController {
    @Autowired
    private IProduitRepository repoProduit;

    @Autowired
    private ICommentaireRepository repoCommentaire;

    @GetMapping("/{id}")
    public CommentaireResponse findById(@PathVariable int id) {
        Commentaire commentaire = this.repoCommentaire.findById(id).orElseThrow(EntityNotFoundException::new);
        Produit produit = this.repoProduit.findById(commentaire.getProduitId()).orElse(Produit.builder().nom("- aucun -").build());

        return CommentaireResponse.builder()
            .texte(commentaire.getTexte())
            .produitNom(produit.getNom())
            .note(commentaire.getNote())
            .build();
    }
}
