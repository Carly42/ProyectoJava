package empleado.entity;

public class Empleado {
	private int codEmpleado;
	private String nombre;
	private int cI;
	private int tel�fono;
	private int celular;
	private String direcci�n;
	private String cargo;
	public Empleado(int codEmpleado, String nombre, int cI, int tel�fono, int celular, String direcci�n, String cargo) {
		super();
		this.codEmpleado = codEmpleado;
		this.nombre = nombre;
		this.cI = cI;
		this.tel�fono = tel�fono;
		this.celular = celular;
		this.direcci�n = direcci�n;
		this.cargo = cargo;
	}
	public int getCodEmpleado() {
		return codEmpleado;
	}
	public void setCodEmpleado(int codEMpleado) {
		this.codEmpleado = codEMpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getcI() {
		return cI;
	}
	public void setcI(int cI) {
		this.cI = cI;
	}
	public int getTel�fono() {
		return tel�fono;
	}
	public void setTel�fono(int tel�fono) {
		this.tel�fono = tel�fono;
	}
	public int getCelular() {
		return celular;
	}
	public void setCelular(int celular) {
		this.celular = celular;
	}
	public String getDirecci�n() {
		return direcci�n;
	}
	public void setDirecci�n(String direcci�n) {
		this.direcci�n = direcci�n;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	@Override
	public String toString() {
		return "Empleado [codEmpleado=" + codEmpleado + ", nombre=" + nombre + ", cI=" + cI + ", tel�fono=" + tel�fono
				+ ", celular=" + celular + ", direcci�n=" + direcci�n + ", cargo=" + cargo + "]";
	}
	
	

}
