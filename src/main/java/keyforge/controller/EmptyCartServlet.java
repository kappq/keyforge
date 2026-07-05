package keyforge.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/common/EmptyCartServlet")
public class EmptyCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("carrello");		request.getSession().setAttribute("successMessage", "Carrello svuotato");
		response.sendRedirect(request.getContextPath() + "/common/carrello.jsp");
	}
}