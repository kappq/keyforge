package keyforge.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UtenteDAO implements DAO<Utente> {

    @Override
    public Utente findById(int id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt =
                conn.prepareStatement("SELECT * FROM utente WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractUtente(rs);
            }
            return null;
        }
    }

    @Override
    public Collection<Utente> findAll() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt =
                conn.prepareStatement("SELECT * FROM utente");
            ResultSet rs = stmt.executeQuery();
            ArrayList<Utente> utenti = new ArrayList<>();
            while (rs.next()) {
                utenti.add(extractUtente(rs));
            }
            return utenti;
        }
    }

    @Override
    public void create(Utente utente) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO utente (email, nome, cognome, data_nascita, password, telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, utente.getEmail());
            stmt.setString(2, utente.getNome());
            stmt.setString(3, utente.getCognome());
            stmt.setDate(4, utente.getDataNascita());
            stmt.setString(5, utente.getPassword());
            stmt.setString(6, utente.getTelefono());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                utente.setId(keys.getInt(1));
            }
        }
    }

    @Override
    public void update(Utente utente) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE utente SET email=?, nome=?, cognome=?, " +
                "data_nascita=?, password=?, telefono=? WHERE id=?"
            );
            stmt.setString(1, utente.getEmail());
            stmt.setString(2, utente.getNome());
            stmt.setString(3, utente.getCognome());
            stmt.setDate(4, utente.getDataNascita());
            stmt.setString(5, utente.getPassword());
            stmt.setString(6, utente.getTelefono());
            stmt.setInt(7, utente.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt =
                conn.prepareStatement("DELETE FROM utente WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Utente findByEmail(String email) throws SQLException {
    	try (Connection conn = ConnectionManager.getConnection()) {
    		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM utente WHERE email = ?");
    		stmt.setString(1, email);
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next()) {
    			return extractUtente(rs);
    		} else {
    			return null;
    		}
    	}
    }

    public static boolean emailExists(String email) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM utente WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    private Utente extractUtente(ResultSet rs) throws SQLException {
        return new Utente(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("nome"),
            rs.getString("cognome"),
            rs.getDate("data_nascita"),
            rs.getString("password"),
            rs.getString("telefono")
        );
    }
}