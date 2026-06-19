package keyforge.model;

import java.io.Serializable;

public class Keycap implements Serializable {
    private static final long serialVersionUID = 1L;

    private int articoloId;
    private String materiale;
    private String profilo;

    public Keycap() {}

    public Keycap(int articoloId, String materiale, String profilo) {
        super();
        this.articoloId = articoloId;
        this.materiale = materiale;
        this.profilo = profilo;
    }

    public int getArticoloId() { return articoloId; }
    public void setArticoloId(int articoloId) { this.articoloId = articoloId; }

    public String getMateriale() { return materiale; }
    public void setMateriale(String materiale) { this.materiale = materiale; }

    public String getProfilo() { return profilo; }
    public void setProfilo(String profilo) { this.profilo = profilo; }
}
