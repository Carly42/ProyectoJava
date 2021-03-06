package ingrediente.view;

import java.sql.SQLException;
import java.util.Scanner;
import ingrediente.entity.Ingrediente;
import ingrediente.entity.NoExisteIngrediente;
import view.InputTypes;

public class Men� {


	private static int encabezado(Scanner scanner) {
		int opcion;

		while (true) {
			System.out.println("Ingrese una opcion: ");
			System.out.println("------------------- ");
			System.out.println("1. Ingresar Ingrediente");
			System.out.println("2. Listar Ingredientes ");
			System.out.println("3. Eliminar Ingrediente ");
			System.out.println("4. Modificar Ingrediente ");
			System.out.println("0. Salir");
			System.out.println();

			opcion = InputTypes.readInt("�Su opci�n? ", scanner);

			if (opcion >= 0 && opcion <= 5) {
				return opcion;
			}
		}
	}

	public static void men�(Scanner scanner, Ingredientes ingredientes) {
		boolean salir = false;

		while (!salir) {
			switch (encabezado(scanner)) {
			case 0:
				salir = true;
				break;
			case 1:
				ingredientes.add();
				break;
			case 2:
				try {
					ingredientes.list();
				} catch (NoExisteIngrediente e1) {
					System.out.println();
					System.out.println("No existen ingredientes!");
					System.out.println();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					ingredientes.delete();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NoExisteIngrediente e) {
					System.out.println();
					System.out.println("No existe ingrediente!");
					System.out.println();	
				}
				break;
			case 4:
				try {
					ingredientes.update();
				} catch (NoExisteIngrediente e) {
					System.out.println();
					System.out.println("No existe el ingrediente");
					System.out.println();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	private static int encabezadoModificar(Scanner scanner) {
		int opcion;

		while (true) {
			System.out.println("Ingrese una opcion: ");
			System.out.println("------------------- ");
			System.out.println("1. Modificar descripci�n: ");
			System.out.println("2. Modificar costo: ");
			System.out.println("3. Modificar n�mero de almac�n: ");
			System.out.println("4. Modificar cantidad: ");
			System.out.println("0. Salir");
			System.out.println();

			opcion = InputTypes.readInt("�Su opci�n? ", scanner);

			if (opcion >= 0 && opcion <= 4) {
				return opcion;
			}
		}
	}

	public static void men�Modificar(Scanner scanner, Ingrediente ingrediente) {
		boolean salir = false;

		while (!salir) {
			switch (encabezadoModificar(scanner)) {
			case 0:
				salir = true;
				break;
			case 1:
				ingrediente.setDescripci�n(InputTypes.readString("Ingrese la nueva descripci�n: ", scanner));
				break;
			case 2:
				ingrediente.setCosto(InputTypes.readDouble("Ingrese el nuevo costo:", scanner));
				break;
			case 3:
				ingrediente.setNumAlmac�n(InputTypes.readInt("Ingrese el  nuevo n�mero del almacen:", scanner));
				break;
			case 4:
				ingrediente.setCantidad(InputTypes.readInt("Ingrese el nuevo n�mero de cantidad de ingredinte:", scanner));
				break;
			}
		}
	}
}