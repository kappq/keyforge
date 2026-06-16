package keyforge.model;

import java.io.Serializable;

public class Tastiera implements Serializable {
    private static final long serialVersionUID = 1L;

    private int articoloId;
    private int dimensione;
    private int peso;
    private String layout;

    public Tastiera() {}

    public Tastiera(int articoloId, int dimensione, int peso, String layout) {
        super();
        this.articoloId = articoloId;
        this.dimensione = dimensione;
        this.peso = peso;
        this.layout = layout;
    }

    public int getArticoloId() { return articoloId; }
    public void setArticoloId(int articoloId) { this.articoloId = articoloId; }

    public int getDimensione() { return dimensione; }
    public void setDimensione(int dimensione) { this.dimensione = dimensione; }

    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }

    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }
}
