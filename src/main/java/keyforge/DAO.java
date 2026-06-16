package keyforge;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO<T> {
	public T findById(int id) throws SQLException;
	public Collection<T> findAll() throws SQLException;
	public void create(T t) throws SQLException;
	public void update(T t) throws SQLException;
	public void delete(int id) throws SQLException;
}
