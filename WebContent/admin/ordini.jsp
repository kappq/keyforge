<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Ordini</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/catalogo.css">
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />
	<jsp:include page="/WEB-INF/popups.jsp" />

	<main class="catalogo-page">
		<div class="catalogo">
			<h1>Ordini</h1>

			<div class="catalogo-layout">
				<aside class="card sidebar">
					<h2>Filtri</h2>

					<div class="field">
						<label for="id-utente">ID utente:</label>
						<input type="number" id="id-utente" placeholder="ID utente" min="1">
					</div>
					<div class="field">
						<label for="stato">Stato:</label>
						<select id="stato">
							<option value="">Tutti gli stati</option>
							<option value="in_attesa">In attesa</option>
							<option value="spedito">Spedito</option>
							<option value="consegnato">Consegnato</option>
							<option value="annullato">Annullato</option>
						</select>
					</div>
					<div class="field">
						<label for="data-inizio">Data inizio:</label>
						<input type="date" id="data-inizio">
					</div>
					<div class="field">
						<label for="data-fine">Data fine:</label>
						<input type="date" id="data-fine">
					</div>

					<button type="button" class="reset" onclick="resetFiltri()">Reset</button>
				</aside>

				<div class="card wide">
					<table>
						<thead>
							<tr>
								<th>ID</th>
								<th>Utente</th>
								<th>Stato</th>
								<th>Indirizzo</th>
								<th>Tracking</th>
								<th>Note</th>
								<th>Data ordine</th>
							</tr>
						</thead>
						<tbody id="tbody"></tbody>
					</table>
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />

	<script>
		const contextPath = "<%= request.getContextPath() %>";
	</script>
	<script src="<%= request.getContextPath() %>/static/js/ordini.js"></script>
</body>
</html>
