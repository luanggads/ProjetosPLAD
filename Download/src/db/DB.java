package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	// metodo de carregamento das informações do banco
	private static Properties loadProperties() {
		try {

			FileInputStream fs = new FileInputStream("db.properties");
			Properties props = new Properties();
			props.load(fs);
			return props;

		} catch (IOException e) {

			throw new DbException(e.getMessage());
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			try {

				Properties props = loadProperties(); // carregando os dados do banco
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props); // realizando a conexão

			} catch (SQLException e) {

				throw new DbException(e.getMessage());
			}
		}
		return conn; // retornando a conexão
	}

	// fechando a conexão
	public static void closeConnection() {
		if (conn != null) {
			try {

				conn.close();

			} catch (SQLException e) {

				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {

				st.close();

			} catch (SQLException e) {

				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {

				rs.close();

			} catch (SQLException e) {

				throw new DbException(e.getMessage());
			}
		}
	}
}
