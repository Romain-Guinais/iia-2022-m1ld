package fr.formation.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.formation.model.Produit;

@Repository
public class ProduitRepository {
	@PersistenceContext // On demande à SPRING de nous injecter un EM
	private EntityManager em;
	
	public Optional<Produit> findById(int id) {
		return Optional.ofNullable(this.em.find(Produit.class, id));
	}
	
	public List<Produit> findAll() {
		return this.em
			.createQuery("select p from Produit p", Produit.class)
			.getResultList();
	}
	
	@Transactional
	public void deleteById(int id) {
//		this.em.remove(this.findById(id));
		
		this.em
			.createQuery("delete from Produit p where p.id = ?1")
			.setParameter(1, id)
			.executeUpdate();
	}
	
	@Transactional
	public Produit save(Produit produit) {
//		em.getTransaction().begin();
		
//		try {
			em.persist(produit); // On persiste le produit en base de données
//			em.getTransaction().commit();
//		}
		
//		catch (Exception e) {
//			em.getTransaction().rollback();
//		}
		
		return produit;
	}
}
