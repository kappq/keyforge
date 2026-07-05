<%@ page import="keyforge.model.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.math.BigDecimal,java.math.RoundingMode" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Ordine ordine = (Ordine)request.getAttribute("ordine");
    Utente utente = (Utente)request.getAttribute("utente");
    Collection<Comprensione> righe = (Collection<Comprensione>)request.getAttribute("righe");
    BigDecimal totale = (BigDecimal)request.getAttribute("totale");
    
    ArticoloDAO articoloDAO = new ArticoloDAO();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fattura Ordine #<%= ordine.getId() %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/fattura.css">
</head>
<body>
    <h1>Fattura Ordine #<%= ordine.getId() %></h1>

    <div class="info">
        <p><strong>Data Ordine:</strong> <%= ordine.getDataOrdine() %></p>
        <p><strong>Tracking:</strong> <%= ordine.getTracking() %></p>
        <p><strong>Stato:</strong> <%= ordine.getStato() %></p>
        <p><strong>Cliente:</strong> <%= utente.getNome() %> <%= utente.getCognome() %> (<%= utente.getEmail() %>)</p>
        <p><strong>Indirizzo di Spedizione:</strong> <%= ordine.getIndirizzoSpedizione() %></p>
    </div>

    <table>
        <thead>
            <tr>
                <th>Prodotto</th>
                <th>Prezzo Unitario</th>
                <th>IVA</th>
                <th>Quantità</th>
                <th>Totale</th>
            </tr>
        </thead>
        <tbody>
            <% for (Comprensione riga : righe) { %>
            <tr>
                <td><%= articoloDAO.findById(riga.getArticoloId()).getNome() %></td>
                <td><%= riga.getPrezzoUnitario() %> €</td>
                <td><%= riga.getIva() %>%</td>
                <td><%= riga.getQuantita() %></td>
                <td><%= riga.getPrezzoTotale().setScale(2, RoundingMode.HALF_UP) %> €</td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <div class="totale">
        TOTALE PAGATO: € <%= totale.setScale(2, RoundingMode.HALF_UP) %>
    </div>

    <div class="no-print">
        <button onclick="window.print()">Stampa / Salva come PDF</button>
    </div>

</body>
</html>
