package keyforge.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Ordine implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int utenteId;
    private String stato;
    private String indirizzoSpedizione;
    private String tracking;
    private String note;
    private Timestamp dataOrdine;

    public Ordine() {}

    public Ordine(int id, int utenteId, String stato, String indirizzoSpedizione, String tracking, String note, Timestamp dataOrdine) {
        this.id = id;
        this.utenteId = utenteId;
        this.stato = stato;
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.tracking = tracking;
        this.note = note;
        this.dataOrdine = dataOrdine;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUtenteId() { return utenteId; }
    public void setUtenteId(int utenteId) { this.utenteId = utenteId; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    public String getIndirizzoSpedizione() { return indirizzoSpedizione; }
    public void setIndirizzoSpedizione(String indirizzoSpedizione) { this.indirizzoSpedizione = indirizzoSpedizione; }

    public String getTracking() { return tracking; }
    public void setTracking(String tracking) { this.tracking = tracking; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public Timestamp getDataOrdine() { return dataOrdine; }
    public void setDataOrdine(Timestamp dataOrdine) { this.dataOrdine = dataOrdine; }
}