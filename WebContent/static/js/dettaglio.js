const params = new URLSearchParams(window.location.search);
const articoloId = params.get("id");

let articoloCorrente = null;

async function caricaDettaglio() {
	const contenitore = document.getElementById("dettaglio-content");

	if (!articoloId) {
		contenitore.innerHTML = `<p class="dettaglio-error">Articolo non specificato</p>`;
		return;
	}

	try {
		const res = await fetch(`${contextPath}/common/ArticoliServlet?id=${articoloId}`);
		if (!res.ok) {
			throw new Error(`Errore interno: ${res.status}`);
		}

		const dati = await res.json();
		const a = Array.isArray(dati) ? dati[0] : dati;

		if (!a) {
			contenitore.innerHTML = `<p class="dettaglio-error">Articolo non trovato</p>`;
			return;
		}

		articoloCorrente = a;
		renderDettaglio(a);
	} catch (err) {
		contenitore.innerHTML = `<p class="dettaglio-error">Impossibile caricare l'articolo</p>`;
	}
}

function renderDettaglio(a) {
	const contenitore = document.getElementById("dettaglio-content");
	const esaurito = a.disponibilita === 0;

	contenitore.innerHTML = `
		<div class="card dettaglio-img">
			<img src="${contextPath}/ImageServlet?articoloId=${a.id}" onerror="this.src='https://placehold.co/600x420'">
		</div>
		<div class="card dettaglio-info">
			<p class="dettaglio-brand">${a.brand}</p>
			<h1>${a.nome}</h1>
			<p class="dettaglio-desc">${a.descrizione}</p>
			<div class="dettaglio-meta">
				<span class="dettaglio-prezzo">€ ${a.prezzo.toFixed(2)}</span>
				<span class="dettaglio-disponibilita ${esaurito ? "esaurito" : ""}">
					${esaurito ? "Esaurito" : a.disponibilita + " disponibili"}
				</span>
			</div>
			${!esaurito ? `
			` : ""}
			<button type="button" ${esaurito ? "disabled" : ""} class="btn" onclick="addToCart(${a.id})">Aggiungi al Carrello</button>
		</div>
	`;
}

function cambiaQuantita(delta) {
	const input = document.getElementById("quantita");
	const max = articoloCorrente ? articoloCorrente.disponibilita : 999;
	let valore = parseInt(input.value, 10) || 1;
	valore = Math.min(Math.max(valore + delta, 1), max);
	input.value = valore;
}

async function addToCart(articoloId) {
	const params = new URLSearchParams();
	params.set("articoloId", articoloId);
	params.set("quantita", 1);

    const res = await fetch(`${contextPath}/common/AddToCartServlet`, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params.toString(),
    });
	const popup = document.getElementById("successPopupInPage");
	popup.textContent = "Elemento inserito nel carrello.";
	popup.style.display = "block";
	setTimeout(() => {
		popup.textContent = "";
		popup.style.display = "none";
	}, 2000);

    if (res.ok) {
        console.log("Aggiunto al carrello");
    }
}

caricaDettaglio();