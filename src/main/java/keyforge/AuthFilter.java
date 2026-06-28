package keyforge;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyforge.model.Utente;
import keyforge.model.UtenteDAO;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		HttpSession session = req.getSession(true);
		Integer utenteId = (Integer)session.getAttribute("utenteId");

		try {
			Utente utente = null;
			if (utenteId != null) {
				UtenteDAO utenteDAO = new UtenteDAO();
				utente = utenteDAO.findById(utenteId);
			}

			boolean isLogged = utente != null;
			boolean isAdmin = utente != null && utente.getIsAdmin();

			String uri = req.getRequestURI();
			String contextPath = req.getContextPath();
			boolean isAllowed =
				uri.startsWith(contextPath + "/common/") ||
				uri.startsWith(contextPath + "/static/") ||
				(uri.startsWith(contextPath + "/user/") && isLogged) ||
				(uri.startsWith(contextPath + "/admin/") && isAdmin);

			if (isAllowed) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/common/login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error interno del database nel filtro di autenticazione", e);
		}
	}
}
