package fr.formation.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.formation.model.Produit;

@WebListener
public class ApplicationListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		List<Produit> produits = new ArrayList<>();

		produits.add(new Produit("GoPRO HRO 12"));
		produits.add(new Produit("Parachute de France"));
		produits.add(new Produit("Casque de moto"));
		produits.add(new Produit("Triple Karmelite"));
		
		sce.getServletContext().setAttribute("produits", produits);
	}
	
}
