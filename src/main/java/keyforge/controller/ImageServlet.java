package keyforge.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import keyforge.model.*;

@WebServlet("/common/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try {
			ImmagineDAO immagineDAO = new ImmagineDAO();
			Immagine immagine = immagineDAO.findById(Integer.parseInt(id));
			if (immagine == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			byte[] dati = immagine.getDati();

			response.setContentType("image/png");
			response.setContentLength(dati.length);

			ServletOutputStream out = response.getOutputStream();
			out.write(dati);
			out.flush();
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}
}
