const filtri = ["id-utente", "stato", "data-inizio", "data-fine"];

let debounceTimer;

filtri.forEach(id => {
	document.getElementById(id).addEventListener("input", () => {
		clearTimeout(debounceTimer);
		debounceTimer = setTimeout(filtra, 300);
	})
});

async function filtra() {
	const params = new URLSearchParams();

	const utente = document.getElementById('id-utente').value.trim();
	if (utente) params.set('utente-id', utente);

	const stato = document.getElementById('stato').value;
	if (stato) params.set('stato', stato);

	const dataInizio = document.getElementById('data-inizio').value;
	if (dataInizio) params.set('data-inizio', dataInizio);

	const dataFine = document.getElementById('data-fine').value;
	if (dataFine) params.set('data-fine', dataFine);

	const tbody = document.getElementById('tbody');
	tbody.innerHTML = '<tr><td colspan="7" class="empty">Caricamento…</td></tr>';

	try {
		const res = await fetch(contextPath + '/admin/OrdiniServlet?' + params.toString());
		if (!res.ok) throw new Error('Errore server: ' + res.status);
		const ordini = await res.json();

		if (ordini.length === 0) {
			tbody.innerHTML = '<tr><td colspan="7" class="empty">Nessun ordine trovato.</td></tr>';
			return;
		}

		tbody.innerHTML = ordini.map(o => `
			<tr>
				<td><strong>${o.id}</strong></td>
				<td>${o.utenteId}</td>
				<td><span>${o.stato ?? '—'}</span></td>
				<td>${o.indirizzoSpedizione ?? '—'}</td>
				<td>${o.tracking ?? '—'}</td>
				<td>${o.note ?? '—'}</td>
				<td>${o.dataOrdine}</td>
			</tr>
		`).join('');
	} catch (e) {
		tbody.innerHTML = `<tr><td colspan="7" class="error">️ ${e.message}</td></tr>`;
	}
}

function resetFiltri() {
	document.getElementById('id-utente').value = '';
	document.getElementById('stato').value = '';
	document.getElementById('data-inizio').value = '';
	document.getElementById('data-fine').value = '';
	filtra();
}

filtra();
