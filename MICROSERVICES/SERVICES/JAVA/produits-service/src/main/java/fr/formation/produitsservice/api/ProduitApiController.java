package fr.formation.produitsservice.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.produitsservice.api.request.ProduitRequest;
import fr.formation.produitsservice.api.response.CommentaireResponse;
import fr.formation.produitsservice.api.response.ProduitDetailsResponse;
import fr.formation.produitsservice.api.response.ProduitResponse;
import fr.formation.produitsservice.model.Commentaire;
import fr.formation.produitsservice.model.Produit;
import fr.formation.produitsservice.service.ProduitService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProduitApiController {
    private final ProduitService srvProduit;

    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.srvProduit
            .findAll()
            .stream()
            .map(p -> ProduitResponse.builder()
                .id(p.getId())
                .nom(p.getNom())
                .prix(p.getPrix())
                .note(this.srvProduit.getNote(p))
                .build()
            )
            .toList();
    }

    @GetMapping("/{id}")
    public ProduitDetailsResponse findById(@PathVariable int id) {
        Produit produit = this.srvProduit.findById(id);
        List<Commentaire> commentaires = this.srvProduit.findAllByProduit(produit);

        return ProduitDetailsResponse.builder()
            .id(produit.getId())
            .nom(produit.getNom())
            .prix(produit.getPrix())
            .commentaires(
                commentaires.stream().map(c -> CommentaireResponse
                    .builder()
                    .texte(c.getTexte())
                    .note(c.getNote())
                    .build()
                ).toList()
            )
            .note(this.srvProduit.getNote(produit))
            .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int add(@RequestBody ProduitRequest produitRequest) {
        return this.srvProduit.add(produitRequest);
    }

    @PutMapping("/{id}")
    public int edit(@PathVariable int id, @RequestBody ProduitRequest produitRequest) {
        return this.srvProduit.edit(id, produitRequest);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable int id) {
        return this.srvProduit.deleteById(id);
    }
}
