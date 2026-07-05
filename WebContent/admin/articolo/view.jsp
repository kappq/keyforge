<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="keyforge.model.Articolo" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Visualizza Articoli</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
	<% Collection<Articolo> articoli = (Collection<Articolo>)request.getAttribute("articoli"); %>

	<jsp:include page="/WEB-INF/fragments/header.jsp" />
	<jsp:include page="/WEB-INF/popups.jsp" />

	<main class="page">
		<div class="card wide">
			<h1>Inventario Articoli</h1>
			
			<% if (articoli.isEmpty()) { %>
				<div class="form-footer">Nessun articolo presente nel catalogo.</div>
			<% } else { %>
				<table class="table-scroll">
					<thead>
						<tr>
							<th>ID</th>
							<th>Nome</th>
							<th>Brand</th>
							<th>Prezzo</th>
							<th>Disponibilità</th>
							<th>Layout</th>
							<th>Modifica</th>
							<th>Elimina</th>
						</tr>
					</thead>
					<tbody>
						<% for (Articolo a : articoli) { %>
							<tr>
								<td><%= a.getId() %></td>
								<td><strong><%= a.getNome() %></strong></td>
								<td><%= a.getBrand() %></td>
								<td><%= a.getPrezzo() %> €</td>
								<td><%= a.getDisponibilita() %> px</td>
								<td><%= a.getLayout() %></td>
								<td>
									<a href="${pageContext.request.contextPath}/admin/UpdateArticoloServlet?articolo-id=<%= a.getId() %>" class="link">Modifica</a>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/admin/DeleteArticoloServlet?articolo-id=<%= a.getId() %>" class="link">Elimina</a>
								</td>
							</tr>
						<% } %>
					</tbody>
				</table>
			<% } %>

			<div class="form-footer">
				Devi inserire qualcosa di nuovo? <a href="${pageContext.request.contextPath}/admin/CreateArticoloServlet" class="link">Aggiungi articolo</a>.
			</div>
		</div>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />
</body>
</html>
