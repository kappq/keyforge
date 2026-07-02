package keyforge;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import keyforge.model.Utente;
import keyforge.model.UtenteDAO;

@WebServlet("/common/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if (email == null || email.isBlank()) {
			request.setAttribute("errorMessage", "Inserire l'email");
			request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			return;
		}

		String password = request.getParameter("password");
		if (password == null || password.isBlank()) {
			request.setAttribute("errorMessage", "Inserire la password");
			request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			return;
		}

		try {
			Utente utente = login(email, password);
			if (utente == null) {
				request.setAttribute("errorMessage", "Credenziali errate");
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
				return;
			}

			request.getSession().setAttribute("utenteId", utente.getId());

			response.sendRedirect(request.getContextPath() + "/user/profilo.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}
	
	private Utente login(String email, String password) throws SQLException {
		UtenteDAO utenteDAO = new UtenteDAO();
		Utente utente = utenteDAO.findByEmail(email);
		return utente != null && BCrypt.checkpw(password, utente.getPassword()) ? utente : null;
	}
}
