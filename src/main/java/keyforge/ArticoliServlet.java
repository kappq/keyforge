package keyforge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import keyforge.model.*;

@WebServlet("/common/ArticoliServlet")
public class ArticoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		if (nome == null) {
			nome = "";
		}

		String prezzoMinString = request.getParameter("prezzo-min");
		int prezzoMin = prezzoMinString != null ? Integer.parseInt(prezzoMinString) : 0;

		String prezzoMaxString = request.getParameter("prezzo-max");
		int prezzoMax = prezzoMaxString != null ? Integer.parseInt(prezzoMaxString) : Integer.MAX_VALUE;
		
		String disponibilitaString = request.getParameter("disponibilita");
		int disponibilita = disponibilitaString != null ? Integer.parseInt(disponibilitaString) : 0;
		
		try {
			ArticoloDAO articoloDAO = new ArticoloDAO();
			Collection<Articolo> articoli = articoloDAO.findFiltered(nome, prezzoMin, prezzoMax, disponibilita);

			JSONArray arr = new JSONArray(articoli.toArray());

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			out.write(arr.toString());
			out.flush();
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}
}
