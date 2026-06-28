function badgeClass(stato) {
	if (!stato) return 'badge-default';
	const s = stato.toLowerCase();
	if (s.includes('in_attesa')) return 'badge-attesa';
	if (s.includes('spedito')) return 'badge-spedito';
	if (s.includes('consegnato')) return 'badge-consegnato';
	if (s.includes('annullato')) return 'badge-annullato';
	return 'badge-default';
}

function formatDate(ts) {
	if (!ts) return '—';
	return new Date(ts).toLocaleString('it-IT', {
		day: '2-digit', month: '2-digit', year: 'numeric',
		hour: '2-digit', minute: '2-digit'
	});
}

async function loadOrdini() {
	const params = new URLSearchParams();

	const utente = document.getElementById('f-utente').value.trim();
	if (utente) params.set('utente-id', utente);

	const stato = document.getElementById('f-stato').value;
	if (stato) params.set('stato', stato);

	const dataInizio = document.getElementById('f-data-inizio').value;
	if (dataInizio) params.set('data-inizio', dataInizio);

	const dataFine = document.getElementById('f-data-fine').value;
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
				<td><span class="badge ${badgeClass(o.stato)}">${o.stato ?? '—'}</span></td>
				<td>${o.indirizzoSpedizione ?? '—'}</td>
				<td>${o.tracking ?? '—'}</td>
				<td>${o.note ?? '—'}</td>
				<td>${formatDate(o.dataOrdine)}</td>
			</tr>
		`).join('');
	} catch (e) {
		tbody.innerHTML = `<tr><td colspan="7" class="error">⚠️ ${e.message}</td></tr>`;
	}
}

function resetFiltri() {
	document.getElementById('f-utente').value = '';
	document.getElementById('f-stato').value = '';
	document.getElementById('f-data-inizio').value = '';
	document.getElementById('f-data-fine').value = '';
	loadOrdini();
}

loadOrdini();
