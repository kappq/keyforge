<%@ page isErrorPage="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
  Throwable ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
  String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
  if (status == null) status = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/error.css">
<title>Errore <%= status %></title>
</head>
<body>
<div class="error-page">
	<div class="error-card">
		<p class="error-code"><%= status %></p>
		<h1>Si è verificato un errore</h1>

		<p class="error-uri">URI: <%= (uri != null) ? uri : "sconosciuta" %></p>

		<% if (ex != null) { %>
			<div class="error-detail">
				<h2>Dettaglio eccezione</h2>
				<p><%= ex.getClass().getName() %>: <%= ex.getMessage() %></p>
			</div>
		<% } else { %>
			<p class="error-none">Nessuna eccezione associata.</p>
		<% } %>

		<a href="${pageContext.request.contextPath}/" class="link error-back">Torna alla home</a>
	</div>
</div>
</body>
</html>