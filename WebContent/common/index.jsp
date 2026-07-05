<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Keyforge - Tastiere Meccaniche</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />

	<main class="home-page">
		<section class="hero">
			<div class="hero-content">
				<h1>Trova la tastiera meccanica perfetta</h1>
				<p>Switch, keycap e layout su misura per te. Qualità artigianale, sensazioni uniche.</p>
				<a href="${pageContext.request.contextPath}/common/catalogo.jsp" class="btn hero-btn">Esplora il catalogo</a>
			</div>
		</section>

		<div class="home">
			<section class="section">
				<h2>Prodotti consigliati</h2>
			</section>
			<div class="catalogo-layout">
				<div id="griglia" class="grid"></div>
			</div>	

			<section class="section">
				<div class="section-header">
					<h2>In evidenza</h2>
					<a href="${pageContext.request.contextPath}/common/catalogo.jsp" class="link">Vedi tutto →</a>
				</div>
				<div id="evidenza-griglia" class="grid"></div>
			</section>

			<section class="section perks">
				<div class="perk-card">
					<h3>Spedizione veloce</h3>
					<p>Consegna in 24/48h su tutto il territorio.</p>
					<div class="svg-view">
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640"><!--!Font Awesome Free v7.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2026 Fonticons, Inc.--><path fill="rgb(131, 206, 194)" d="M64 160C64 124.7 92.7 96 128 96L416 96C451.3 96 480 124.7 480 160L480 192L530.7 192C547.7 192 564 198.7 576 210.7L621.3 256C633.3 268 640 284.3 640 301.3L640 448C640 483.3 611.3 512 576 512L572.7 512C562.3 548.9 528.3 576 488 576C447.7 576 413.8 548.9 403.3 512L300.7 512C290.3 548.9 256.3 576 216 576C175.7 576 141.8 548.9 131.3 512L128 512C92.7 512 64 483.3 64 448L64 400L24 400C10.7 400 0 389.3 0 376C0 362.7 10.7 352 24 352L136 352C149.3 352 160 341.3 160 328C160 314.7 149.3 304 136 304L24 304C10.7 304 0 293.3 0 280C0 266.7 10.7 256 24 256L200 256C213.3 256 224 245.3 224 232C224 218.7 213.3 208 200 208L24 208C10.7 208 0 197.3 0 184C0 170.7 10.7 160 24 160L64 160zM576 352L576 301.3L530.7 256L480 256L480 352L576 352zM256 488C256 465.9 238.1 448 216 448C193.9 448 176 465.9 176 488C176 510.1 193.9 528 216 528C238.1 528 256 510.1 256 488zM488 528C510.1 528 528 510.1 528 488C528 465.9 510.1 448 488 448C465.9 448 448 465.9 448 488C448 510.1 465.9 528 488 528z"/></svg>
					</div>
				</div>
				<div class="perk-card">
					<h3>Qualità garantita</h3>
					<p>Componenti selezionati e testati singolarmente.</p>
					<div class="svg-view">
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640"><!--!Font Awesome Free v7.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2026 Fonticons, Inc.--><path fill="rgb(131, 206, 194)" d="M341.9 38.1C328.5 29.9 311.6 29.9 298.2 38.1C273.8 53 258.7 57 230.1 56.4C214.4 56 199.8 64.5 192.2 78.3C178.5 103.4 167.4 114.5 142.3 128.2C128.5 135.7 120.1 150.4 120.4 166.1C121.1 194.7 117 209.8 102.1 234.2C93.9 247.6 93.9 264.5 102.1 277.9C117 302.3 121 317.4 120.4 346C120 361.7 128.5 376.3 142.3 383.9C164.4 396 175.6 406 187.4 425.4L138.7 522.5C132.8 534.4 137.6 548.8 149.4 554.7L235.4 597.7C246.9 603.4 260.9 599.1 267.1 587.9L319.9 492.8L372.7 587.9C378.9 599.1 392.9 603.5 404.4 597.7L490.4 554.7C502.3 548.8 507.1 534.4 501.1 522.5L452.5 425.3C464.2 405.9 475.5 395.9 497.6 383.8C511.4 376.3 519.8 361.6 519.5 345.9C518.8 317.3 522.9 302.2 537.8 277.8C546 264.4 546 247.5 537.8 234.1C522.9 209.7 518.9 194.6 519.5 166C519.9 150.3 511.4 135.7 497.6 128.1C472.5 114.4 461.4 103.3 447.7 78.2C440.2 64.4 425.5 56 409.8 56.3C381.2 57 366.1 52.9 341.7 38zM320 160C373 160 416 203 416 256C416 309 373 352 320 352C267 352 224 309 224 256C224 203 267 160 320 160z"/></svg>
					</div>
				</div>
				<div class="perk-card">
					<h3>Assistenza dedicata</h3>
					<p>Supporto pre e post vendita sempre disponibile.</p>
					<div class="svg-view">
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640"><!--!Font Awesome Pro v7.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2026 Fonticons, Inc.--><path fill="rgb(131, 206, 194)" d="M415.8 89C423.6 70.2 444.2 60.1 463.9 65.5L469.4 67C534 84.6 589.2 147.2 573.1 223.4C536 398.4 398.3 536.1 223.3 573.2C147 589.4 84.5 534.1 66.9 469.5L65.4 464C60 444.3 70.1 423.7 88.9 415.9L186.2 375.4C202.7 368.5 221.8 373.3 233.2 387.2L271.8 434.4C342.1 399.5 398.6 341.1 431.1 269.5L387 233.4C373.1 222.1 368.4 203 375.2 186.4L415.8 89z"/></svg>
					</div>
				</div>
			</section>
		</div>
	</main>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />

	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/static/js/index.js"></script>
</body>
</html>
