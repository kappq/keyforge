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
	<jsp:include page="/WEB-INF/popups.jsp" />

	<main class="page">
		<div class="card">
			<form method="post" action="${pageContext.request.contextPath}/user/UpdateProfiloServlet">
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

				<button type="submit" id="submit-btn" class="btn">Aggiorna Profilo</button>
			</form>

			<form method="post" action="${pageContext.request.contextPath}/user/LogoutServlet" class="logout-form">
				<button type="submit" class="btn btn-logout">Logout</button>
		
				<div class="form-footer">
					Visualizza i tuoi <a href="${pageContext.request.contextPath}/user/ordini.jsp">ordini</a>.
				</div>
			</form>
		</div>
		
		<% if (utente.getIsAdmin()) { %>
			<div class="card">
				<h1>Admin</h1>
				
				<a href="${pageContext.request.contextPath}/admin/ordini.jsp" class="btn">Visualizza e Filtra Ordini</a>
				<a href="${pageContext.request.contextPath}/admin/ViewArticoliServlet" class="btn">Gestisci Articoli</a>
			</div>
		<% } %>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />

	<script>
		const contextPath = "<%= request.getContextPath() %>";
		const userEmail = "<%= utente.getEmail() %>";
	</script>
	<script src="${pageContext.request.contextPath}/static/js/validate-profilo.js"></script>
</body>
</html>
