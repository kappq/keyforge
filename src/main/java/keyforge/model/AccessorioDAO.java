package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AccessorioDAO implements DAO<Accessorio> {

    @Override
    public Accessorio findById(int articoloId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accessorio WHERE articolo_id = ?");
			stmt.setInt(1, articoloId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Accessorio(
					rs.getInt("articolo_id"),
					rs.getString("tipo")
				);
			} else {
				return null;
			}
        }
    }

    @Override
    public Collection<Accessorio> findAll() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accessorio");
			ResultSet rs = stmt.executeQuery();
			ArrayList<Accessorio> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Accessorio(
					rs.getInt("articolo_id"),
					rs.getString("tipo")
				));
			}
			return list;
        }
    }

    @Override
    public void create(Accessorio accessorio) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO accessorio (articolo_id, tipo) VALUES (?, ?)");
			stmt.setInt(1, accessorio.getArticoloId());
			stmt.setString(2, accessorio.getTipo());
			stmt.executeUpdate();
        }
    }

    @Override
    public void update(Accessorio accessorio) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE accessorio SET tipo = ? WHERE articolo_id = ?");
			stmt.setString(1, accessorio.getTipo());
			stmt.setInt(2, accessorio.getArticoloId());
			stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int articoloId) throws SQLException {
    	try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM accessorio WHERE articolo_id = ?");
			stmt.setInt(1, articoloId);
			stmt.executeUpdate();
    	}
    }
}
