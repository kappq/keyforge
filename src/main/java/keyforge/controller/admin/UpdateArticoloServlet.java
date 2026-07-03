package keyforge.controller.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import keyforge.model.Articolo;
import keyforge.model.ArticoloDAO;

@WebServlet("/admin/UpdateArticoloServlet")
public class UpdateArticoloServlet extends HttpServlet {
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
			request.getRequestDispatcher("/admin/articolo/update.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String brand = request.getParameter("brand");
        BigDecimal prezzo = new BigDecimal(request.getParameter("prezzo"));
        int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
        int dimensione = Integer.parseInt(request.getParameter("dimensione"));
        int peso = Integer.parseInt(request.getParameter("peso"));
        String layout = request.getParameter("layout");

        ArticoloDAO articoloDAO = new ArticoloDAO();
        Articolo articolo = new Articolo(id, nome, descrizione, brand, prezzo, disponibilita, dimensione, peso, layout);

        try {
			articoloDAO.update(articolo);

			request.getSession().setAttribute("successMessage", "Articolo modificato con successo");
			response.sendRedirect(request.getContextPath() + "/admin/ViewArticoliServlet");
        } catch (SQLException e) {
        	e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
        }
	}
}
