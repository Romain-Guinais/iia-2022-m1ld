package fr.formation.produitsservice.api;

import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;

import fr.formation.produitsservice.api.request.ProduitRequest;
import fr.formation.produitsservice.api.response.CommentaireResponse;
import fr.formation.produitsservice.api.response.ProduitDetailsResponse;
import fr.formation.produitsservice.api.response.ProduitResponse;
import fr.formation.produitsservice.model.Produit;
import fr.formation.produitsservice.service.ProduitService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProduitApiController {
    private final ProduitService srvProduit;
    private final RestTemplate restTemplate;

    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.srvProduit
            .findAll()
            .stream()
            .map(p -> {
                Integer note = this.restTemplate.getForObject("http://localhost:5156/note/" + p.getId(), Integer.class);
                
                return ProduitResponse.builder()
                    .id(p.getId())
                    .nom(p.getNom())
                    .prix(p.getPrix())
                    .note(note)
                    .build();
            })
            .toList();
    }

    @GetMapping("/{id}")
    public ProduitDetailsResponse findById(@PathVariable int id) {
        Produit produit = this.srvProduit.findById(id);
        CommentaireResponse[] commentaires = this.restTemplate.getForObject("http://localhost:5156/by-produit/" + id, CommentaireResponse[].class);
        Integer note = this.restTemplate.getForObject("http://localhost:5156/note/" + id, Integer.class);
        
        return ProduitDetailsResponse.builder()
            .id(produit.getId())
            .nom(produit.getNom())
            .prix(produit.getPrix())
            .commentaires(Arrays.asList(commentaires))
            .note(note)
            .build();
    }

    @GetMapping("/info/{id}/notable")
    public boolean notableById(@PathVariable int id) {
        Produit produit = this.srvProduit.findById(id);
        return produit.isNotable();
    }

    @GetMapping("/info/{id}/nom")
    public String nomById(@PathVariable int id) {
        Produit produit = this.srvProduit.findById(id);
        return produit.getNom();
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
        CommentaireResponse[] commentaires = this.restTemplate.getForObject("http://localhost:5156/by-produit/" + id, CommentaireResponse[].class);
        
        if (commentaires != null && commentaires.length == 0) {
            return this.srvProduit.deleteById(id);
        }

        return false;
    }
}