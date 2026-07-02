<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Carrello</title>
    <script>
		const contextPath = "<%= request.getContextPath() %>";
	</script>
</head>
<body>
	<div id="successPopupInPage" class="popup-success"></div>
	<jsp:include page="/WEB-INF/popups.jsp" />
    <h1>Carrello</h1>
	<form method="post" action="<%= request.getContextPath() %>/common/EmptyCartServlet">
		<button type="submit">Svuota Carrello</button>
	</form>
    

    
    <% String errorMessage = (String)request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
    	<p><%= errorMessage %></p>
    <% } %>
    <% HttpSession session1 = request.getSession(); %>
	<% Map<Integer, Integer> carrello = (Map<Integer, Integer>) session1.getAttribute("carrello"); %>	
	<% if(carrello == null || carrello.isEmpty()) { %>
		<p>Il carrello e' vuoto</p>
	<% } else {
		for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			ArticoloDAO articoloDAO = new ArticoloDAO();
			Articolo articolo = articoloDAO.findById(key);	
	%>
		<div>
			<p>Nome prodotto: <%= articolo.getNome() %>, quantità: <%= value %></p>
			<p>Prezzo <%= articolo.getPrezzo().multiply(BigDecimal.valueOf(value)) %></p>
			<button type="submit" onclick="changeQuantity(<%= key %>, -1)">-</button>
			<button type="submit" onclick="changeQuantity(<%= key %>, +1)">+</button>
		</div>
	<%
		}
	}
	%>
	<script src="<%= request.getContextPath() %>/static/js/carrello.js"></script>
</body>
</html>