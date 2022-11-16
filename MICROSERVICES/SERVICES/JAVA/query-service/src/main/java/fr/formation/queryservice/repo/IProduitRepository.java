package fr.formation.queryservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.formation.queryservice.model.Produit;

public interface IProduitRepository extends MongoRepository<Produit, Integer> {

}
