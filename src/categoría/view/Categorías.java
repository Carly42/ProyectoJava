package categor�a.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import categor�a.entity.Categor�a;
import categor�a.entity.NoExisteCategor�a;
import control.Conexi�n;
import producto.entity.NoExisteProducto;
import producto.entity.Producto;
import view.InputTypes;

public class Categor�as {
	private Conexi�n conexi�n;
	private Scanner scanner;

	public Categor�as(Conexi�n conexi�n, Scanner scanner) {
		this.conexi�n = conexi�n;
		this.scanner = scanner;
	}

	public void add() {
		Categor�a categor�a = RegistroCategor�a.ingresar(scanner);
		String sql = "Insert into Categor�a (nombre, descripci�n) " + "values(?,?)";
		try {
			conexi�n.consulta(sql);
			conexi�n.getSentencia().setString(1, categor�a.getNombre());
			conexi�n.getSentencia().setString(2, categor�a.getDescripci�n());
			conexi�n.modificacion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update() throws NoExisteCategor�a, SQLException {
		ResultSet resultSet;
		Categor�a categor�a;
		String nombre;
		String descripci�n;
		int codCategor�a = InputTypes.readInt("C�digo de categor�a: ", scanner);
		String sql = "select * from categor�a where codCategor�a = ?";
		conexi�n.consulta(sql);
		conexi�n.getSentencia().setInt(1, codCategor�a);
		resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			nombre = resultSet.getString("nombre");
			descripci�n = resultSet.getString("descripci�n");
			categor�a = new Categor�a(codCategor�a, nombre, descripci�n);
		} else {
			throw new NoExisteCategor�a();
		}
		System.out.println(categor�a);
		Men�.men�Modificar(scanner, categor�a);

		sql = "update categor�a set nombre = ?, descripci�n = ? where codCategor�a = ?";

		conexi�n.consulta(sql);
		conexi�n.getSentencia().setString(1, categor�a.getNombre());
		conexi�n.getSentencia().setString(2, categor�a.getDescripci�n());
		conexi�n.getSentencia().setInt(3, categor�a.getCodCategor�a());
		conexi�n.modificacion();
	}

	public void list() throws SQLException, NoExisteCategor�a {
		Categor�a categor�a;
		String sql = "select * from categor�a ";
		conexi�n.consulta(sql);
		ResultSet resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			resultSet.previous();
			while (resultSet.next()) {
				categor�a = new Categor�a(resultSet.getInt("codCategor�a"), resultSet.getString("nombre"),
						resultSet.getString("descripci�n"));
				System.out.println(categor�a);
			}
		} else {
			throw new NoExisteCategor�a();
		}
	}

	public void listProducts() throws NoExisteCategor�a, SQLException, NoExisteProducto {
		ResultSet resultSet;
		Categor�a categor�a;
		String nombre;
		String descripci�n;
		int codCategor�a = InputTypes.readInt("C�digo de categor�a: ", scanner);
		String sql = "select * from categor�a where codCategor�a = ?";
		conexi�n.consulta(sql);
		conexi�n.getSentencia().setInt(1, codCategor�a);
		resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			nombre = resultSet.getString("nombre");
			descripci�n = resultSet.getString("descripci�n");
			categor�a = new Categor�a(codCategor�a, nombre, descripci�n);
		} else {
			throw new NoExisteCategor�a();
		}
		System.out.println(categor�a);

		Producto producto;
		double precio;
		int codProducto;

		sql = "select * from producto where codCategor�a = ?";
		conexi�n.consulta(sql);
		conexi�n.getSentencia().setInt(1, codCategor�a);
		resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			resultSet.previous();
			while (resultSet.next()) {
				codProducto = resultSet.getInt("codProducto");
				nombre = resultSet.getString("nombre");
				descripci�n = resultSet.getString("descripci�n");
				precio = resultSet.getDouble("precio");
				producto = new Producto(codProducto, nombre, descripci�n, precio, codCategor�a);
				System.out.println(producto);
			}
		} else {
			throw new NoExisteProducto();
		}
	}
}