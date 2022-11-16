package fr.formation.produitsservice.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import fr.formation.produitsservice.api.request.ProduitRequest;
import fr.formation.produitsservice.exception.EntityNotFoundException;
import fr.formation.produitsservice.messaging.ProduitCreatedCommand;
import fr.formation.produitsservice.model.Produit;
import fr.formation.produitsservice.repo.IProduitRepository;

@Service
public class ProduitService {
    @Autowired
    private IProduitRepository repoProduit;
    
    @Autowired
    private StreamBridge streamBridge;

    public List<Produit> findAll() {
        return this.repoProduit.findAll();
    }

    public Produit findById(int id) {
        return this.repoProduit.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public int add(ProduitRequest produitRequest) {
        Produit produit = new Produit();

        BeanUtils.copyProperties(produitRequest, produit);

        this.repoProduit.save(produit);
        this.streamBridge.send("produit-created-out-0",
            ProduitCreatedCommand.builder()
                .produitId(produit.getId())
                .nom(produit.getNom())
                .prix(produit.getPrix())
                .build()
        );

        return produit.getId();
    }

    public int edit(int id, ProduitRequest produitRequest) {
        Produit produit = this.findById(id);

        BeanUtils.copyProperties(produitRequest, produit);

        this.repoProduit.save(produit);

        return produit.getId();
    }

    public boolean deleteById(int id) {
        try {
            this.repoProduit.deleteById(id);
            return true;
        }

        catch (Exception e) {
            return false;
        }
    }
}
