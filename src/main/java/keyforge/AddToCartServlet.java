package keyforge;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyforge.model.*;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/common/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articoloId;
        int quantita;

        try {
            articoloId = Integer.parseInt(request.getParameter("articoloId"));
            quantita   = Integer.parseInt(request.getParameter("quantita"));
            if (quantita <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getHeader("Referer"));
            return;
        }

        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> carrello =
            (Map<Integer, Integer>) session.getAttribute("carrello");

        if (carrello == null) {
            carrello = new HashMap<>();
            session.setAttribute("carrello", carrello);
        }

        carrello.merge(articoloId, quantita, Integer::sum);
        System.out.println(carrello);
	}
}