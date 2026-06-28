package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class OrdineDAO implements DAO<Ordine, Integer> {
    @Override
    public Ordine findById(Integer id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ordine WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? extractOrdine(rs) : null;
        }
    }

    public Collection<Ordine> findByUtenteId(int utenteId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ordine WHERE utente_id = ? ORDER BY data_ordine DESC");
            stmt.setInt(1, utenteId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Ordine> list = new ArrayList<>();
            while (rs.next()) {
            	list.add(extractOrdine(rs));
            }
            return list;
        }
    }

    @Override
    public Collection<Ordine> findAll() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ordine ORDER BY data_ordine DESC");
            ResultSet rs = stmt.executeQuery();
            ArrayList<Ordine> list = new ArrayList<>();
            while (rs.next()) {
            	list.add(extractOrdine(rs));
            }
            return list;
        }
    }

    @Override
    public void create(Ordine ordine) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO ordine (utente_id, stato, indirizzo_spedizione, tracking, note) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, ordine.getUtenteId());
            stmt.setString(2, ordine.getStato());
            stmt.setString(3, ordine.getIndirizzoSpedizione());
            stmt.setString(4, ordine.getTracking());
            stmt.setString(5, ordine.getNote());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
            	ordine.setId(keys.getInt(1));
            }
        }
    }

    @Override
    public void update(Ordine ordine) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE ordine SET stato = ?, indirizzo_spedizione = ?, tracking = ?, note = ? WHERE id = ?");
            stmt.setString(1, ordine.getStato());
            stmt.setString(2, ordine.getIndirizzoSpedizione());
            stmt.setString(3, ordine.getTracking());
            stmt.setString(4, ordine.getNote());
            stmt.setInt(5, ordine.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM ordine WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private static Ordine extractOrdine(ResultSet rs) throws SQLException {
        return new Ordine(
            rs.getInt("id"),
            rs.getInt("utente_id"),
            rs.getString("stato"),
            rs.getString("indirizzo_spedizione"),
            rs.getString("tracking"),
            rs.getString("note"),
            rs.getTimestamp("data_ordine")
        );
    }
    
    public Collection<Ordine> findFiltered(int utenteId, String stato, String dataInizio, String dataFine) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
        	PreparedStatement stmt = conn.prepareStatement(
        		"SELECT * FROM ordine WHERE (utente_id = ? OR 0 = ?) AND (stato = ? OR \"\" = ?) AND ? <= data_ordine AND data_ordine <= ? ORDER BY data_ordine DESC"
        	);
        	
        	stmt.setInt(1, utenteId);
        	stmt.setInt(2, utenteId);
        	stmt.setString(3, stato);
        	stmt.setString(4, stato);
        	stmt.setString(5, dataInizio);
        	stmt.setString(6, dataFine);

            ResultSet rs = stmt.executeQuery();

            ArrayList<Ordine> list = new ArrayList<>();
            while (rs.next()) {
            	list.add(extractOrdine(rs));
            }

            return list;
        }
    }
}
