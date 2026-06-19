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
                "INSERT INTO utente " +
                "(email, nome, cognome, data_nascita, password, telefono, carrello_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, utente.getEmail());
            stmt.setString(2, utente.getNome());
            stmt.setString(3, utente.getCognome());
            stmt.setDate(4, utente.getDataNascita());
            stmt.setString(5, utente.getPassword());
            stmt.setString(6, utente.getTelefono());

            if (utente.getCarrelloId() == null)
                stmt.setNull(7, Types.INTEGER);
            else
                stmt.setInt(7, utente.getCarrelloId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Utente utente) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE utente SET " +
                "email=?, nome=?, cognome=?, data_nascita=?, " +
                "password=?, telefono=?, carrello_id=? " +
                "WHERE id=?"
            );

            stmt.setString(1, utente.getEmail());
            stmt.setString(2, utente.getNome());
            stmt.setString(3, utente.getCognome());
            stmt.setDate(4, utente.getDataNascita());
            stmt.setString(5, utente.getPassword());
            stmt.setString(6, utente.getTelefono());

            if (utente.getCarrelloId() == null)
                stmt.setNull(7, Types.INTEGER);
            else
                stmt.setInt(7, utente.getCarrelloId());

            stmt.setInt(8, utente.getId());

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

    public static boolean emailExists(String email) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {

            PreparedStatement stmt =
                conn.prepareStatement(
                    "SELECT 1 FROM utente WHERE email = ?"
                );

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
    }

    private Utente extractUtente(ResultSet rs) throws SQLException {
        int carrelloId = rs.getInt("carrello_id");

        return new Utente(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("nome"),
            rs.getString("cognome"),
            rs.getDate("data_nascita"),
            rs.getString("password"),
            rs.getString("telefono"),
            rs.wasNull() ? null : carrelloId
        );
    }
}