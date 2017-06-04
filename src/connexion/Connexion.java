package connexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 *
 * @author Louis
 */
public class Connexion {

	private static Connection connectionMySQL;

	// Ouvre une connexion s'il n'en existe pas déja une
	public static Connection getInstance() throws IOException, SQLException {
		if (connectionMySQL == null) {
			try {
				MysqlDataSource mds = new MysqlDataSource();
				mds.setUser("root");
				mds.setPassword("stayfitadmin");
				mds.setServerName("54.214.204.132");
				mds.setUrl(mds.getUrl() + "projetJEE");
				return (mds.getConnection());
			} catch (SQLException e) {
				System.err.println("Erreur lors de la connection : "
						+ e.getMessage());
			}
		}
		return connectionMySQL;
	}

	public void closeConnection() throws SQLException {
		connectionMySQL.close();
	}

}