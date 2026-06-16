<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Catalogo</title>
</head>
<body>
<main>
	<%@ page import="keyforge.model.Articolo,keyforge.model.ArticoloDAO" %>
	<%@ page import="keyforge.model.Tastiera,keyforge.model.TastieraDAO" %>
	<% ArticoloDAO articoloDAO = new ArticoloDAO(); %>
	<% TastieraDAO tastieraDAO = new TastieraDAO(); %>
	<% for (Tastiera tastiera : tastieraDAO.findAll()) { %>
		<% Articolo articolo = articoloDAO.findById(tastiera.getArticoloId()); %>
		<%= articolo.getNome() %> - <%= tastiera.getLayout() %><br>
	<% } %>
</main>
</body>
</html>
