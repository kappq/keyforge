package keyforge.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import keyforge.model.Articolo;
import keyforge.model.ArticoloDAO;

@WebServlet("/admin/DeleteArticoloServlet")
public class DeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articoloId;
		try {
			articoloId = Integer.parseInt(request.getParameter("articolo-id"));
		} catch (NullPointerException | NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		ArticoloDAO articoloDAO = new ArticoloDAO();

		try {
			Articolo articolo = articoloDAO.findById(articoloId);

			if (articolo == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			request.setAttribute("articolo", articolo);
			request.getRequestDispatcher("/admin/articolo/delete.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articoloId;
		try {
			articoloId = Integer.parseInt(request.getParameter("articolo-id"));
		} catch (NullPointerException | NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		ArticoloDAO articoloDAO = new ArticoloDAO();

		try {
			articoloDAO.delete(articoloId);
			
			request.getSession().setAttribute("successMessage", "Articolo cancellato con successo");
			response.sendRedirect(request.getContextPath() + "/admin/ViewArticoliServlet");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}
}
