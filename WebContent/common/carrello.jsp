<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Carrello</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/carrello.css">
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />
	<jsp:include page="/WEB-INF/popups.jsp" />

	<%
		HttpSession session1 = request.getSession();
		Map<Integer, Integer> carrello = (Map<Integer, Integer>) session1.getAttribute("carrello");
		String errorMessage = (String) request.getAttribute("errorMessage");

		BigDecimal totale = BigDecimal.ZERO;
		ArticoloDAO articoloDAO = new ArticoloDAO();
	%>

	<div class="cart-page">
		<div class="cart">
			<div class="cart-header">
				<h1>Carrello</h1>

				<% if (carrello != null && !carrello.isEmpty()) { %>
					<form method="post" action="<%= request.getContextPath() %>/common/EmptyCartServlet">
						<button type="submit" class="btn-outline">Svuota carrello</button>
					</form>
				<% } %>
			</div>

			<% if (carrello == null || carrello.isEmpty()) { %>
				<div class="cart-empty">
					<svg viewBox="0 0 24 24"><path d="M7 4h-2l-1 2h-2v2h2l3.6 7.59-1.35 2.44c-.16.28-.25.61-.25.97 0 1.1.9 2 2 2h12v-2h-11.42c-.14 0-.25-.11-.25-.25l.03-.12.9-1.63h7.45c.75 0 1.41-.42 1.75-1.03l3.58-6.49c.08-.14.12-.31.12-.48 0-.55-.45-1-1-1h-14.42l-.94-2zm0 16c-1.1 0-1.99.9-1.99 2s.89 2 1.99 2 2-.9 2-2-.9-2-2-2zm10 0c-1.1 0-1.99.9-1.99 2s.89 2 1.99 2 2-.9 2-2-.9-2-2-2z"/></svg>
					<p>Il carrello è vuoto</p>
					<a href="${pageContext.request.contextPath}/common/catalogo.jsp" class="btn">Continua lo shopping</a>
				</div>
			<% } else { %>
				<div class="cart-layout">
					<div class="cart-items">
						<%
							for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
								Integer key = entry.getKey();
								Integer value = entry.getValue();
								Articolo articolo = articoloDAO.findById(key);
								BigDecimal subtotale = articolo.getPrezzo().multiply(BigDecimal.valueOf(value));
								totale = totale.add(subtotale);
						%>
						<div class="cart-item">
							<div class="cart-item-info">
								<b><%= articolo.getNome() %></b>
								<span><%= articolo.getPrezzo() %> € cad.</span>
							</div>

							<div class="cart-item-qty">
								<button type="button" onclick="changeQuantity(<%= key %>, -1)">−</button>
								<span><%= value %></span>
								<button type="button" onclick="changeQuantity(<%= key %>, +1)">+</button>
							</div>

							<div class="cart-item-subtotal"><%= subtotale %> €</div>

							<button type="button" class="cart-item-remove" onclick="changeQuantity(<%= key %>, -<%= value %>)">×</button>
						</div>
						<% } %>
					</div>

					<div class="cart-summary card">
						<h2>Riepilogo ordine</h2>
						<div class="summary-row">
							<span>Subtotale</span>
							<span><%= totale %> €</span>
						</div>
						<div class="summary-row">
							<span>Spedizione</span>
							<span>Gratuita</span>
						</div>
						<div class="summary-row total">
							<span>Totale</span>
							<span><%= totale %> €</span>
						</div>
						<a href="${pageContext.request.contextPath}/user/checkout.jsp">
							<button type="button" class="btn">Procedi al checkout</button>
						</a>
					</div>
				</div>
			<% } %>
		</div>
	</div>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />
	
	<script>
		const contextPath = "<%= request.getContextPath() %>";
	</script>
	<script src="<%= request.getContextPath() %>/static/js/carrello.js"></script>
</body>
</html>