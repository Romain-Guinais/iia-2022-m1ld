package fr.formation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.exception.EntityNotDeletedException;
import fr.formation.exception.EntityNotFoundException;
import fr.formation.exception.EntityNotPersistedException;
import fr.formation.exception.InvalidArgsException;
import fr.formation.exception.InvalidEntityException;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;
import fr.formation.repo.IProduitRepository;
import fr.formation.request.ProduitRequest;

@Service
public class ProduitService {
    @Autowired
    private IProduitRepository repoProduit;

    public List<Produit> findAll() {
        return Optional
                .ofNullable(this.repoProduit.findAll())
                .orElse(new ArrayList<>());
    }

    public Produit findById(int id) {
        if (id <= 0) {
            throw new InvalidArgsException();
        }
        
        return this.repoProduit.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Produit findByNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new InvalidArgsException();
        }

        return this.repoProduit.findByNom(nom).orElseThrow(EntityNotFoundException::new);
    }

    public Produit save(ProduitRequest produitRequest) {
        Produit produit = new Produit();

        if (produitRequest.getId() != null) {
            produit = this.findById(produitRequest.getId());
        }
        
        produit.setNom(produitRequest.getNom());
        produit.setPrix(produitRequest.getPrix());
        
        if (produitRequest.getFournisseurId() != null) {
            produit.setFournisseur(new Fournisseur());
            produit.getFournisseur().setId(produitRequest.getFournisseurId());
        }

        return this.save(produit);
    }

    public Produit save(Produit produit) {
        if (produit.getNom() == null || produit.getNom().isBlank()) {
            throw new InvalidEntityException();
        }

        if (produit.getPrix() < 0) {
            throw new InvalidEntityException();
        }

        if (produit.getFournisseur() == null || produit.getFournisseur().getId() == 0) {
            throw new InvalidEntityException();
        }

        try {
            return this.repoProduit.save(produit);
        }

        catch (Exception e) {
            throw new EntityNotPersistedException();
        }
    }

    public void deleteById(int id) {
        if (id <= 0) {
            throw new InvalidArgsException();
        }
        
        try {
            this.repoProduit.deleteById(id);
        }

        catch (Exception e) {
            throw new EntityNotDeletedException();
        }
    }
}
