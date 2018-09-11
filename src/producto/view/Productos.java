package producto.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import categoría.entity.Categoría;
import categoría.entity.NoExisteCategoría;
import control.Conexión;
import producto.entity.NoExisteProducto;
import producto.entity.Producto;
import view.InputTypes;

public class Productos {
	private Conexión conexión;
	private Scanner scanner;

	/****************************
	 * Constructor *
	 ****************************/

	public Productos(Conexión conexión, Scanner scanner) {
		this.conexión = conexión;
		this.scanner = scanner;
	}

	/****************************
	 * Agregar productos
	 ****************************/

	public void add() throws NoExisteCategoría {
		Producto producto = RegistroProducto.ingresar(scanner);
		String sql = "Insert into Producto (nombre, descripción, precio, códigoCategoría, codIngrediente) values(?,?,?,?,?)";
		try {
			conexión.consulta(sql);
			conexión.getSentencia().setString(1, producto.getNombre());
			conexión.getSentencia().setString(2, producto.getDescripción());
			conexión.getSentencia().setDouble(3, producto.getPrecio());
			conexión.getSentencia().setInt(4, producto.getCodCategoría());
			conexión.getSentencia().setInt(5, producto.getCodIngrediente());
			conexión.modificacion();
		} catch (SQLException e) {
			throw new NoExisteCategoría();
		}

	}

	/****************************
	 * Eliminar productos *
	 ****************************/

	public void delete() {
		int codProducto = InputTypes.readInt("Código de producto: ", scanner);
		String sql = "delete from producto where código = ?";
		try {
			conexión.consulta(sql);
			conexión.getSentencia().setInt(1, codProducto);
			conexión.modificacion();
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
		}
	}

	/****************************
	 * Modificar categorías
	 * 
	 * @throws SQLException
	 * @throws NoExisteProducto *
	 ****************************/

	public void update() throws NoExisteCategoría, SQLException, NoExisteProducto {
		ResultSet resultSet;
		Producto producto;
		String nombre;
		double precio;
		String descripción;
		int códigoCategoría;
		int códigoIngrediente;
		int código;
		int codProducto = InputTypes.readInt("Código de producto: ", scanner);
		String sql = "select * from producto where código = ?";
		conexión.consulta(sql);
		conexión.getSentencia().setInt(1, codProducto);
		resultSet = conexión.resultado();
		if (resultSet.next()) {
			nombre = resultSet.getString("nombre");
			descripción = resultSet.getString("descripción");
			precio = resultSet.getDouble("precio");
			códigoCategoría = resultSet.getInt("códigoCategoría");
			códigoIngrediente = resultSet.getInt("códigoIngredeinte");
			código = resultSet.getInt("código");
			producto = new Producto(código, nombre, descripción, precio, códigoCategoría, códigoIngrediente);
		} else {
			throw new NoExisteProducto();
		}

		System.out.println(producto);
		Menú.menúModificar(scanner, producto);

		sql = "update producto set nombre = ?, descripción = ?, precio = ?, códigoCategoría = ?, códigoIngrediente = ?  where código = ?";

		conexión.consulta(sql);
		conexión.getSentencia().setString(1, producto.getNombre());
		conexión.getSentencia().setString(2, producto.getDescripción());
		conexión.getSentencia().setDouble(3, producto.getPrecio());
		conexión.getSentencia().setInt(4, producto.getCodCategoría());
		conexión.getSentencia().setInt(5, producto.getCodIngrediente());
		conexión.modificacion();
	}

	/****************************
	 * Listar productos 
	 * @throws SQLException *
	 ****************************/

	public void list() throws SQLException {
		Producto producto;
		String sql = "select * from producto ";
		conexión.consulta(sql);
		ResultSet resultSet = conexión.resultado();
		while (resultSet.next()) {
			producto = new Producto(resultSet.getInt("código"), resultSet.getString("nombre"),
					resultSet.getString("descripción"), resultSet.getDouble("precio"),
					resultSet.getInt("CódigoCategoría"), resultSet.getInt("CódigoIngrediente"));
			System.out.println(producto);
		}
	}

	public void listCatogories() throws NoExisteProducto, SQLException, NoExisteCategoría {
		ResultSet resultSet;
		Producto producto;
		String nombre;
		String descripción;
		Double precio;
		int códigoCategoría;
		int códigoIngrediente;
		int código = InputTypes.readInt("Código de producto: ", scanner);
		String sql = "select * from producto where código = ?";
		conexión.consulta(sql);
		conexión.getSentencia().setInt(1, código);
		resultSet = conexión.resultado();
		if (resultSet.next()) {
			nombre = resultSet.getString("nombre");
			descripción = resultSet.getString("descripción");
			precio = resultSet.getDouble("precio");
			códigoCategoría = resultSet.getInt("códigoCategoría");
			códigoIngrediente = resultSet.getInt("códigoIngrediente");
			producto = new Producto(código,nombre, descripción, precio, códigoCategoría, códigoIngrediente);
		} else {
			throw new NoExisteProducto();
		}
		System.out.println(producto);

		Categoría categoría;

		sql = "select * from categoría where código = ?";
		conexión.consulta(sql);
		conexión.getSentencia().setInt(1, códigoCategoría);
		resultSet = conexión.resultado();
		if (resultSet.next()) {
			código = resultSet.getInt("código");
			nombre = resultSet.getString("nombre");
			descripción = resultSet.getString("descripción");
			categoría = new Categoría(código, nombre, descripción);
			System.out.println(categoría);
		} else {
			throw new NoExisteCategoría();
		}

	}
}
