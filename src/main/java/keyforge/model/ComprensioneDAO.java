package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Map.Entry;

public class ComprensioneDAO implements DAO<Comprensione, Entry<Integer, Integer>> {
	@Override
	public Comprensione findById(Entry<Integer, Integer> id) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comprensione WHERE ordine_id = ? AND articolo_id = ? AND quantita = ?");
			stmt.setInt(1, id.getKey());
			stmt.setInt(2, id.getValue());
			ResultSet rs = stmt.executeQuery();
			return rs.next() ? extractComprensione(rs) : null;
		}
	}

	@Override
	public Collection<Comprensione> findAll() throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comprensione");
			ResultSet rs = stmt.executeQuery();
			ArrayList<Comprensione> list = new ArrayList<>();
			while (rs.next()) {
				list.add(extractComprensione(rs));
			}
			return list;
		}
	}

	@Override
	public void create(Comprensione comprensione) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO comprensione VALUES (?, ?, ?)");
			stmt.setInt(1, comprensione.getOrdineId());
			stmt.setInt(2, comprensione.getArticoloId());
			stmt.setInt(3, comprensione.getQuantita());
			stmt.executeUpdate();
		}
	}

	@Override
	public void update(Comprensione comprensione) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE comprensione SET articolo_id = ?, ordine_id = ?, quantita = ?");
			stmt.setInt(1, comprensione.getOrdineId());
			stmt.setInt(2, comprensione.getArticoloId());
			stmt.setInt(3, comprensione.getQuantita());
			stmt.executeUpdate();
		}
	}

	@Override
	public void delete(Entry<Integer, Integer> id) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM comprensione WHERE ordine_id = ? AND articolo_id = ?");
			stmt.setInt(1, id.getKey());
			stmt.setInt(2, id.getValue());
			stmt.executeUpdate();
		}
	}
	
	private Comprensione extractComprensione(ResultSet rs) throws SQLException {
		return new Comprensione(
			rs.getInt("ordine_id"),
			rs.getInt("articolo_id"),
			rs.getInt("quantita")
		);
	}
}
