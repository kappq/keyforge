<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="keyforge.model.Articolo" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conferma Eliminazione</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
	<% Articolo articolo = (Articolo)request.getAttribute("articolo"); %>

	<jsp:include page="/WEB-INF/fragments/header.jsp" />

	<main class="page">
		<form method="post" action="${pageContext.request.contextPath}/admin/DeleteArticoloServlet?articolo-id=<%= articolo.getId() %>" class="card">

			<h1>Elimina Articolo</h1>
			
			<div class="field">
				<p>
					Sei sicuro di voler eliminare definitivamente l'articolo <strong><%= (articolo.getNome() != null) ? articolo.getNome() : "N/A" %></strong>?
				</p>

				<p>Questa azione è irreversibile.</p>
			</div>
			
			<input type="hidden" name="id" value="<%= articolo.getId() %>">

			<button type="submit" class="btn">Conferma ed Elimina</button>
			
			<div class="form-footer">
				Ci hai ripensato? <a href="${pageContext.request.contextPath}/admin/ViewArticoliServlet" class="link">Annulla</a>.
			</div>
		</form>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />
</body>
</html>
