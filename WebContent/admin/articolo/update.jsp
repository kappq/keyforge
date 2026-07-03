<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="keyforge.model.Articolo" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Articolo</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
	<% Articolo articolo = (Articolo)request.getAttribute("articolo"); %>

	<jsp:include page="/WEB-INF/fragments/header.jsp" />

	<main class="page">
		<form method="post" action="${pageContext.request.contextPath}/admin/UpdateArticoloServlet" class="card wide">

			<h1>Modifica Articolo</h1>
			
			<input type="hidden" name="id" value="<%= articolo.getId() %>">
			
			<div class="field">
				<label for="nome">Nome:</label>
				<input type="text" id="nome" name="nome" value="<%= (articolo.getNome() != null) ? articolo.getNome() : "" %>" required>
			</div>

			<div class="field">
				<label for="brand">Brand:</label>
				<input type="text" id="brand" name="brand" value="<%= (articolo.getBrand() != null) ? articolo.getBrand() : "" %>" required>
			</div>

			<div class="field">
				<label for="descrizione">Descrizione:</label>
				<textarea id="descrizione" name="descrizione" required><%= (articolo.getDescrizione() != null) ? articolo.getDescrizione() : "" %></textarea>
			</div>

			<div class="field">
				<label for="prezzo">Prezzo (€):</label>
				<input type="number" step="0.01" min="0" id="prezzo" name="prezzo" value="<%= (articolo.getPrezzo() != null) ? articolo.getPrezzo() : "" %>" required>
			</div>

			<div class="field">
				<label for="disponibilita">Disponibilità:</label>
				<input type="number" min="0" id="disponibilita" name="disponibilita" value="<%= articolo.getDisponibilita() %>" required>
			</div>

			<div class="field">
				<label for="dimensione">Dimensione (mm):</label>
				<input type="number" min="0" id="dimensione" name="dimensione" value="<%= articolo.getDimensione() %>" required>
			</div>

			<div class="field">
				<label for="peso">Peso (g):</label>
				<input type="number" min="0" id="peso" name="peso" value="<%= articolo.getPeso() %>" required>
			</div>

			<div class="field">
				<label for="layout">Layout:</label>
				<input type="text" id="layout" name="layout" value="<%= (articolo.getLayout() != null) ? articolo.getLayout() : "" %>" required>
			</div>

			<button type="submit" class="btn">Aggiorna Articolo</button>
			
			<div class="form-footer">
				Ci hai ripensato? <a href="${pageContext.request.contextPath}/admin/ViewArticoliServlet" class="link">Annulla</a>.
			</div>
		</form>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />
</body>
</html>
