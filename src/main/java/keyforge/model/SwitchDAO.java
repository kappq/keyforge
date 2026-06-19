package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class SwitchDAO implements DAO<Switch> {
    @Override
    public Switch findById(int articoloId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM switch WHERE articolo_id = ?");
			stmt.setInt(1, articoloId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Switch(
					rs.getInt("articolo_id"),
					rs.getString("compatibilita"),
					rs.getInt("attivazione")
				);
			} else {
				return null;
			}
        }
    }

    @Override
    public Collection<Switch> findAll() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM switch");
			ResultSet rs = stmt.executeQuery();
			ArrayList<Switch> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Switch(
					rs.getInt("articolo_id"),
					rs.getString("compatibilita"),
					rs.getInt("attivazione")
				));
			}
			return list;
        }
    }

    @Override
    public void create(Switch sw) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO switch (articolo_id, compatibilita, attivazione) VALUES (?, ?, ?)");
			stmt.setInt(1, sw.getArticoloId());
			stmt.setString(2, sw.getCompatibilita());
			stmt.setInt(3, sw.getAttivazione());
			stmt.executeUpdate();
        }
    }

    @Override
    public void update(Switch sw) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE switch SET compatibilita = ?, attivazione = ? WHERE articolo_id = ?");
			stmt.setString(1, sw.getCompatibilita());
			stmt.setInt(2, sw.getAttivazione());
			stmt.setInt(3, sw.getArticoloId());
			stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int articoloId) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM switch WHERE articolo_id = ?");
			stmt.setInt(1, articoloId);
			stmt.executeUpdate();
        }
    }
}
