const filtri = ["nome", "prezzo-min", "prezzo-max", "disponibilita"];

let debounceTimer;

filtri.forEach(id => {
	document.getElementById(id).addEventListener("input", () => {
		clearTimeout(debounceTimer);
		debounceTimer = setTimeout(filtra, 300);
	})
});

async function filtra() {
	const params = new URLSearchParams();
	
	const nome = document.getElementById("nome").value;
	const prezzoMin = document.getElementById("prezzo-min").value;
	const prezzoMax = document.getElementById("prezzo-max").value;
	const disponibilita = document.getElementById("disponibilita").value;
	
	if (nome) {
		params.set("nome", nome);
	}
	if (prezzoMin) {
		params.set("prezzo-min", prezzoMin);
	}
	if (prezzoMax) {
		params.set("prezzo-max", prezzoMax);
	}
	if (disponibilita) {
		params.set("disponibilita", disponibilita);
	}
	
	const res = await fetch(`${contextPath}/common/ArticoliServlet?${params.toString()}`);
	if (!res.ok) {
		throw new Error(`Errore interno: ${res.status}`);
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
		<div class="card articolo">
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
		            <button type="button" ${a.disponibilita === 0 ? "disabled" : ""} class="btn" onclick="addToCart(${a.id})">Aggiungi al Carrello</button>
		        </div>
		    </div>
		</div>
	`).join("");
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
	setTimeout(() => {
		popup.textContent = "";
	}, 2000);

    if (res.ok) {
        console.log("Aggiunto al carrello");
    }
}

function clearFilters() {
	document.getElementById("nome").value = "";
	document.getElementById("prezzo-min").value = "";
	document.getElementById("prezzo-max").value = "";
	document.getElementById("disponibilita").value = "0";
	filtra();
}

filtra();
