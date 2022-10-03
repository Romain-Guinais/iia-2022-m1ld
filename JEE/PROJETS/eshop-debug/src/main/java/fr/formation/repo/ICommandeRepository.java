package fr.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Commande;

public interface ICommandeRepository extends JpaRepository<Commande, Integer> {
    
}
