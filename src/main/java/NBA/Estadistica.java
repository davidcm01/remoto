package NBA;

public class Estadistica {
	private String temporada;
	private int jugador;
	private float puntos_por_partido;
	private float Asistencias_por_partido;
	private float Tapones_por_partido;
	private float Rebotes_por_partido;
	
	public Estadistica() {
		super();
	}

	public Estadistica(String temporada, int jugador, float puntos_por_partido, float asistencias_por_partido,
			float tapones_por_partido, float rebotes_por_partido) {
		super();
		this.temporada = temporada;
		this.jugador = jugador;
		this.puntos_por_partido = puntos_por_partido;
		Asistencias_por_partido = asistencias_por_partido;
		Tapones_por_partido = tapones_por_partido;
		Rebotes_por_partido = rebotes_por_partido;
	}

	public String getTemporada() {
		return temporada;
	}

	public int getJugador() {
		return jugador;
	}

	public float getPuntos_por_partido() {
		return puntos_por_partido;
	}

	public float getAsistencias_por_partido() {
		return Asistencias_por_partido;
	}

	public float getTapones_por_partido() {
		return Tapones_por_partido;
	}

	public float getRebotes_por_partido() {
		return Rebotes_por_partido;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public void setJugador(int jugador) {
		this.jugador = jugador;
	}

	public void setPuntos_por_partido(float puntos_por_partido) {
		this.puntos_por_partido = puntos_por_partido;
	}

	public void setAsistencias_por_partido(float asistencias_por_partido) {
		Asistencias_por_partido = asistencias_por_partido;
	}

	public void setTapones_por_partido(float tapones_por_partido) {
		Tapones_por_partido = tapones_por_partido;
	}

	public void setRebotes_por_partido(float rebotes_por_partido) {
		Rebotes_por_partido = rebotes_por_partido;
	}

	@Override
	public String toString() {
		return "Temporada: " + temporada + ", jugador: " + jugador + ", puntos por partido: "
				+ puntos_por_partido + ", Asistencias por partido: " + Asistencias_por_partido + ", Tapones por partido: "
				+ Tapones_por_partido + ", Rebotes por partido: " + Rebotes_por_partido;
	}
	
}
