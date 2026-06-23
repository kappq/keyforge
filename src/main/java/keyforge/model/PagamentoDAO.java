package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class PagamentoDAO implements DAO<Pagamento> {
	
    private static Pagamento fromResultSet(ResultSet rs) throws SQLException {
        return new Pagamento(
            rs.getInt("id"),
            rs.getInt("ordine_id"),
            rs.getString("stato"),
            rs.getBigDecimal("importo"),
            rs.getString("valuta"),
            rs.getTimestamp("data")
        );
    }

    @Override
    public Pagamento findById(int id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM pagamento WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return fromResultSet(rs);
            return null;
        }
    }

    public Pagamento findByOrdineId(int ordineId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM pagamento WHERE ordine_id = ?");
            stmt.setInt(1, ordineId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return fromResultSet(rs);
            return null;
        }
    }

    @Override
    public Collection<Pagamento> findAll() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM pagamento ORDER BY data DESC");
            ResultSet rs = stmt.executeQuery();
            ArrayList<Pagamento> list = new ArrayList<>();
            while (rs.next()) list.add(fromResultSet(rs));
            return list;
        }
    }

    @Override
    public void create(Pagamento pagamento) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO pagamento (ordine_id, stato, importo, valuta) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, pagamento.getOrdineId());
            stmt.setString(2, pagamento.getStato());
            stmt.setBigDecimal(3, pagamento.getImporto());
            stmt.setString(4, pagamento.getValuta());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) pagamento.setId(keys.getInt(1));
        }
    }

    @Override
    public void update(Pagamento pagamento) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE pagamento SET stato = ?, importo = ?, valuta = ? WHERE id = ?");
            stmt.setString(1, pagamento.getStato());
            stmt.setBigDecimal(2, pagamento.getImporto());
            stmt.setString(3, pagamento.getValuta());
            stmt.setInt(4, pagamento.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM pagamento WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}