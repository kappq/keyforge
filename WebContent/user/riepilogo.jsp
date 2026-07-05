<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Riepilogo</title>
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
				<h1>Riepilogo Ordine</h1>
			</div>

			<div class="cart-layout">
				<div class="cart-items">
					<% for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) { %>
						<%
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

							<div class="cart-item-subtotal"><%= subtotale %> €</div>
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

					<form method="post" action="${pageContext.request.contextPath}/user/ConfirmOrderServlet">
						<div class="check" style="margin-bottom: 1rem;">
							<input type="checkbox" id="conferma-pagamento" name="conferma-pagamento" required>
							<label for="conferma-pagamento">*Il pagamento avviene alla consegna</label>
						</div>
					
						<button type="submit" class="btn">Conferma Ordine</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />
	
	<script>
		const contextPath = "<%= request.getContextPath() %>";
	</script>
	<script src="<%= request.getContextPath() %>/static/js/carrello.js"></script>
</body>
</html>
