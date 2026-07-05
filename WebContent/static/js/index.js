async function filtra() {
	const params = new URLSearchParams();
	params.set("disponibilita", '0');

	const res = await fetch(`${contextPath}/common/ArticoliServlet?${params.toString()}`);
	if (!res.ok) {
		throw new Error(`Errore interno: ${res.status}`);
	}
	articoli = await res.json();
	articoli = articoli.slice(0, 6);
	renderHomeGriglia(articoli);
}
function renderHomeGriglia(articoli) {
	const griglia = document.getElementById("griglia");

	if (articoli.length === 0) {
		griglia.innerHTML = "<p>Nessuna tastiera disponibile</p>";
		return;
	}
	griglia.innerHTML = articoli.map(a => `
		<div class="card articolo" onclick="vaiAlDettaglio(${a.id})" style="cursor:pointer;">
			<img src="${contextPath}/ImageServlet?articoloId=${a.id}" onerror="this.src='https://placehold.co/320x200'">
			<div class="articolo-info">
				<b>${a.nome}</b>
				<span>${a.brand}</span>
			</div>
			<div class="articolo-footer">
				<p>${a.descrizione}</p>
				<div class="articolo-footer-bottom">
					<div class="articolo-meta">
						<span class="prezzo">€ ${a.prezzo.toFixed(2)}</span>
						<span class="disponibilita">${a.disponibilita > 0 ? a.disponibilita + " disponibili" : "Esaurito"}</span>
					</div>
				</div>
			</div>
		</div>
	`).join("");
}
function vaiAlDettaglio(articoloId) {
	window.location.href = `${contextPath}/common/dettaglio.jsp?id=${articoloId}`;
}
filtra();