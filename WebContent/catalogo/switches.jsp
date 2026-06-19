<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Switches</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<h1>Switches</h1>
<%
	ArticoloDAO articoloDAO = new ArticoloDAO();
	ImmagineDAO immagineDAO = new ImmagineDAO();
	SwitchDAO switchDAO = new SwitchDAO();
	Collection<Switch> switches = switchDAO.findAll();
%>
<% if (switches.isEmpty()) { %>
	<p>Nessuno switch disponibile</p>
<% } else { %>
	<div class="grid">
		<% for (Switch switch_ : switchDAO.findAll()) { %>
			<%
				Articolo articolo = articoloDAO.findById(switch_.getArticoloId());
				Collection<Immagine> immagini = immagineDAO.findByArticoloId(articolo.getId());
			%>
			<div class="articolo">
				<% if (immagini.isEmpty()) { %>
					<img src="https://placehold.co/320x200"">
				<% } else { %>
					<% Immagine immagine = (Immagine)immagini.toArray()[0]; %>
					<img src="<%= request.getContextPath() %>/immagine?id=<%= immagine.getId() %>">
				<% } %>
				<div class="spaced">
					<b><%= articolo.getNome() %></b>
					<span><%= articolo.getBrand() %></span>
				</div>
				<p><%= articolo.getDescrizione() %></p>
				<div class="spaced">
					<div>€ <%= articolo.getPrezzo() %></div>
					<% if (articolo.getDisponibilita() > 0) { %>
						<div><%= articolo.getDisponibilita() %> disponibili</div>
					<% } else { %>
						<div>Esaurito</div>
					<% } %>
				</div>
			</div>
		<% } %>
	</div>
<% } %>
</body>
</html>
