package keyforge.model;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO<T, U> {
	public T findById(U id) throws SQLException;
	public Collection<T> findAll() throws SQLException;
	public void create(T t) throws SQLException;
	public void update(T t) throws SQLException;
	public void delete(U id) throws SQLException;
}
