<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="keyforge.model.Utente,keyforge.model.UtenteDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Profilo</title>
</head>
<body>
	<% Integer utenteId = (Integer)session.getAttribute("utenteId"); %>

	<% UtenteDAO utenteDAO = new UtenteDAO(); %>
	<% Utente utente = utenteDAO.findById(utenteId); %>

	<h1>Benvenuto, <%= utente.getNome() %>!</h1>
	
	<p>Email: <%= utente.getEmail() %></p>
	<p>Nome: <%= utente.getNome() %></p>
	<p>Cognome: <%= utente.getCognome() %></p>
	<p>Data di Nascita: <%= utente.getDataNascita() %></p>
	<p>Telefono: <%= utente.getTelefono() %></p>

	<form method="post" action="<%= request.getContextPath() %>/LogoutServlet">
		<button type="submit">Logout</button>
	</form>
</body>
</html>
