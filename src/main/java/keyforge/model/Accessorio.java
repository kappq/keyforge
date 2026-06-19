package keyforge.model;

import java.io.Serializable;

public class Accessorio implements Serializable {
    private static final long serialVersionUID = 1L;

    private int articoloId;
    private String tipo;

    public Accessorio() {}

    public Accessorio(int articoloId, String tipo) {
        super();
        this.articoloId = articoloId;
        this.tipo = tipo;
    }

    public int getArticoloId() { return articoloId; }
    public void setArticoloId(int articoloId) { this.articoloId = articoloId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
