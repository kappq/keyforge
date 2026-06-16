package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ImmagineDAO implements DAO<Immagine> {
    @Override
    public Immagine findById(int id) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM immagine WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Immagine(
                rs.getInt("id"),
                rs.getInt("articolo_id"),
                rs.getBytes("dati")
            );
        } else {
            return null;
        }
    }

    public Collection<Immagine> findByArticoloId(int articoloId) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM immagine WHERE articolo_id = ?");
        stmt.setInt(1, articoloId);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Immagine> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Immagine(
                rs.getInt("id"),
                rs.getInt("articolo_id"),
                rs.getBytes("dati")
            ));
        }
        return list;
    }

    @Override
    public Collection<Immagine> findAll() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM immagine");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Immagine> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Immagine(
                rs.getInt("id"),
                rs.getInt("articolo_id"),
                rs.getBytes("dati")
            ));
        }
        return list;
    }

    @Override
    public void create(Immagine immagine) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO immagine (articolo_id, dati) VALUES (?, ?)");
        stmt.setInt(1, immagine.getArticoloId());
        stmt.setBytes(2, immagine.getDati());
        stmt.executeUpdate();
    }

    @Override
    public void update(Immagine immagine) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE immagine SET articolo_id = ?, dati = ? WHERE id = ?");
        stmt.setInt(1, immagine.getArticoloId());
        stmt.setBytes(2, immagine.getDati());
        stmt.setInt(3, immagine.getId());
        stmt.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM immagine WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
