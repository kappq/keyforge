<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ordini</title>
	  <style>
    *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

    body {
      font-family: system-ui, sans-serif;
      background: #f4f5f7;
      color: #1a1a2e;
    }

    header {
      background: #1a1a2e;
      color: #fff;
      padding: 1rem 2rem;
      font-size: 1.2rem;
      font-weight: 600;
      letter-spacing: .5px;
    }

    .container { max-width: 1200px; margin: 2rem auto; padding: 0 1.5rem; }

    .filters {
      display: flex;
      flex-wrap: wrap;
      gap: .75rem;
      background: #fff;
      border-radius: 10px;
      padding: 1.25rem 1.5rem;
      box-shadow: 0 1px 4px rgba(0,0,0,.08);
      margin-bottom: 1.5rem;
    }

    .filters input,
    .filters select {
      flex: 1 1 160px;
      padding: .5rem .75rem;
      border: 1px solid #d0d5dd;
      border-radius: 6px;
      font-size: .9rem;
      outline: none;
      transition: border-color .2s;
    }
    .filters input:focus,
    .filters select:focus { border-color: #4f6ef7; }

    .filters button {
      padding: .5rem 1.2rem;
      background: #4f6ef7;
      color: #fff;
      border: none;
      border-radius: 6px;
      font-size: .9rem;
      cursor: pointer;
      transition: background .2s;
    }
    .filters button:hover { background: #3a58d6; }

    .table-wrap {
      background: #fff;
      border-radius: 10px;
      box-shadow: 0 1px 4px rgba(0,0,0,.08);
      overflow-x: auto;
    }

    table { width: 100%; border-collapse: collapse; font-size: .9rem; }

    thead tr { background: #1a1a2e; color: #fff; }
    thead th { padding: .9rem 1rem; text-align: left; font-weight: 500; white-space: nowrap; }

    tbody tr { border-bottom: 1px solid #f0f0f0; transition: background .15s; }
    tbody tr:hover { background: #f8f9ff; }
    tbody td { padding: .8rem 1rem; vertical-align: top; }

    .badge {
      display: inline-block;
      padding: .2rem .65rem;
      border-radius: 20px;
      font-size: .78rem;
      font-weight: 600;
      text-transform: capitalize;
    }
    .badge-attesa { background: #fff3cd; color: #856404; }
    .badge-spedito { background: #cfe2ff; color: #084298; }
    .badge-consegnato { background: #d1e7dd; color: #0a3622; }
    .badge-annullato { background: #f8d7da; color: #58151c; }
    .badge-default { background: #e9ecef; color: #495057; }

    .empty, .error {
      text-align: center;
      padding: 3rem;
      color: #888;
      font-size: .95rem;
    }
    .error { color: #c0392b; }
  </style>
</head>
<body>
	<div class="container">
		<div class="filters">
			<input type="number" id="f-utente" placeholder="ID utente" min="1" />
			<select id="f-stato">
				<option value="">Tutti gli stati</option>
				<option value="in_attesa">In attesa</option>
				<option value="spedito">Spedito</option>
				<option value="consegnato">Consegnato</option>
				<option value="annullato">Annullato</option>
			</select>
			<input type="date" id="f-data-inizio" title="Data inizio" />
			<input type="date" id="f-data-fine" title="Data fine" />
			<button onclick="loadOrdini()">🔍 Filtra</button>
			<button onclick="resetFiltri()" style="background:#6c757d">✕ Reset</button>
		</div>
		<div class="table-wrap">
			<table>
				<thead>
					<tr>
						<th>#</th>
						<th>Utente</th>
						<th>Stato</th>
						<th>Indirizzo</th>
						<th>Tracking</th>
						<th>Note</th>
						<th>Data ordine</th>
					</tr>
				</thead>
				<tbody id="tbody"></tbody>
			</table>
		</div>
	</div>

	<script>
	const contextPath = "<%= request.getContextPath() %>";
	</script>
	<script src="<%= request.getContextPath() %>/static/js/ordini.js"></script>
</body>
</html>
