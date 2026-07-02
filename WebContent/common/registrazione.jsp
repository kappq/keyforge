<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrazione</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/error.css">
</head>
<body>
	<jsp:include page="/WEB-INF/popups.jsp" />

    <h2>Registrazione</h2>

    <form id="lForm" method="post" action="${pageContext.request.contextPath}/common/RegisterServlet">
	    <label for="nome">Nome:</label><br>
	    <input type="text" id="nome" name="nome" required><br><br>
	
	    <label for="cognome">Cognome:</label><br>
	    <input type="text" id="cognome" name="cognome" required><br><br>
	
	    <label for="email">Email:</label><br>
	    <input type="email" id="email" name="email" placeholder="m.rossi@gmail.com" required><br><br>
	
	    <label for="dataNascita">Data di nascita:</label><br>
	    <input type="date" id="dataNascita" name="dataNascita" required><br><br>
	
	    <label for="telefono">Telefono:</label><br>
	    <input type="tel" id="telefono" name="telefono"><br><br>
	
	    <label for="password">Password:</label><br>
	    <input type="password" id="password" name="password" placeholder="Almeno 6 caratteri"required><br><br>

		<ul id="errorList"></ul>
		<span id="emailMsg"></span>

		<br>

	    <button type="submit">Registrati</button>
    </form>

	<script>
		const contextPath = "<%= request.getContextPath() %>";
	</script>
	<script src="${pageContext.request.contextPath}/static/js/validate-form.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/check-email.js"></script>
</body>
</html>
