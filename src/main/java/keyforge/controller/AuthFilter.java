package keyforge.controller;

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

			String contextPath = req.getContextPath();

			String uri = req.getRequestURI();
			boolean isLogged = utente != null;
			boolean isAdmin = utente != null && utente.getIsAdmin();

			if (uri.startsWith(contextPath + "/admin/")) {
				if (!isAdmin) {
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				chain.doFilter(req, res);
				return;
			} else if (uri.startsWith(contextPath + "/common/") || uri.startsWith(contextPath + "/static/") || (uri.startsWith(contextPath + "/user/") && isLogged)) {
				chain.doFilter(request, response);
				return;
			} else {
				req.getSession().setAttribute("errorMessage", "Devi effettuare l'accesso per eseguire questa operazione");
				res.sendRedirect(req.getContextPath() + "/common/login.jsp");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error interno del database nel filtro di autenticazione", e);
		}
	}
}
