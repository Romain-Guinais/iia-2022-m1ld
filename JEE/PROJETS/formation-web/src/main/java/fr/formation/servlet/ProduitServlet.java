package fr.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.model.Produit;

@WebServlet("/produit")
public class ProduitServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Produit> produits = new ArrayList<>();

		produits.add(new Produit("GoPRO HRO 11"));
		produits.add(new Produit("Parachute de France"));
		produits.add(new Produit("Casque de moto"));
		produits.add(new Produit("Triple Karmelite"));
		
		req.setAttribute("produits", produits);
		
		this.getServletContext()
			.getRequestDispatcher("/WEB-INF/views/produits.jsp")
			.forward(req, resp);
	}
}
