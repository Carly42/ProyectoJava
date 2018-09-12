package control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexi�n {

	private Connection connection = null;
	private PreparedStatement sentencia = null;
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private String DB_URL = "jdbc:mariadb://127.0.0.1/";

	public Conexi�n(String user, String password, String dataBase) throws ClassNotFoundException, SQLException {
			Class.forName(JDBC_DRIVER);
			System.out.println("Conectado...");
			DB_URL = DB_URL + dataBase;
			connection = DriverManager.getConnection(DB_URL, user, password);

	}

	public void consulta(String sql) throws SQLException   {
		sentencia = connection.prepareStatement(sql);
	}

	public ResultSet resultado() throws SQLException  {
		return sentencia.executeQuery();
	}
	
	public int modificacion() throws SQLException {
		return sentencia.executeUpdate();
		
	}
	
	public void close() throws SQLException  {
		if (sentencia != null) {
			sentencia.close();
		}
		
		if (connection != null) {
			connection.close();
		}
	}

	public PreparedStatement getSentencia() {
		return sentencia;
	}
}
