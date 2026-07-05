<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Dettaglio Articolo</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/dettaglio.css">
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />
	<div id="successPopupInPage" class="popup-success" style="display:none;"></div>
	<jsp:include page="/WEB-INF/popups.jsp" />

	<main class="dettaglio-page">
		<div class="dettaglio">
			<a href="${pageContext.request.contextPath}/common/catalogo.jsp" class="dettaglio-back">&larr; Torna al catalogo</a>

			<div id="dettaglio-content" class="dettaglio-layout"></div>
		</div>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />

	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/static/js/dettaglio.js"></script>
</body>
</html>