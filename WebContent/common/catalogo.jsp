<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Catalogo</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/style.css">
</head>
<body>
	<form method="post" action="<%= request.getContextPath() %>/common/EmptyCartServlet">
		<button type="submit">Svuota Carrello</button>
	</form>

	<div id="filtri">
		<input type="text" id="nome" placeholder="Nome articolo">
		<input type="number" id="prezzo-min" placeholder="Prezzo minimo">
		<input type="number" id="prezzo-max" placeholder="Prezzo massimo">
		<select id="disponibilita">
			<option value="0">Tutti</option>
			<option value="1">Disponibile</option>
		</select>
		<button onclick="clearFilters()">Reset Filtri</button>
	</div>

	<div id="griglia" class="grid"></div>

	<script>
		const contextPath = "<%= request.getContextPath() %>";
	</script>
	<script src="<%= request.getContextPath() %>/static/js/catalogo.js"></script>
</body>
</html>
