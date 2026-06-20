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
		try (Connection conn = ConnectionManager.getConnection()) {
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
					rs.getInt("disponibilita"),
					rs.getInt("dimensione"),
					rs.getInt("peso"),
					rs.getString("layout")
				);
				return articolo;
			} else {
				return null;
			}
		}
	}

	@Override
	public Collection<Articolo> findAll() throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
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
					rs.getInt("disponibilita"),
					rs.getInt("dimensione"),
					rs.getInt("peso"),
					rs.getString("layout")
				);
				list.add(articolo);
			}
			return list;
		}
	}

	@Override
	public void create(Articolo articolo) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO articolo (nome, descrizione, brand, prezzo, disponibilita, dimensione, peso, layout) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, articolo.getNome());
			stmt.setString(2, articolo.getDescrizione());
			stmt.setString(3, articolo.getBrand());
			stmt.setBigDecimal(4, articolo.getPrezzo());
			stmt.setInt(5, articolo.getDisponibilita());
			stmt.setInt(6, articolo.getDimensione());
			stmt.setInt(7, articolo.getPeso());
			stmt.setString(8, articolo.getLayout());
			stmt.executeUpdate();
		}
	}

	@Override
	public void update(Articolo articolo) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE articolo SET nome = ?, descrizione = ?, brand = ?, prezzo = ?, disponibilita = ?, dimensione = ?, peso = ?, layout = ? WHERE id = ?");
			stmt.setString(1, articolo.getNome());
			stmt.setString(2, articolo.getDescrizione());
			stmt.setString(3, articolo.getBrand());
			stmt.setBigDecimal(4, articolo.getPrezzo());
			stmt.setInt(5, articolo.getDisponibilita());
			stmt.setInt(6, articolo.getDimensione());
			stmt.setInt(7, articolo.getPeso());
			stmt.setString(8, articolo.getLayout());
			stmt.setInt(9, articolo.getId());
			stmt.executeUpdate();
		}
	}

	@Override
	public void delete(int id) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM articolo WHERE id = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	public Collection<Articolo> findFiltered(int prezzoMin, int prezzoMax, int disponibilita) throws SQLException {
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM articolo WHERE ? <= prezzo AND prezzo <= ? AND disponibilita >= ?");
			stmt.setInt(1, prezzoMin);
			stmt.setInt(2, prezzoMax);
			stmt.setInt(3, disponibilita);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Articolo> list = new ArrayList<Articolo>();
			while (rs.next()) {
				Articolo articolo = new Articolo(
					rs.getInt("id"),
					rs.getString("nome"),
					rs.getString("descrizione"),
					rs.getString("brand"),
					rs.getBigDecimal("prezzo"),
					rs.getInt("disponibilita"),
					rs.getInt("dimensione"),
					rs.getInt("peso"),
					rs.getString("layout")
				);
				list.add(articolo);
			}
			return list;
		}
	}
}
