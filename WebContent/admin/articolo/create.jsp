<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Articolo</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />

	<main class="page">
		<form method="post" action="${pageContext.request.contextPath}/admin/CreateArticoloServlet" class="card wide">

			<h1>Aggiungi Nuovo Articolo</h1>
			
			<div class="field">
				<label for="nome">Nome:</label>
				<input type="text" id="nome" name="nome" placeholder="Keychron K2 Pro" required>
			</div>

			<div class="field">
				<label for="brand">Brand:</label>
				<input type="text" id="brand" name="brand" placeholder="Keychron" required>
			</div>

			<div class="field">
				<label for="descrizione">Descrizione:</label>
				<textarea id="descrizione" name="descrizione" placeholder="Inserisci una descrizione dettagliata..." required></textarea>
			</div>

			<div class="field">
				<label for="prezzo">Prezzo (€):</label>
				<input type="number" min="0" step="0.01" id="prezzo" name="prezzo" placeholder="129.99" required>
			</div>

			<div class="field">
				<label for="disponibilita">Disponibilità:</label>
				<input type="number" min="0" id="disponibilita" name="disponibilita" placeholder="0" required>
			</div>

			<div class="field">
				<label for="dimensione">Dimensione (mm):</label>
				<input type="number" min="0" id="dimensione" name="dimensione" placeholder="365" required>
			</div>

			<div class="field">
				<label for="peso">Peso (g):</label>
				<input type="number" min="0" id="peso" name="peso" placeholder="1950" required>
			</div>

			<div class="field">
				<label for="layout">Layout:</label>
				<input type="text" id="layout" name="layout" placeholder="ISO TKL" required>
			</div>

			<button type="submit" class="btn">Aggiungi Articolo</button>

			<div class="form-footer">
				Ci hai ripensato? <a href="${pageContext.request.contextPath}/admin/ViewArticoliServlet" class="link">Annulla</a>.
			</div>
		</form>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />
</body>
</html>
