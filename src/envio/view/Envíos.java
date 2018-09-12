package envio.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import control.Conexi�n;
import envio.entity.Env�o;
import envio.entity.NoExisteEnv�o;

import view.InputTypes;

public class Env�os {

	private Conexi�n conexi�n;
	private Scanner scanner;
	public Env�os(Conexi�n conexi�n, Scanner scanner) {
		super();
		this.conexi�n = conexi�n;
		this.scanner = scanner;
	}
	public void add() throws SQLException{
		Env�o env�o = RegistroEnv�o.ingresar(scanner);
		String sql = "Insert into Env�o (codEnv�o, destinatario, tel�fono, costoAdicional) values(?,?,?,?)";
			conexi�n.consulta(sql);
			conexi�n.getSentencia().setInt(1, env�o.getCodEnv�o());
			conexi�n.getSentencia().setString(2, env�o.getDestinatario());
			conexi�n.getSentencia().setInt(3, env�o.getTel�fono());
			conexi�n.getSentencia().setDouble(4, env�o.getCostoAdicional());
			conexi�n.modificacion();
	}
	public void delete() throws NoExisteEnv�o{
		int codEnv�o= InputTypes.readInt("C�digo del env�o: ", scanner);
		String sql = "delete from env�o where codEnv�o = ?";
		try {
			conexi�n.consulta(sql);
			conexi�n.getSentencia().setInt(1, codEnv�o);
			conexi�n.modificacion();
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
		}
	}
	
	public void update() throws SQLException, NoExisteEnv�o {
		ResultSet resultSet;
		Env�o env�o=null;
		String destinatario;
		int tel�fono;
		double costoAdicional;
		int codEnv�o = InputTypes.readInt("C�digo del env�o: ", scanner);
		String sql = "select * from env�o where codEnv�o = ?";
		conexi�n.consulta(sql);
		conexi�n.getSentencia().setInt(1, codEnv�o);
		resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			destinatario = resultSet.getString("destinatario");
			codEnv�o = resultSet.getInt("codEnv�o");
			tel�fono = resultSet.getInt("tel�fono");
			costoAdicional = resultSet.getDouble("costoAdicional");
			env�o = new Env�o(destinatario, tel�fono, costoAdicional);
		} else {
			throw new NoExisteEnv�o();
		}

		System.out.println(env�o);
		Men�.men�Modificar(scanner, env�o);

		sql = "update env�o set destinatario = ?, tel�fono = ?, costoAdicional = ? where codEnv�o = ?";

		conexi�n.consulta(sql);
		conexi�n.getSentencia().setInt(1, env�o.getCodEnv�o());
		conexi�n.getSentencia().setString(2, env�o.getDestinatario());
		conexi�n.getSentencia().setInt(3, env�o.getTel�fono());
		conexi�n.getSentencia().setDouble(4, env�o.getCostoAdicional());
		conexi�n.modificacion();
	}
	public void list() throws SQLException {
		Env�o env�o;
		String sql = "select * from env�o ";
		conexi�n.consulta(sql);
		ResultSet resultSet = conexi�n.resultado();
		while (resultSet.next()) {
			env�o = new Env�o(resultSet.getString("destinatario"), resultSet.getInt("tel�fono"),
					resultSet.getDouble("costoAdicional"));
			System.out.println(env�o);
		}
	}
}
