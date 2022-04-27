package NBA;

public class Equipo {
	private String nombre;
	private String ciudad;
	private String conferencia;
	private String division;
	public Equipo() {
		super();
	}
	public Equipo(String nombre, String ciudad, String conferencia, String division) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.conferencia = conferencia;
		this.division = division;
	}
	public String getNombre() {
		return nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getConferencia() {
		return conferencia;
	}
	public String getDivision() {
		return division;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public void setConferencia(String conferencia) {
		this.conferencia = conferencia;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	@Override
	public String toString() {
		return "Nombre: " + nombre + ", ciudad: " + ciudad + ", conferencia: " + conferencia + ", división: " + division;
	}
	
}
