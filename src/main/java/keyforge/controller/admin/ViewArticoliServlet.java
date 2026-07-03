package keyforge.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import keyforge.model.Articolo;
import keyforge.model.ArticoloDAO;

@WebServlet("/admin/ViewArticoliServlet")
public class ViewArticoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticoloDAO articoloDAO = new ArticoloDAO();
		
		try {
			Collection<Articolo> articoli = articoloDAO.findAll();
			
			request.setAttribute("articoli", articoli);
			request.getRequestDispatcher("/admin/articolo/view.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}
}
