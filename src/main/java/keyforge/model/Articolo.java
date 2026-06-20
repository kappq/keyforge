package keyforge.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Articolo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private String descrizione;
	private String brand;
	private BigDecimal prezzo;
	private int disponibilita;
    private int dimensione;
    private int peso;
    private String layout;
	
	public Articolo() {}

	public Articolo(int id, String nome, String descrizione, String brand, BigDecimal prezzo, int disponibilita, int dimensione, int peso, String layout) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.brand = brand;
		this.prezzo = prezzo;
		this.disponibilita = disponibilita;
        this.dimensione = dimensione;
        this.peso = peso;
        this.layout = layout;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getDescrizione() { return descrizione; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

	public String getBrand() { return brand; }
	public void setBrand(String brand) { this.brand = brand; }

	public BigDecimal getPrezzo() { return prezzo; }
	public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }

	public int getDisponibilita() { return disponibilita; }
	public void setDisponibilita(int disponibilita) { this.disponibilita = disponibilita; }

    public int getDimensione() { return dimensione; }
    public void setDimensione(int dimensione) { this.dimensione = dimensione; }

    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }

    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }
}
