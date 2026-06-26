package keyforge;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		HttpSession session = req.getSession(false);
		boolean isLoggedIn = session != null && session.getAttribute("utenteId") != null;

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		boolean isAllowed = isLoggedIn || uri.startsWith(contextPath + "/common/") || uri.startsWith(contextPath + "/static/");
		
		if (isAllowed) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + "/common/login.jsp");
		}
	}
}
