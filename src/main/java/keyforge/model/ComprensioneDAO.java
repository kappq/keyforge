package keyforge.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ComprensioneDAO {

    public Collection<Comprensione> findByOrdineId(int ordineId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comprensione WHERE ordine_id = ?");
            stmt.setInt(1, ordineId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Comprensione> list = new ArrayList<>();
            while (rs.next()) {
                list.add(extractComprensione(rs));
            }
            return list;
        }
    }

    public void create(Comprensione c) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO comprensione (ordine_id, articolo_id, quantita, prezzo_unitario, iva) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, c.getOrdineId());
            stmt.setInt(2, c.getArticoloId());
            stmt.setInt(3, c.getQuantita());
            stmt.setBigDecimal(4, c.getPrezzoUnitario());
            stmt.setBigDecimal(5, c.getIva());
            stmt.executeUpdate();
        }
    }

    public void delete(int ordineId, int articoloId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM comprensione WHERE ordine_id = ? AND articolo_id = ?"
            );
            stmt.setInt(1, ordineId);
            stmt.setInt(2, articoloId);
            stmt.executeUpdate();
        }
    }

    private static Comprensione extractComprensione(ResultSet rs) throws SQLException {
        return new Comprensione(
            rs.getInt("ordine_id"),
            rs.getInt("articolo_id"),
            rs.getInt("quantita"),
            rs.getBigDecimal("prezzo_unitario"),
            rs.getBigDecimal("iva")
        );
    }
}