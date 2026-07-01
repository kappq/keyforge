<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Catalogo</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/catalogo.css">
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />

	<main class="catalogo-page">
		<div class="catalogo">
			<h1>Catalogo</h1>

			<div class="catalogo-layout">
				<aside class="card sidebar">
					<h2>Filtri</h2>

					<div class="field">
						<label for="nome">Nome articolo</label>
						<input type="text" id="nome" placeholder="Cerca...">
					</div>

					<div class="field">
						<label>Prezzo</label>
						<div class="price-range">
							<input type="number" id="prezzo-min" placeholder="Min">
							<span>—</span>
							<input type="number" id="prezzo-max" placeholder="Max">
						</div>
					</div>

					<div class="field">
						<label for="disponibilita">Disponibilità</label>
						<select id="disponibilita">
							<option value="0">Tutti</option>
							<option value="1">Disponibile</option>
						</select>
					</div>

					<button type="button" class="reset" onclick="clearFilters()">Reset Filtri</button>
				</aside>

				<div id="griglia" class="grid"></div>
			</div>
		</div>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />

	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/static/js/catalogo.js"></script>
</body>
</html>
