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

@WebServlet("/admin/CreateArticoloServlet")
public class CreateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/articolo/create.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String brand = request.getParameter("brand");
        BigDecimal prezzo = new BigDecimal(request.getParameter("prezzo"));
        int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
        int dimensione = Integer.parseInt(request.getParameter("dimensione"));
        int peso = Integer.parseInt(request.getParameter("peso"));
        String layout = request.getParameter("layout");

        ArticoloDAO articoloDAO = new ArticoloDAO();

        Articolo articolo = new Articolo();
        articolo.setNome(nome);
        articolo.setDescrizione(descrizione);
        articolo.setBrand(brand);
        articolo.setPrezzo(prezzo);
        articolo.setDisponibilita(disponibilita);
        articolo.setDimensione(dimensione);
        articolo.setPeso(peso);
        articolo.setLayout(layout);

        try {
			articoloDAO.create(articolo);

			request.getSession().setAttribute("successMessage", "Articolo creato con successo");
			response.sendRedirect(request.getContextPath() + "/admin/ViewArticoliServlet");
        } catch (SQLException e) {
        	e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
        }
	}
}
