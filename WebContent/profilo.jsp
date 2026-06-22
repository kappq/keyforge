<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="keyforge.model.Utente" %>
<% Utente utente = (Utente)session.getAttribute("utente"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Profilo</title>
</head>
<body>
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
