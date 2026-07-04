package keyforge.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import keyforge.model.Utente;
import keyforge.model.UtenteDAO;

@WebServlet("/user/UpdateProfiloServlet")
public class UpdateProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nome = request.getParameter("nome");
	    String cognome = request.getParameter("cognome");
	    String dataNascita = request.getParameter("data-nascita");
		String email = request.getParameter("email");
	    String telefono = request.getParameter("telefono");

        if (nome == null || cognome == null || dataNascita == null || email == null || telefono == null) {
        	forwardWithError(request, response, "Compilare tutti i campi");
        	return;
        }

        nome = nome.trim();
        cognome = cognome.trim();
        dataNascita = dataNascita.trim();
        email = email.trim().toLowerCase();
        telefono = telefono.trim();

        if (nome.isEmpty() || nome.length() > 100) {
            forwardWithError(request, response, "Nome non valido");
            return;
        }
        if (cognome.isEmpty() || cognome.length() > 100) {
            forwardWithError(request, response, "Cognome non valido");
            return;
        }
        if (!telefono.matches("\\d{10}")) {
            forwardWithError(request, response, "Numero di telefono non valido");
            return;
        }
        if (!email.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-z]{2,}$") || email.length() > 255) {
            forwardWithError(request, response, "Email non valida");
            return;
        }

		int utenteId = (int)request.getSession().getAttribute("utenteId");

		UtenteDAO utenteDAO = new UtenteDAO();

	    try {
	    	Utente utente = utenteDAO.findById(utenteId);

	    	if (!utente.getEmail().equals(email) && UtenteDAO.emailExists(email)) {
	    		forwardWithError(request, response, "Email già esistente");
	    		return;
	    	}
	    	
	    	utente.setNome(nome);
	    	utente.setCognome(cognome);
	    	utente.setDataNascita(Date.valueOf(dataNascita));
	    	utente.setTelefono(telefono);

	    	utenteDAO.update(utente);

	    	request.getSession().setAttribute("successMessage", "Profilo aggiornato con successo");
	    	response.sendRedirect(request.getContextPath() + "/common/registrazione.jsp");
	    } catch (SQLException e) {
			e.printStackTrace();
			forwardWithError(request, response, "Errore durante l'aggiornamento del profilo");
	    }
	}

	private void forwardWithError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("errorMessage", message);
		request.getRequestDispatcher("/common/registrazione.jsp").forward(request, response);
	}
}
