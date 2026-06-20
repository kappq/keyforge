package keyforge.model;

import java.io.Serializable;

public class Carrello implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int utenteId;

    public Carrello() {}

    public Carrello(int id, int utenteId) {
        this.id = id;
        this.utenteId = utenteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUtenteId() { return utenteId; }
    public void setUtenteId(int utenteId) { this.utenteId = utenteId; }
}