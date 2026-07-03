package keyforge.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyforge.model.Ordine;

@WebServlet("/user/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String paese = request.getParameter("paese");
		if (paese == null || paese.isBlank()) {
			session.setAttribute("errorMessage", "Inserire il paese");
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
			return;
		}

		String cap = request.getParameter("cap");
		if (cap == null || cap.isBlank()) {
			session.setAttribute("errorMessage", "Inserire il CAP");
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
			return;
		}

		String via = request.getParameter("via");
		if (via == null || via.isBlank()) {
			session.setAttribute("errorMessage", "Inserire la via");
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
			return;
		}
		
		Ordine ordine = new Ordine();
		ordine.setUtenteId((int)session.getAttribute("utenteId"));
		ordine.setIndirizzoSpedizione(paese + ";" + cap + ";" + via);

		session.setAttribute("ordine", ordine);

		request.getRequestDispatcher("/user/riepilogo.jsp").forward(request, response);
	}
}
