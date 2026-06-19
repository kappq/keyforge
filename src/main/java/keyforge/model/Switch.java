package keyforge.model;

import java.io.Serializable;

public class Switch implements Serializable {
    private static final long serialVersionUID = 1L;

    private int articoloId;
    private String compatibilita;
    private int attivazione;

    public Switch() {}

    public Switch(int articoloId, String compatibilita, int attivazione) {
        super();
        this.articoloId = articoloId;
        this.compatibilita = compatibilita;
        this.attivazione = attivazione;
    }

    public int getArticoloId() { return articoloId; }
    public void setArticoloId(int articoloId) { this.articoloId = articoloId; }

    public String getCompatibilita() { return compatibilita; }
    public void setCompatibilita(String compatibilita) { this.compatibilita = compatibilita; }

    public int getAttivazione() { return attivazione; }
    public void setAttivazione(int attivazione) { this.attivazione = attivazione; }
}
