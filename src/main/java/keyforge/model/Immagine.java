package keyforge.model;

import java.io.Serializable;

public class Immagine implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int articoloId;
    private byte[] dati;

    public Immagine() {}

    public Immagine(int id, int articoloId, byte[] dati) {
        super();
        this.id = id;
        this.articoloId = articoloId;
        this.dati = dati;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getArticoloId() { return articoloId; }
    public void setArticoloId(int articoloId) { this.articoloId = articoloId; }

    public byte[] getDati() { return dati; }
    public void setDati(byte[] dati) { this.dati = dati; }
}
