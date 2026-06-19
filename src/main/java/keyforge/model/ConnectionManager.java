package keyforge.model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	public static Connection getConnection() throws SQLException {
		try {
			Context initialCtx = new InitialContext();
			DataSource ds = (DataSource)initialCtx.lookup("java:comp/env/jdbc/keyforge");
			return ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Database connection failed", e);
		}
	}
}
