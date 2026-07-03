package keyforge.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import keyforge.model.Ordine;
import keyforge.model.OrdineDAO;

@WebServlet("/admin/OrdiniServlet")
public class OrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String utenteIdString = request.getParameter("utente-id");
		Integer utenteId = utenteIdString != null && !utenteIdString.isBlank() ? Integer.parseInt(utenteIdString) : 0;
		
        String stato = request.getParameter("stato");
        if (stato == null) {
        	stato = "";
        }

        String dataInizio = request.getParameter("data-inizio");
        if (dataInizio == null) {
        	dataInizio = "1000-01-01 00:00:00";
        }

        String dataFine = request.getParameter("data-fine");
        if (dataFine == null) {
        	dataFine = "9999-12-31 23:59:59";
        }

        try {
            OrdineDAO ordineDAO = new OrdineDAO();
            Collection<Ordine> ordini = ordineDAO.findFiltered(utenteId, stato, dataInizio, dataFine);

            JSONArray arr = new JSONArray(ordini.toArray());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(arr.toString());
        } catch (SQLException e) {
        	e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}
}
