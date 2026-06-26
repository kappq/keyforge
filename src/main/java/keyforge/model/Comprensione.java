package keyforge.model;

import java.io.Serializable;

public class Comprensione implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int ordineId;
	private int articoloId;
	private int quantita;
	
	public Comprensione() {}
	
	public Comprensione(int ordineId, int articoloId, int quantita) {
		this.ordineId = ordineId;
		this.articoloId = articoloId;
		this.quantita = quantita;
	}

	public int getOrdineId() { return ordineId; }
	public void setOrdineId(int ordineId) { this.ordineId = ordineId; }
	
	public int getArticoloId() { return articoloId; }
	public void setArticoloId(int articoloId) { this.articoloId = articoloId; }
	
	public int getQuantita() { return quantita; }
	public void setQuantita(int quantita) { this.quantita = quantita; }
}
