package keyforge.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import keyforge.model.UtenteDAO;

@WebServlet("/common/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		email = email.trim().toLowerCase();
		try {
			boolean exists = UtenteDAO.emailExists(email);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			JSONObject obj = new JSONObject();
			obj.put("exists", exists);

			response.getWriter().write(obj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
