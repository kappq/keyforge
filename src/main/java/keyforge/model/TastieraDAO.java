package keyforge.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class TastieraDAO implements DAO<Tastiera> {

    @Override
    public Tastiera findById(int articoloId) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tastiera WHERE articolo_id = ?");
        stmt.setInt(1, articoloId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Tastiera(
                rs.getInt("articolo_id"),
                rs.getInt("dimensione"),
                rs.getInt("peso"),
                rs.getString("layout")
            );
        } else {
            return null;
        }
    }

    @Override
    public Collection<Tastiera> findAll() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tastiera");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Tastiera> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Tastiera(
                rs.getInt("articolo_id"),
                rs.getInt("dimensione"),
                rs.getInt("peso"),
                rs.getString("layout")
            ));
        }
        return list;
    }

    @Override
    public void create(Tastiera tastiera) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO tastiera (articolo_id, dimensione, peso, layout) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, tastiera.getArticoloId());
        stmt.setInt(2, tastiera.getDimensione());
        stmt.setInt(3, tastiera.getPeso());
        stmt.setString(4, tastiera.getLayout());
        stmt.executeUpdate();
    }

    @Override
    public void update(Tastiera tastiera) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE tastiera SET dimensione = ?, peso = ?, layout = ? WHERE articolo_id = ?");
        stmt.setInt(1, tastiera.getDimensione());
        stmt.setInt(2, tastiera.getPeso());
        stmt.setString(3, tastiera.getLayout());
        stmt.setInt(4, tastiera.getArticoloId());
        stmt.executeUpdate();
    }

    @Override
    public void delete(int articoloId) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM tastiera WHERE articolo_id = ?");
        stmt.setInt(1, articoloId);
        stmt.executeUpdate();
    }
}
