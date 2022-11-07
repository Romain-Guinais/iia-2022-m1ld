package fr.formation.produitsservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.produitsservice.model.Commentaire;
import fr.formation.produitsservice.model.Produit;

public interface ICommentaireRepository extends JpaRepository<Commentaire, Integer> {
    public List<Commentaire> findByProduit(Produit produit);
}
