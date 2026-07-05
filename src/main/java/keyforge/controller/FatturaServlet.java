package keyforge.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyforge.model.*;

@WebServlet("/user/FatturaServlet")
public class FatturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer utenteId = (Integer) session.getAttribute("utenteId");
		if (utenteId == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		int ordineId = Integer.parseInt(request.getParameter("ordine-id"));

		try {
			OrdineDAO ordineDAO = new OrdineDAO();
			ComprensioneDAO comprensioneDAO = new ComprensioneDAO();
			UtenteDAO utenteDAO = new UtenteDAO();

			Ordine ordine = ordineDAO.findById(ordineId);
			if (ordine == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			if (ordine.getUtenteId() != utenteId) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}

			Utente utente = utenteDAO.findById(utenteId);
			Collection<Comprensione> righe = comprensioneDAO.findByOrdineId(ordineId);

			BigDecimal totale = BigDecimal.ZERO;
			for (Comprensione r : righe) {
				BigDecimal subtotale = r.getPrezzoUnitario().multiply(BigDecimal.valueOf(r.getQuantita()));
				BigDecimal iva = subtotale.multiply(r.getIva()).divide(BigDecimal.valueOf(100));
				totale = totale.add(subtotale).add(iva);
			}

			request.setAttribute("ordine", ordine);
			request.setAttribute("utente", utente);
			request.setAttribute("righe", righe);
			request.setAttribute("totale", totale);

			request.getRequestDispatcher("/user/fattura.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
