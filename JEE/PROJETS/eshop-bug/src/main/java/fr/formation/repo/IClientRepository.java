package fr.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Client;

public interface IClientRepository extends JpaRepository<Client, Integer> {
    
}
