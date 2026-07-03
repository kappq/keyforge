<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="keyforge.model.Utente,keyforge.model.UtenteDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profilo</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
	<% Integer utenteId = (Integer)session.getAttribute("utenteId"); %>

	<% UtenteDAO utenteDAO = new UtenteDAO(); %>
	<% Utente utente = utenteDAO.findById(utenteId); %>

	<jsp:include page="/WEB-INF/fragments/header.jsp" />

	<main class="page">
		<form method="post" action="${pageContext.request.contextPath}/common/RegisterServlet" class="card">
			<% String errorMessage = (String)request.getAttribute("errorMessage"); %>
			<% if (errorMessage != null) { %>
				<p><%= errorMessage %></p><br>
			<% } %>

			<h1>Profilo</h1>

			<div class="field">
				<label for="nome">Nome:</label>
				<input type="text" id="nome" name="nome" placeholder="Marco" value="<%= utente.getNome() %>" required>
				<div id="nome-error" class="field-error"></div>
			</div>
		
			<div class="field">
				<label for="cognome">Cognome:</label>
				<input type="text" id="cognome" name="cognome" placeholder="Rossi" value="<%= utente.getCognome() %>" required>
				<div id="cognome-error" class="field-error"></div>
			</div>
		
			<div class="field">
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" placeholder="m.rossi@gmail.com" value="<%= utente.getEmail() %>" required>
				<div id="email-error" class="field-error"></div>
			</div>
		
			<div class="field">
				<label for="dataNascita">Data di Nascita:</label>
				<input type="date" id="data-nascita" name="data-nascita" value="<%= utente.getDataNascita() %>" required>
				<div id="data-nascita-error" class="field-error"></div>
			</div>
		
			<div class="field">
				<label for="telefono">Telefono:</label>
				<input type="tel" id="telefono" name="telefono" placeholder="1234567890" value="<%= utente.getTelefono() %>" required>
				<div id="telefono-error" class="field-error"></div>
			</div>

			<button type="submit" id="submit-btn" class="btn">Registrati</button>
		</form>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />

	<script>
		const contextPath = "<%= request.getContextPath() %>";
	</script>
	<script src="${pageContext.request.contextPath}/static/js/validate-form.js"></script>
</body>
</html>
