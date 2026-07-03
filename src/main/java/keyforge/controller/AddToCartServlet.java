package keyforge.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/common/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			int articoloId = Integer.parseInt(request.getParameter("articoloId"));

			int quantita = Integer.parseInt(request.getParameter("quantita"));
            if (quantita <= 0) {
            	throw new NumberFormatException();
            }

			HttpSession session = request.getSession();

			@SuppressWarnings("unchecked")
			Map<Integer, Integer> carrello = (Map<Integer, Integer>)session.getAttribute("carrello");

			if (carrello == null) {
				carrello = new HashMap<>();
				session.setAttribute("carrello", carrello);
			}

			carrello.merge(articoloId, quantita, Integer::sum);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
	}
}