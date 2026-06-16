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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>Errore <%= status %></title>
</head>
<body>
<main>
  	<h1>Errore <%= status %></h1>
  	<p>URI: <%= (uri != null) ? uri : "sconosciuta" %></p>	
	<%  if (ex != null) { %>
    	<h2>Dettaglio eccezione</h2>
    	<p><%= ex.getClass().getName() %>: <%= ex.getMessage() %></p>
 	<% } else { %>
    	<p>Nessuna eccezione associata.</p>
 	<% } %>
</main>
</body>
</html>
