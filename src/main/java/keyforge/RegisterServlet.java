package keyforge;
import keyforge.model.*;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// -- Lettura parametri --
	    String name = request.getParameter("nome");
	    String surname = request.getParameter("cognome");
	    String bornDate = request.getParameter("dataNascita");
		String email = request.getParameter("email");
	    String phone = request.getParameter("telefono");
	    String password = request.getParameter("password");
	    // -- Eccezioni size -- 
	    if(name == null || name.length() > 100 ) {
	    	throw new IllegalArgumentException("Invalid name");
	    }
	    if(surname == null || surname.length() > 100 ) {
	    	throw new IllegalArgumentException("Invalid surname");
	    }
	    if(phone == null || phone.length() != 10 ) {
	    	throw new IllegalArgumentException("Invalid phone");
	    }
	    if(email == null || email.length() > 255) {
	    	throw new IllegalArgumentException("Invalid email");
	    }
	    if(password == null || password.length() > 255 || password.length() < 8) {
	    	throw new IllegalArgumentException("Invalid password");
	    }
	    boolean exists = false;
	    // -- trim fields -- 
        name     = name.trim();
        surname  = surname.trim();
        bornDate = bornDate.trim();
        email    = email.trim().toLowerCase();
        phone    = phone.trim();
	    try {
	    	exists = UtenteDAO.emailExists(email);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	    try {
	    	Date date = Date.valueOf(bornDate);
	    	Utente utente = new Utente(
	    			0, email, name, surname, date, hashedPassword, phone
	    	);
	    	UtenteDAO utenteDAO = new UtenteDAO();
	    	utenteDAO.create(utente);

	    	Carrello carrello = new Carrello(0, utente.getId());
	    	CarrelloDAO carrelloDAO = new CarrelloDAO();
	    	carrelloDAO.create(carrello);

	    	// -- redirect --
	    	response.sendRedirect(request.getContextPath() + "/common/registrazione.jsp");
	    }catch(Exception e) {
	    	 e.printStackTrace();
	    	    request.setAttribute("errorMessage", "Errore durante la registrazione.");
	    	    request.getRequestDispatcher("/register.jsp").forward(request, response);
	    }
	}

}
