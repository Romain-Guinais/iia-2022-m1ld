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
import fr.formation.repo.IFournisseurRepository;
import fr.formation.request.FournisseurRequest;

@Service
public class FournisseurService {
    @Autowired
    private IFournisseurRepository repoFournisseur;

    public List<Fournisseur> findAll() {
        return Optional
                .ofNullable(this.repoFournisseur.findAll())
                .orElse(new ArrayList<>());
    }

    public Fournisseur findById(int id) {
        if (id <= 0) {
            throw new InvalidArgsException();
        }

        return this.repoFournisseur.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Fournisseur findDetailedById(int id) {
        if (id <= 0) {
            throw new InvalidArgsException();
        }

        return this.repoFournisseur.findByIdFetchingProduits(id).orElseThrow(EntityNotFoundException::new);
    }

    public Fournisseur save(FournisseurRequest fournisseurRequest) {
        Fournisseur fournisseur = new Fournisseur();

        if (fournisseurRequest.getId() != null) {
            fournisseur.setId(fournisseurRequest.getId());
        }

        fournisseur.setNom(fournisseurRequest.getNom());

        return this.save(fournisseur);
    }

    public Fournisseur save(Fournisseur fournisseur) {
        if (fournisseur.getNom() == null || fournisseur.getNom().isBlank()) {
            throw new InvalidEntityException();
        }

        try {
            return this.repoFournisseur.save(fournisseur);
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
            this.repoFournisseur.deleteById(id);
        }

        catch (Exception e) {
            throw new EntityNotDeletedException();
        }
    }
}
