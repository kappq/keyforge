<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="keyforge.model.Articolo,keyforge.model.ArticoloDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Riepilogo Ordine</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/popups.jsp" />

	<h1>Riepilogo Ordine</h1>

    <% String errorMessage = (String)request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
    	<p><%= errorMessage %></p>
    <% } %>

	<% ArticoloDAO articoloDAO = new ArticoloDAO(); %>
	<% Map<Integer, Integer> carrello = (Map<Integer, Integer>)session.getAttribute("carrello"); %>
	
	<% if (carrello == null || carrello.isEmpty()) { %>
		<% request.getSession().setAttribute("errorMessage", "Il carrello è vuoto"); %>
		<% response.sendRedirect(request.getContextPath() + "/common/catalogo.jsp"); %>
	<% } else { %>
		<% BigDecimal totale = BigDecimal.ZERO; %>
		<div class="grid">
			<% for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) { %>
				<% Articolo articolo = articoloDAO.findById(entry.getKey()); %>
				<% totale = totale.add(articolo.getPrezzo().multiply(new BigDecimal(entry.getValue()))); %>
				<div class="articolo">
					<img src="<%= request.getContextPath() %>/ImageServlet?articoloId=<%= articolo.getId() %>" onerror="this.src = 'https://placehold.co/320x200'">
					<div class="spaced">
						<b><%= articolo.getNome() %></b>
						<span><%= articolo.getBrand() %></span>
					</div>
					<p><%= articolo.getDescrizione() %></p>
					<div class="spaced">
						<div>€ <%= articolo.getPrezzo() %></div>
						<div><%= entry.getValue() %>x</div>
					</div>
				</div>
			<% } %>
		</div>
		
		<p>Totale: € <%= totale %></p>

		<form method="post" action="<%= request.getContextPath() %>/user/ConfirmOrderServlet">
			<button type="submit">Conferma Ordine</button>
		</form>
	<% } %>
</body>
</html>
