package producto.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import control.Conexi�n;
import producto.entity.NoExisteProducto;
import producto.entity.Producto;
import view.InputTypes;

public class Productos {
	private Conexi�n conexi�n;
	private Scanner scanner;

	public Productos(Conexi�n conexi�n, Scanner scanner) {
		this.conexi�n = conexi�n;
		this.scanner = scanner;
	}

	public void add() throws SQLException {
		Producto producto = RegistroProducto.ingresar(scanner);
		String sql = "Insert into Producto (nombre, descripci�n, precio, codCategor�a) values(?,?,?,?)";
		conexi�n.consulta(sql);
		conexi�n.getSentencia().setString(1, producto.getNombre());
		conexi�n.getSentencia().setString(2, producto.getDescripci�n());
		conexi�n.getSentencia().setDouble(3, producto.getPrecio());
		conexi�n.getSentencia().setInt(4, producto.getCodCategor�a());
		conexi�n.modificacion();
	}

	public void update() throws SQLException, NoExisteProducto {
		ResultSet resultSet;
		Producto producto;
		String nombre;
		String descripci�n;
		double precio;
		int c�digoCategor�a;
		int c�digo;
		int codProducto = InputTypes.readInt("C�digo del producto: ", scanner);
		String sql = "select * from producto where codProducto = ?";
		conexi�n.consulta(sql);
		conexi�n.getSentencia().setInt(1, codProducto);
		resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			nombre = resultSet.getString("nombre");
			descripci�n = resultSet.getString("descripci�n");
			precio = resultSet.getDouble("precio");
			c�digoCategor�a = resultSet.getInt("codCategor�a");
			c�digo = resultSet.getInt("codProducto");
			producto = new Producto(c�digo, nombre, descripci�n, precio, c�digoCategor�a);

			System.out.println(producto);
			Men�.men�Modificar(scanner, producto);

			sql = "update producto set nombre = ?, descripci�n = ?, precio = ?, codCategor�a = ? where codProducto = ?";

			conexi�n.consulta(sql);
			conexi�n.getSentencia().setString(1, producto.getNombre());
			conexi�n.getSentencia().setString(2, producto.getDescripci�n());
			conexi�n.getSentencia().setDouble(3, producto.getPrecio());
			conexi�n.getSentencia().setInt(4, producto.getCodCategor�a());
			conexi�n.getSentencia().setInt(5, producto.getCodProducto());
			conexi�n.modificacion();
		} else {
			throw new NoExisteProducto();
		}
	}

	public void list() throws NoExisteProducto, SQLException {
		Producto producto;
		String sql = "select * from producto";
		conexi�n.consulta(sql);
		ResultSet resultSet = conexi�n.resultado();
		if (resultSet.next()) {
			resultSet.previous();
			while (resultSet.next()) {
				producto = new Producto(resultSet.getInt("codProducto"), resultSet.getString("nombre"),
						resultSet.getString("descripci�n"), resultSet.getDouble("precio"),
						resultSet.getInt("codCategor�a"));
				System.out.println(producto);
			} 
		} else {
			throw new NoExisteProducto();
		}
	}
}