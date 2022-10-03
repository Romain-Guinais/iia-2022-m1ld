package fr.formation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Produit;

public interface IProduitRepository extends JpaRepository<Produit, Integer> {
    @Query("select p from Produit p where p.pro_nom = ?")
    public Optional<Produit> findByNom(String nom);

    public List<Produit> findByPriceBetween(float start, float end);
}
