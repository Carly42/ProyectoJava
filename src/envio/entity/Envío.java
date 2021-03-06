package envio.entity;

public class Env�o {
	private int codEnv�o;
	private String destinatario;
	private int tel�fono;
	private double costoAdicional;
	private int numVenta;
	public Env�o(int codEnv�o, String destinatario, int tel�fono, double costoAdicional, int numVenta) {
		super();
		this.codEnv�o = codEnv�o;
		this.destinatario = destinatario;
		this.tel�fono = tel�fono;
		this.costoAdicional = costoAdicional;
		this.numVenta = numVenta;
	}
	public int getCodEnv�o() {
		return codEnv�o;
	}
	public void setCodEnv�o(int codEnv�o) {
		this.codEnv�o = codEnv�o;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public int getTel�fono() {
		return tel�fono;
	}
	public void setTel�fono(int tel�fono) {
		this.tel�fono = tel�fono;
	}
	public double getCostoAdicional() {
		return costoAdicional;
	}
	public void setCostoAdicional(double costoAdicional) {
		this.costoAdicional = costoAdicional;
	}
	public int getNumVenta() {
		return numVenta;
	}
	public void setNumVenta(int numVenta) {
		this.numVenta = numVenta;
	}
	@Override
	public String toString() {
		return "Env�o [codEnv�o=" + codEnv�o + ", destinatario=" + destinatario + ", tel�fono=" + tel�fono
				+ ", costoAdicional=" + costoAdicional + ", numVenta=" + numVenta + "]";
	}
	
	
}
