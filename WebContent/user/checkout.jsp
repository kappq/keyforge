<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="keyforge.model.Utente,keyforge.model.UtenteDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Checkout</title>
</head>
<body>
	<h1>Checkout</h1>

    <% String errorMessage = (String)request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
    	<p><%= errorMessage %></p>
    <% } %>

	<% Integer utenteId = (Integer)session.getAttribute("utenteId"); %>

	<% UtenteDAO utenteDAO = new UtenteDAO(); %>
	<% Utente utente = utenteDAO.findById(utenteId); %>

	<form method="post" action="<%= request.getContextPath() %>/user/CheckoutServlet">
		<fieldset>
			<legend>Informazioni personali:</legend>
			<p>Nome: <%= utente.getNome() %></p>
			<p>Cognome: <%= utente.getCognome() %></p>
			<p>Email: <%= utente.getEmail() %></p>
			<p>Telefono: <%= utente.getTelefono() %></p>
		</fieldset>
		<fieldset>
			<legend>Indirizzo di spedizione:</legend>
			<label for="paese">Paese:</label><br>
			<input id="paese" name="paese" type="text" required /><br>
			<label for="cap">CAP:</label><br>
			<input id="cap" name="cap" type="text" required /><br>
			<label for="via">Via:</label><br>
			<input id="via" name="via" type="text" required /><br>
		</fieldset>
		<button type="submit">Riepilogo Ordine</button>
	</form>
</body>
</html>
