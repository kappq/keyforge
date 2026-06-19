package keyforge.model;

import java.io.Serializable;
import java.sql.Date;

public class Utente implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String email;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String password;
    private String telefono;
    private Integer carrelloId; // Integer because it can be NULL

    public Utente() {}

    public Utente(int id, String email, String nome, String cognome,
                  Date dataNascita, String password,
                  String telefono, Integer carrelloId) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.password = password;
        this.telefono = telefono;
        this.carrelloId = carrelloId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public Date getDataNascita() { return dataNascita; }
    public void setDataNascita(Date dataNascita) { this.dataNascita = dataNascita; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Integer getCarrelloId() { return carrelloId; }
    public void setCarrelloId(Integer carrelloId) { this.carrelloId = carrelloId; }
}