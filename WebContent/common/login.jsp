<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    
    <% String errorMessage = (String)request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
    	<p><%= errorMessage %></p>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/common/LoginServlet">
	    <label for="email">Email:</label><br>
	    <input type="email" id="email" name="email" required><br>

	    <label for="password">Password:</label><br>
	    <input type="password" id="password" name="password" required><br>

	    <button type="submit">Login</button>
    </form>
</body>
</html>
