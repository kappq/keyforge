<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Catalogo</title>
</head>
<body>
<main>
	<%@ page import="keyforge.ConnectionManager" %>
	<%@ page import="java.sql.*" %>
	<section class="catalogo">
		<%
			Connection conn = ConnectionManager.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM articolo");
		%>
		<% while (rs.next()) { %>
			<%= rs.getString("nome") %><br>
		<% } %>
	</section>
</main>
</body>
</html>
