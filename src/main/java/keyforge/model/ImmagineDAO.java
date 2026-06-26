package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ImmagineDAO implements DAO<Immagine, Integer> {
    @Override
    public Immagine findById(Integer id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM immagine WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			return rs.next() ? extractImmagine(rs) : null;
        }
    }

    @Override
    public Collection<Immagine> findAll() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM immagine");
			ResultSet rs = stmt.executeQuery();
			ArrayList<Immagine> list = new ArrayList<>();
			while (rs.next()) {
				list.add(extractImmagine(rs));
			}
			return list;
        }
    }

    @Override
    public void create(Immagine immagine) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO immagine (articolo_id, dati) VALUES (?, ?)");
			stmt.setInt(1, immagine.getArticoloId());
			stmt.setBytes(2, immagine.getDati());
			stmt.executeUpdate();
        }
    }

    @Override
    public void update(Immagine immagine) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE immagine SET articolo_id = ?, dati = ? WHERE id = ?");
			stmt.setInt(1, immagine.getArticoloId());
			stmt.setBytes(2, immagine.getDati());
			stmt.setInt(3, immagine.getId());
			stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM immagine WHERE id = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
        }
    }

    public Collection<Immagine> findByArticoloId(int articoloId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM immagine WHERE articolo_id = ?");
			stmt.setInt(1, articoloId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Immagine> list = new ArrayList<>();
			while (rs.next()) {
				list.add(extractImmagine(rs));
			}
			return list;
        }
    }
    
    private Immagine extractImmagine(ResultSet rs) throws SQLException {
    	return new Immagine(
			rs.getInt("id"),
			rs.getInt("articolo_id"),
			rs.getBytes("dati")
		);
    }
}
