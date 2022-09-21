package fr.formation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.getServletContext()
			.getRequestDispatcher("/WEB-INF/views/login.jsp")
			.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// On récupère le paramètre de requête
		String myUsername = req.getParameter("username");
		
		// On récupère la session Http
		HttpSession session = req.getSession();
		
		// On injecte le username dans la session de l'utilisateur
		session.setAttribute("utilisateurSession", myUsername);
		
		// On redirige vers welcome
		resp.sendRedirect("welcome");
	}
}
