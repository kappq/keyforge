package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class KeycapDAO implements DAO<Keycap> {
    @Override
    public Keycap findById(int articoloId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM keycap WHERE articolo_id = ?");
			stmt.setInt(1, articoloId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Keycap(
					rs.getInt("articolo_id"),
					rs.getString("materiale"),
					rs.getString("profilo")
				);
			} else {
				return null;
			}
        }
    }

    @Override
    public Collection<Keycap> findAll() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM keycap");
			ResultSet rs = stmt.executeQuery();
			ArrayList<Keycap> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Keycap(
					rs.getInt("articolo_id"),
					rs.getString("materiale"),
					rs.getString("profilo")
				));
			}
			return list;
        }
    }

    @Override
    public void create(Keycap keycap) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO keycap (articolo_id, materiale, profilo) VALUES (?, ?, ?)");
			stmt.setInt(1, keycap.getArticoloId());
			stmt.setString(2, keycap.getMateriale());
			stmt.setString(3, keycap.getProfilo());
			stmt.executeUpdate();
        }
    }

    @Override
    public void update(Keycap keycap) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE keycap SET materiale = ?, profilo = ? WHERE articolo_id = ?");
			stmt.setString(1, keycap.getMateriale());
			stmt.setString(2, keycap.getProfilo());
			stmt.setInt(3, keycap.getArticoloId());
			stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int articoloId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM keycap WHERE articolo_id = ?");
			stmt.setInt(1, articoloId);
			stmt.executeUpdate();
        }
    }
}
