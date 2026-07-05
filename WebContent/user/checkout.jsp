<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="keyforge.model.Utente,keyforge.model.UtenteDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Checkout</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />
	<jsp:include page="/WEB-INF/popups.jsp" />

	<% Integer utenteId = (Integer)session.getAttribute("utenteId"); %>

	<% UtenteDAO utenteDAO = new UtenteDAO(); %>
	<% Utente utente = utenteDAO.findById(utenteId); %>

	<main class="page">
		<form method="post" action="<%= request.getContextPath() %>/user/CheckoutServlet" class="card">
			<h1>Checkout</h1>

			<fieldset>
				<legend>Informazioni personali</legend>
				<div class="field">
					<label for="nome">Nome:</label>
					<input id="nome" type="text" value="<%= utente.getNome() %>" disabled>
				</div>
				<div class="field">
					<label for="cognome">Cognome:</label>
					<input id="cognome" type="text" value="<%= utente.getCognome() %>" disabled>
				</div>
				<div class="field">
					<label for="email">Email:</label>
					<input id="email" type="email" value="<%= utente.getEmail() %>" disabled>
				</div>
				<div class="field">
					<label for="telefono">Telefono:</label>
					<input id="telefono" type="text" value="<%= utente.getTelefono() %>" disabled>
				</div>
			</fieldset>

			<fieldset>
				<legend>Indirizzo di spedizione</legend>
				<div class="field">
					<label for="paese">Paese:</label>
					<input id="paese" name="paese" type="text" placeholder="Italia" required>
				</div>
				<div class="field">
					<label for="cap">CAP:</label>
					<input id="cap" name="cap" type="number" placeholder="00100" required>
				</div>
				<div class="field">
					<label for="via">Via:</label>
					<input id="via" name="via" type="text" placeholder="Via Roma 123" required>
				</div>
			</fieldset>

			<button type="submit" class="btn">Riepilogo Ordine</button>

			<div class="form-footer">
				Le informazioni personali possono essere modificate dalla <a href="<%= request.getContextPath() %>/user/profilo.jsp">pagina del profilo</a>.
			</div>
		</form>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />
</body>
</html>
