package NBA;

public class Jugador {
	private int cod;
	private String nombre;
	private String procedencia;
	private String altura;
	private int peso;
	private String posicion;
	private String nombre_equipo;
	public Jugador() {
		super();
	}
	public Jugador(int cod, String nombre, String procedencia, String altura, int peso, String posicion,
			String nombre_equipo) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.procedencia = procedencia;
		this.altura = altura;
		this.peso = peso;
		this.posicion = posicion;
		this.nombre_equipo = nombre_equipo;
	}
	
	public Jugador(String nombre, String procedencia, String altura, int peso, String posicion, String nombre_equipo) {
		super();
		this.nombre = nombre;
		this.procedencia = procedencia;
		this.altura = altura;
		this.peso = peso;
		this.posicion = posicion;
		this.nombre_equipo = nombre_equipo;
	}
	public int getCod() {
		return cod;
	}
	public String getNombre() {
		return nombre;
	}
	public String getProcedencia() {
		return procedencia;
	}
	public String getAltura() {
		return altura;
	}
	public int getPeso() {
		return peso;
	}
	public String getPosicion() {
		return posicion;
	}
	public String getNombre_equipo() {
		return nombre_equipo;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public void setNombre_equipo(String nombre_equipo) {
		this.nombre_equipo = nombre_equipo;
	}
	@Override
	public String toString() {
		return "Código: " + cod + ", nombre: " + nombre + ", procedencia: " + procedencia + ", altura: " + altura
				+ ", peso: " + peso + ", posicion: " + posicion + ", nombre equipo: " + nombre_equipo;
	}
	
}
