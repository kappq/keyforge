<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="keyforge.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tastiere</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div id="filtri">
	<input type="number" id="prezzo-min" placeholder="Prezzo minimo">
	<input type="number" id="prezzo-max" placeholder="Prezzo massimo">
	<select id="disponibilita">
		<option value="0">Tutti</option>
		<option value="1">Disponibile</option>
	</select>
	<button onclick="clearFilters()">Pulisci</button>
</div>

<div id="griglia" class="grid"></div>

<script>
const contextPath = "<%= request.getContextPath() %>";

const filtri = ["prezzo-min", "prezzo-max", "disponibilita"];
let debounceTimer;

filtri.forEach(id => {
	document.getElementById(id).addEventListener("input", () => {
		clearTimeout(debounceTimer);
		debounceTimer = setTimeout(filtra, 300);
	})
});

async function filtra() {
	const params = new URLSearchParams();
	
	const prezzoMin = document.getElementById("prezzo-min").value;
	const prezzoMax = document.getElementById("prezzo-max").value;
	const disponibilita = document.getElementById("disponibilita").value;
	
	if (prezzoMin) {
		params.set("prezzo-min", prezzoMin);
	}
	if (prezzoMax) {
		params.set("prezzo-max", prezzoMax);
	}
	if (disponibilita) {
		params.set("disponibilita", disponibilita);
	}
	
	const res = await fetch(`\${contextPath}/articoli?\${params}`);
	if (!res.ok) {
		throw new Error("Errore interno: " + resp.status);
	}
	const articoli = await res.json();

	renderGriglia(articoli);
}

function renderGriglia(articoli) {
	const griglia = document.getElementById("griglia");

	if (articoli.length === 0) {
		griglia.innerHTML = "<p>Nessuna tastiera disponibile</p>";
		return;
	}

	griglia.innerHTML = articoli.map(a => `
		<div class="articolo">
			<img src="\${contextPath}/immagine?articoloId=\${a.id}" onerror="this.src = 'https://placehold.co/320x200'">
			<div class="spaced">
				<b>\${a.nome}</b>
				<span>\${a.brand}</span>
			</div>
			<p>\${a.descrizione}</p>
			<div class="spaced">
				<div>€ \${a.prezzo.toFixed(2)}</div>
				<div>\${a.disponibilita > 0 ? a.disponibilita + " disponibili" : "Esaurito"}</div>
			</div>
		</div>
	`).join("");
}

function clearFilters() {
	document.getElementById("prezzo-min").value = "";
	document.getElementById("prezzo-max").value = "";
	document.getElementById("disponibilita").value = "0";
	filtra();
}

filtra();
</script>
</body>
</html>
