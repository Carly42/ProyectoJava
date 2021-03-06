package cliente.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import cliente.entity.Cliente;
import cliente.entity.NoExisteCliente;
import control.Conexi�n;
import view.InputTypes;

public class Clientes {

	private Conexi�n conexi�n;
	private Scanner scanner;

	public Clientes(Conexi�n conexi�n, Scanner scanner) {
		super();
		this.conexi�n = conexi�n;
		this.scanner = scanner;
	}
	public void add() {
		Cliente cliente = RegistroCliente.ingresar(scanner);
		String sql = "Insert into Cliente (codCliente, nombre, cI, tel�fono, celular, direcci�n, puntos) values(?,?,?,?,?,?,?)";
		try {
			conexi�n.consulta(sql);
			conexi�n.getSentencia().setInt(1, cliente.getCodCliente());
			conexi�n.getSentencia().setString(2, cliente.getNombre());
			conexi�n.getSentencia().setInt(3, cliente.getCI());
			conexi�n.getSentencia().setInt(4, cliente.getTel�fono());
			conexi�n.getSentencia().setInt(5, cliente.getCelular());
			conexi�n.getSentencia().setString(6, cliente.getDirecci�n());
			conexi�n.getSentencia().setInt(7, cliente.getPuntos());
			conexi�n.modificacion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update() throws SQLException, NoExisteCliente {
		ResultSet resultSet;
		Cliente cliente;
		String nombre;
		int cI;
		int tel�fono;
		int celular;
		String direcci�n;
		int puntos;
		int codCliente = InputTypes.readInt("C�digo del cliente: ", scanner);
		String sql = "select * from cliente where codCliente = ?";
		conexi�n.consulta(sql);
		conexi�n.getSentencia().setInt(1, codCliente);
		resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			nombre = resultSet.getString("nombre");
			cI = resultSet.getInt("CI");
			tel�fono = resultSet.getInt("tel�fono");
			celular = resultSet.getInt("celular");
			direcci�n = resultSet.getString("direcci�n");
			puntos = resultSet.getInt("puntos");
			codCliente = resultSet.getInt("codCliente");
			cliente = new Cliente(codCliente, nombre, cI, tel�fono, celular, direcci�n, puntos);
		} else {
			throw new NoExisteCliente();
		}

		System.out.println(cliente);
		Men�.men�Modificar(scanner, cliente);

		sql = "update cliente set nombre = ?, CI = ?, tel�fono = ?, celular = ?, direcci�n = ?, puntos = ?  where codCliente = ?";

		conexi�n.consulta(sql);
		conexi�n.getSentencia().setInt(7, cliente.getCodCliente());
		conexi�n.getSentencia().setString(1, cliente.getNombre());
		conexi�n.getSentencia().setInt(2, cliente.getCI());
		conexi�n.getSentencia().setInt(3, cliente.getTel�fono());
		conexi�n.getSentencia().setInt(4, cliente.getCelular());
		conexi�n.getSentencia().setString(5, cliente.getDirecci�n());
		conexi�n.getSentencia().setInt(6, cliente.getPuntos());
		conexi�n.modificacion();
	}
	public void list() throws NoExisteCliente, SQLException{
		Cliente cliente;
		String sql = "select * from cliente ";
		conexi�n.consulta(sql);
		ResultSet resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			resultSet.previous();
			while (resultSet.next()) {
				cliente = new Cliente(resultSet.getInt("codCliente"), resultSet.getString("nombre"), resultSet.getInt("CI"),
						resultSet.getInt("tel�fono"), resultSet.getInt("celular"), resultSet.getString("direcci�n"),
						resultSet.getInt("puntos"));
				System.out.println(cliente);
			}
		} else {
			throw new NoExisteCliente();
		}	
	}
	
	public void ganador () throws SQLException, NoExisteCliente {
		ArrayList<Cliente> sorteados = new ArrayList<Cliente>();
		Cliente cliente;
		String sql = "select * from cliente ";
		conexi�n.consulta(sql);
		ResultSet resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			resultSet.previous();
			while (resultSet.next()) {
				cliente = new Cliente(resultSet.getInt("codCliente"), resultSet.getString("nombre"), resultSet.getInt("CI"),
						resultSet.getInt("tel�fono"), resultSet.getInt("celular"), resultSet.getString("direcci�n"),
						resultSet.getInt("puntos"));
				for (int i = 0; i < resultSet.getInt("puntos"); i++) {
					sorteados.add(cliente);
				}
			}
			int ganador = (int)(Math.random() * sorteados.size()) + 1;
			System.out.println();
			System.out.println("El ganador es: " + sorteados.get(ganador).getNombre());
			System.out.println(sorteados.get(ganador));
		} else {
			throw new NoExisteCliente();
		}
	}
}
