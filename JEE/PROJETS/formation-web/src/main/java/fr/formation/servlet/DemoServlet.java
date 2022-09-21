package fr.formation.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().println("<html>");
//		response.getWriter().println("<body>");
//		response.getWriter().println("<h1>Bonjour le monde !</h1>");
//		response.getWriter().println("</body>");
//		response.getWriter().println("</html>");
		
		// On récupère le paramètre "username"
		String myUsername = request.getParameter("username");
		
		// Pour transférer des infos de la Servlet vers la JSP
		// On utilise le scope request
		request.setAttribute("utilisateur", myUsername);
		
		// Pour afficher la JSP, on va faire de la délégation de requête
		this.getServletContext() // Contexte de Servlet
			.getRequestDispatcher("/WEB-INF/views/demo.jsp") // On récupère le dispatcher de requêtes
			.forward(request, response); // On transfert la requête et la réponse HTTP
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
