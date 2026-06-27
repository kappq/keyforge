<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    
    <% String errorMessage = (String)request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
    	<p><%= errorMessage %></p>
    <% } %>
    <% HttpSession session1 = request.getSession(); %>
	<% Map<Integer, Integer> carrello = (Map<Integer, Integer>) session1.getAttribute("carrello"); %>	
	<% if(carrello == null) { %>
		<p>Il carrello e' vuoto</p>
	<% } else {
		for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			ArticoloDAO articoloDAO = new ArticoloDAO();
			Articolo articolo = articoloDAO.findById(key);	
	%>
		<div>
			<p>Nome prodotto: <%= articolo.getNome() %>, quantità: <%= value %></p>
			<p>Prezzo <%= articolo.getPrezzo().multiply(BigDecimal.valueOf(value)) %></p>
		</div>
	<%
		}
	}
	%>
	
</body>
</html>