package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ArticoloDAO implements DAO<Articolo> {
	@Override
	public Articolo findById(int id) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM articolo WHERE id = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			Articolo articolo = new Articolo(
				rs.getInt("id"),
				rs.getString("nome"),
				rs.getString("descrizione"),
				rs.getString("brand"),
				rs.getBigDecimal("prezzo"),
				rs.getInt("disponibilita")
			);
			return articolo;
		} else {
			return null;
		}
	}

	@Override
	public Collection<Articolo> findAll() throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM articolo");
		ResultSet rs = stmt.executeQuery();
		ArrayList<Articolo> list = new ArrayList<Articolo>();
		while (rs.next()) {
			Articolo articolo = new Articolo(
				rs.getInt("id"),
				rs.getString("nome"),
				rs.getString("descrizione"),
				rs.getString("brand"),
				rs.getBigDecimal("prezzo"),
				rs.getInt("disponibilita")
			);
			list.add(articolo);
		}
		return list;
	}

	@Override
	public void create(Articolo articolo) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO articolo (nome, descrizione, brand, prezzo, disponibilita) VALUES (?, ?, ?, ?, ?)");
		stmt.setString(1, articolo.getNome());
		stmt.setString(2, articolo.getDescrizione());
		stmt.setString(3, articolo.getBrand());
		stmt.setBigDecimal(4, articolo.getPrezzo());
		stmt.setInt(5, articolo.getDisponibilita());
		stmt.executeUpdate();
	}

	@Override
	public void update(Articolo articolo) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement stmt = conn.prepareStatement("UPDATE articolo SET nome = ?, descrizione = ?, brand = ?, prezzo = ?, disponibilita = ? WHERE id = ?");
		stmt.setString(1, articolo.getNome());
		stmt.setString(2, articolo.getDescrizione());
		stmt.setString(3, articolo.getBrand());
		stmt.setBigDecimal(4, articolo.getPrezzo());
		stmt.setInt(5, articolo.getDisponibilita());
		stmt.setInt(6, articolo.getId());
		stmt.executeUpdate();
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM articolo WHERE id = ?");
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}
}
