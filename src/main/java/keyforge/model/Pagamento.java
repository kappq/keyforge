package keyforge.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int ordineId;
    private String stato;
    private BigDecimal importo;
    private String valuta;
    private Timestamp data;

    public Pagamento() {}

    public Pagamento(int id, int ordineId, String stato, BigDecimal importo,
                     String valuta, Timestamp data) {
        this.id = id;
        this.ordineId = ordineId;
        this.stato = stato;
        this.importo = importo;
        this.valuta = valuta;
        this.data = data;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrdineId() { return ordineId; }
    public void setOrdineId(int ordineId) { this.ordineId = ordineId; }
    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }
    public BigDecimal getImporto() { return importo; }
    public void setImporto(BigDecimal importo) { this.importo = importo; }
    public String getValuta() { return valuta; }
    public void setValuta(String valuta) { this.valuta = valuta; }
    public Timestamp getData() { return data; }
    public void setData(Timestamp data) { this.data = data; }
}