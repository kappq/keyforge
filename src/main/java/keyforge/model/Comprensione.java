package keyforge.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Comprensione implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ordineId;
    private int articoloId;
    private int quantita;
    private BigDecimal prezzoUnitario;
    private BigDecimal iva;

    public Comprensione() {}

    public Comprensione(int ordineId, int articoloId, int quantita, BigDecimal prezzoUnitario, BigDecimal iva) {
        this.ordineId = ordineId;
        this.articoloId = articoloId;
        this.quantita = quantita;
        this.prezzoUnitario = prezzoUnitario;
        this.iva = iva;
    }

    public int getOrdineId() { return ordineId; }
    public void setOrdineId(int ordineId) { this.ordineId = ordineId; }
    public int getArticoloId() { return articoloId; }
    public void setArticoloId(int articoloId) { this.articoloId = articoloId; }
    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }
    public BigDecimal getPrezzoUnitario() { return prezzoUnitario; }
    public void setPrezzoUnitario(BigDecimal prezzoUnitario) { this.prezzoUnitario = prezzoUnitario; }
    public BigDecimal getIva() { return iva; }
    public void setIva(BigDecimal iva) { this.iva = iva; }

    // prezzo totale della riga, IVA inclusa
    public BigDecimal getPrezzoTotale() {
        BigDecimal imponibile = prezzoUnitario.multiply(BigDecimal.valueOf(quantita));
        BigDecimal moltiplicatoreIva = BigDecimal.ONE.add(iva.divide(BigDecimal.valueOf(100)));
        return imponibile.multiply(moltiplicatoreIva);
    }
}