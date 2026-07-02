package keyforge;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyforge.model.Articolo;
import keyforge.model.ArticoloDAO;
import keyforge.model.Comprensione;
import keyforge.model.ComprensioneDAO;
import keyforge.model.Ordine;
import keyforge.model.OrdineDAO;

@WebServlet("/user/ConfirmOrderServlet")
public class ConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();


		Map<Integer, Integer> carrello = (Map<Integer, Integer>)session.getAttribute("carrello");
		if (carrello == null) {
			request.setAttribute("errorMessage", "Il carrello è vuoto");
			request.getRequestDispatcher("/user/riepilogo.jsp");
			return;
		}

		Ordine ordine = (Ordine)session.getAttribute("ordine");

		try {
			ArticoloDAO articoloDAO = new ArticoloDAO();

			for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
				Articolo articolo = articoloDAO.findById(entry.getKey());

				if (entry.getValue() > articolo.getDisponibilita()) {
					request.setAttribute("errorMessage", "L'articolo \"" + articolo.getNome() + "\" non ha sufficiente disponibilità");
					request.getRequestDispatcher("/user/riepilogo.jsp").forward(request, response);
					return;
				}
			}

			ordine.setStato("in_attesa");
			ordine.setTracking(Integer.toString(new Random().nextInt(100_000)));
			ordine.setDataOrdine(Timestamp.from(java.time.Instant.now()));

			OrdineDAO ordineDAO = new OrdineDAO();
			ordineDAO.create(ordine);

			ComprensioneDAO comprensioneDAO = new ComprensioneDAO();


			for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
				Articolo articolo = articoloDAO.findById(entry.getKey());
				articolo.setDisponibilita(articolo.getDisponibilita() - entry.getValue());
				articoloDAO.update(articolo);

				Comprensione comprensione = new Comprensione(ordine.getId(), entry.getKey(), entry.getValue());
				comprensioneDAO.create(comprensione);
			}

			response.sendRedirect(request.getContextPath() + "/user/profilo.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
