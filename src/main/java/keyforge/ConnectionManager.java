package keyforge;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	public static Connection getConnection() throws NamingException, SQLException {
		Context initialCtx = new InitialContext();
		Context envCtx = (Context)initialCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/keyforge");
		Connection conn = ds.getConnection();
		return conn;
	}
}
