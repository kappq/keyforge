package keyforge;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import keyforge.model.*;

@WebServlet("/common/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = request.getParameter("nome");
	    String surname = request.getParameter("cognome");
	    String bornDate = request.getParameter("dataNascita");
		String email = request.getParameter("email");
	    String phone = request.getParameter("telefono");
	    String password = request.getParameter("password");

        if (name == null || surname == null || email == null || password == null) {
        	forwardWithError(request, response, "Compilare tutti i campi");
        	return;
        }

        name = name.trim();
        surname = surname.trim();
        bornDate = bornDate.trim();
        email = email.trim().toLowerCase();
        phone = phone.trim();

        if (name.isEmpty() || name.length() > 100) {
            forwardWithError(request, response, "Nome non valido");
            return;
        }
        if (surname.isEmpty() || surname.length() > 100) {
            forwardWithError(request, response, "Cognome non valido");
            return;
        }
        if (!phone.matches("\\d{10}")) {
            forwardWithError(request, response, "Numero di telefono non valido");
            return;
        }
        if (!email.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-z]{2,}$") || email.length() > 255) {
            forwardWithError(request, response, "Email non valida");
            return;
        }
        if (password.length() < 8 || password.length() > 255) {
            forwardWithError(request, response, "Password non valida (min. 8 caratteri)");
            return;
        }

	    try {
	    	boolean exists = UtenteDAO.emailExists(email);
	    	if (exists) {
	    		forwardWithError(request, response, "Email già esistente");
	    		return;
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }

	    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	    try {
	    	Date date = Date.valueOf(bornDate);
	    	Utente utente = new Utente(0, email, name, surname, date, hashedPassword, phone);
	    	UtenteDAO utenteDAO = new UtenteDAO();
	    	utenteDAO.create(utente);

	    	Carrello carrello = new Carrello(0, utente.getId());
	    	CarrelloDAO carrelloDAO = new CarrelloDAO();
	    	carrelloDAO.create(carrello);

	    	response.sendRedirect(request.getContextPath() + "/common/registrazione.jsp");
	    } catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore durante la registrazione.");
			request.getRequestDispatcher("/common/registrazione.jsp").forward(request, response);
	    }
	}

	private void forwardWithError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("errorMessage", message);
		request.getRequestDispatcher("/common/registrazione.jsp").forward(request, response);
	}
}
