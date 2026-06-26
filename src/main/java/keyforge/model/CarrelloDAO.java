package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class CarrelloDAO implements DAO<Carrello, Integer> {
    @Override
    public Carrello findById(Integer id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM carrello WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Carrello(rs.getInt("id"), rs.getInt("utente_id"));
            }
            return null;
        }
    }

    public Carrello findByUtenteId(int utenteId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM carrello WHERE utente_id = ?");
            stmt.setInt(1, utenteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Carrello(rs.getInt("id"), rs.getInt("utente_id"));
            }
            return null;
        }
    }

    @Override
    public Collection<Carrello> findAll() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM carrello");
            ResultSet rs = stmt.executeQuery();
            ArrayList<Carrello> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Carrello(rs.getInt("id"), rs.getInt("utente_id")));
            }
            return list;
        }
    }

    @Override
    public void create(Carrello carrello) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO carrello (utente_id) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, carrello.getUtenteId());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                carrello.setId(keys.getInt(1));
            }
        }
    }

    @Override
    public void update(Carrello carrello) throws SQLException {
        // nessun attributo aggiornabile
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM carrello WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}