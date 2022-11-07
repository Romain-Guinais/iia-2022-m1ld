package fr.formation.produitsservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.produitsservice.model.Produit;

public interface IProduitRepository extends JpaRepository<Produit, Integer> {
    
}
