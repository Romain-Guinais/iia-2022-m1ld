package fr.formation.queryservice.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.formation.queryservice.model.Commentaire;

public interface ICommentaireRepository extends MongoRepository<Commentaire, Integer> {
    public List<Commentaire> findByProduitId(int produitId);
}
