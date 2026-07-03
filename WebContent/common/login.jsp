<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />
	<jsp:include page="/WEB-INF/popups.jsp" />
	
	<main class="page">
		<form method="post" action="<%= request.getContextPath() %>/common/LoginServlet" class="card">
			<h1>Login</h1>
			
			<div class="field">
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" placeholder="m.rossi@gmail.com" required>
			</div>

			<div class="field">
				<label for="password">Password:</label>
				<input type="password" id="password" name="password" placeholder="••••••••••••" required>
			</div>

			<button type="submit" class="btn">Login</button>
			
			<div class="form-footer">
				Non hai un account? <a href="${pageContext.request.contextPath}/common/registrazione.jsp">Registrati</a>.
			</div>
		</form>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />
</body>
</html>
