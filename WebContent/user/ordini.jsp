<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="keyforge.model.Ordine,keyforge.model.OrdineDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>I Miei Ordini</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/catalogo.css">
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />
	<jsp:include page="/WEB-INF/popups.jsp" />

	<% Integer utenteId = (Integer)session.getAttribute("utenteId"); %>

	<% OrdineDAO ordineDAO = new OrdineDAO(); %>
	<% Collection<Ordine> ordini = ordineDAO.findByUtenteId(utenteId); %>

	<main class="catalogo-page">
		<div class="catalogo">
			<div class="card wide">
				<h1>I Miei Ordini</h1>

				<table class="table-scroll">
					<thead>
						<tr>
							<th>Stato</th>
							<th>Indirizzo</th>
							<th>Tracking</th>
							<th>Note</th>
							<th>Data ordine</th>
							<th>Fattura</th>
						</tr>
					</thead>
					<tbody>
						<% if (ordini == null || ordini.isEmpty()) { %>
							<tr>
								<td colspan="6" class="griglia-vuota">Non hai ancora effettuato ordini.</td>
							</tr>
						<% } else { %>
							<% for (Ordine ordine : ordini) { %>
								<tr>
									<td><%= ordine.getStato() %></td>
									<td><%= ordine.getIndirizzoSpedizione() %></td>
									<td><%= ordine.getTracking() %></td>
									<td><%= (ordine.getNote() != null ? ordine.getNote() : "-") %></td>
									<td><%= ordine.getDataOrdine() %></td>
									<td><a href="${pageContext.request.contextPath}/user/FatturaServlet?ordine-id=<%= ordine.getId() %>" target="blank_">Fattura</a></td>
								</tr>
							<% } %>
						<% } %>
					</tbody>
				</table>
			</div>
		</div>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />
</body>
</html>
