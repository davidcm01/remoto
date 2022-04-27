package NBA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BDController {
	private Connection conexion;

	public BDController() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexión del constructor vacío");
		}
	}

	// TODO Declaracion de metodos para la clase jugador

	/**
	 * Metodo que devuelve un jugador pasandole un codigo de jugador
	 * 
	 * @param cod
	 * @return
	 */
	public Jugador dameJugador(int cod) {
		Jugador jugador = new Jugador();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from jugadores where codigo like '" + cod + "'");
			if (rs.next()) {
				jugador = new Jugador(rs.getInt("codigo"), rs.getString("Nombre"), rs.getString("Procedencia"),
						rs.getString("Altura"), rs.getInt("peso"), rs.getString("Posicion"),
						rs.getString("Nombre_equipo"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en DameJugador en BDController");
		}

		return jugador;

	}

	public int dameCodigoJugador(String nombre) {
		int codigo = 0;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from jugadores where Nombre like '" + nombre + "'");
			if (rs.next()) {
				codigo = rs.getInt("codigo");
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en DameCodigoJugador en BDController");
		}

		return codigo;

	}

	/**
	 * Metodo que devuelve todos los jugadores en un arrayList de jugadores
	 * 
	 * @return
	 */
	public ArrayList<Jugador> todosJugadores() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from jugadores");
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getInt("codigo"), rs.getString("Nombre"), rs.getString("Procedencia"),
						rs.getString("Altura"), rs.getInt("peso"), rs.getString("Posicion"),
						rs.getString("Nombre_equipo"));
				jugadores.add(jugador);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en todosJugadores en BDController");
		}

		return jugadores;

	}

	/**
	 * Metodo que devuelve true o false dependiendo de si existe un jugador buscado
	 * por su codigo
	 * 
	 * @param cod
	 * @return
	 */
	public boolean existeJugador(int cod) {
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from jugadores where codigo like '" + cod + "'");
			if (rs.next()) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en existeJugador en BDController");
		}

		return encontrado;
	}
	public boolean existeJugadorNombre(String nombre) {
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from jugadores where nombre like '" + nombre + "'");
			if (rs.next()) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en existeJugador en BDController");
		}

		return encontrado;
	}

	public int ultimoCodJUgadores() {
		int cod = 0;

		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select max(codigo) from jugadores");
			if (rs.next() == true) {
				cod = rs.getInt("max(codigo)");
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cod = cod + 1;
		return cod;

	}

	public void altaJugador(int codigo, String nombre, String procedencia, String altura, int peso, String posicion,
			String nombre_equipo) {
		try {
			Statement miStatement = this.conexion.createStatement();
			//System.out.println(codigo);
			String cadena = "insert into jugadores values (" + codigo + ",'" + nombre + "','" + procedencia + "','"
					+ altura + "'," + peso + ",'" + posicion + "','" + nombre_equipo + "')";
			// System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en alta jugadores en BDController");
		}
	}

	public void bajaJugador(int codigo) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "delete from jugadores where codigo = '" + codigo + "'";
			// System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error bdcontroller eliminarJugador");
		}
	}

	public ArrayList<Jugador> jugadoresCondicionesABuscar(String procedencia,String ciudad, String conferencia, String division){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
				try {
					Statement miStatement = this.conexion.createStatement();
//					String cadena="select * from jugadores where procedencia like '" + procedencia + "' and nombre_equipo in(select nombre from equipos where ciudad not like '" +ciudad+"' and conferencia like '"+conferencia+"' and division like '"+division+"'";
//					System.out.println(cadena);
					ResultSet rs = miStatement.executeQuery( "select * from jugadores where procedencia like '" + procedencia + "' and nombre_equipo in(select nombre from equipos where ciudad not like '" +ciudad+"' and conferencia like '"+conferencia+"' and division like '"+division+"')");
					 
					while(rs.next()) {
						jugadores.add(new Jugador(rs.getInt("codigo"), rs.getString("Nombre"), rs.getString("Procedencia"),
						rs.getString("Altura"), rs.getInt("peso"), rs.getString("Posicion"),
						rs.getString("Nombre_equipo")));
					}
					
					miStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Error bdcontroller jugadoresCondicionesABuscar");
				}
				return jugadores;
	}

	// TODO Declaracion de metodos para la clase Equipo

	/**
	 * Metodo que devuelve un equipo pasandole su nombre
	 * 
	 * @param nombre
	 * @return
	 */
	public Equipo dameEquipo(String nombre) {
		Equipo equipo = new Equipo();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from equipos where nombre like '" + nombre + "'");
			if (rs.next()) {
				equipo = new Equipo(rs.getString("Nombre"), rs.getString("Ciudad"), rs.getString("Conferencia"),
						rs.getString("Division"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en dameEquipo en BDController");
		}

		return equipo;

	}

	/**
	 * Metodo que devuelve todos los equipos en un arrayList de equipos
	 * 
	 * @return
	 */
	public ArrayList<Equipo> todosEquipos() {
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from equipos");
			while (rs.next()) {
				Equipo equipo = new Equipo(rs.getString("Nombre"), rs.getString("Ciudad"), rs.getString("Conferencia"),
						rs.getString("Division"));
				equipos.add(equipo);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en todosEquipos en BDController");
		}

		return equipos;

	}

	/**
	 * Metodo que devuelve true o false dependiendo de si existe un equipo buscado
	 * por su nombre
	 * 
	 * @param nombre
	 * @return
	 */
	public boolean existeEquipo(String nombre) {
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from equipos where nombre like '" + nombre + "'");
			if (rs.next()) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en existeEquipo en BDController");
		}

		return encontrado;
	}

	public ArrayList<Equipo> listadoEquiposDivision() {
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT*FROM equipos order by division");
			while (rs.next()) {
				equipos.add(new Equipo(rs.getString("Nombre"), rs.getString("Ciudad"), rs.getString("Conferencia"),
						rs.getString("Division")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en listadoEquiposDivision en BDController");
		}
		return equipos;
	}
	public ArrayList<String> nombreEquiposConferencia(String conferencia) {
		ArrayList<String> nombreEquipos = new ArrayList<String>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT nombre FROM equipos where conferencia like '" + conferencia +"'");
			while (rs.next()) {
				nombreEquipos.add(rs.getString("Nombre"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en nombreEquiposConferencia en BDController");
		}
		return nombreEquipos;
	}
	public ArrayList<String> nombreEquiposDivision(String division) {
		ArrayList<String> nombreEquipos = new ArrayList<String>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT nombre FROM equipos where division like '" + division +"'");
			while (rs.next()) {
				nombreEquipos.add(rs.getString("Nombre"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en nombreEquiposDivision en BDController");
		}
		return nombreEquipos;
	}

	// TODO Declaracion de metodos para la clase Estadistica

	/**
	 * Metodo que devuelve una estadistica pasandole su temporada
	 * 
	 * @param nombre
	 * @return
	 */
	public Estadistica dameEstadistica(String temporada) {
		Estadistica estadistica = new Estadistica();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement
					.executeQuery("Select * from estadisticas where temporada like '" + temporada + "'");
			if (rs.next()) {
				estadistica = new Estadistica(rs.getString("temporada"), rs.getInt("jugador"),
						rs.getFloat("Puntos_por_partido"), rs.getFloat("Asistencias_por_partido"),
						rs.getFloat("Tapones_por_partido"), rs.getFloat("Rebotes_por_partido"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en dameEstadistica en BDController");
		}

		return estadistica;

	}
	public ArrayList<Estadistica> dameEstadisticasJugador(int codigoJugador) {
		ArrayList<Estadistica> estadisticas = new ArrayList<Estadistica>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement
					.executeQuery("Select * from estadisticas where jugador like '" + codigoJugador + "'");
			while (rs.next()) {
				estadisticas.add(new Estadistica(rs.getString("temporada"), rs.getInt("jugador"),
						rs.getFloat("Puntos_por_partido"), rs.getFloat("Asistencias_por_partido"),
						rs.getFloat("Tapones_por_partido"), rs.getFloat("Rebotes_por_partido")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en dameEstadisticasJugador en BDController");
		}

		return estadisticas;

	}

	public Estadistica dameEstadisticaCodigo(int codigo) {
		Estadistica estadistica = new Estadistica();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from estadisticas where jugador like '" + codigo + "'");
			if (rs.next()) {
				estadistica = new Estadistica(rs.getString("temporada"), rs.getInt("jugador"),
						rs.getFloat("Puntos_por_partido"), rs.getFloat("Asistencias_por_partido"),
						rs.getFloat("Tapones_por_partido"), rs.getFloat("Rebotes_por_partido"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en dameEstadistica en BDController");
		}

		return estadistica;

	}

	/**
	 * Metodo que devuelve todos las estadisticas en un arrayList de estadisticas
	 * 
	 * @return
	 */
	public ArrayList<Estadistica> todasEstadisticas() {
		ArrayList<Estadistica> estadisticas = new ArrayList<Estadistica>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from equipos");
			while (rs.next()) {
				Estadistica estadistica = new Estadistica(rs.getString("temporada"), rs.getInt("jugador"),
						rs.getFloat("Puntos_por_partido"), rs.getFloat("Asistencias_por_partido"),
						rs.getFloat("Tapones_por_partido"), rs.getFloat("Rebotes_por_partido"));
				estadisticas.add(estadistica);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en todasEstadisticas en BDController");
		}

		return estadisticas;

	}

	/**
	 * Metodo que devuelve true o false dependiendo de si existe una estadistica
	 * buscado por su temporada
	 * 
	 * @param nombre
	 * @return
	 */
	public boolean existeEstadistica(String temporada) {
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement
					.executeQuery("Select * from estadisticas where temporada like '" + temporada + "'");
			if (rs.next()) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en existeEstadistica en BDController");
		}

		return encontrado;
	}

	public void altaEstadistica(int codigo, String temporada, Float ppp, Float app, Float tpp, Float rpp) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "insert into estadisticas values ('" + temporada + "','" + codigo + "','" + ppp + "','"
					+ app + "'," + tpp + ",'" + rpp + "')";
			// System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en alta estadistica en BDController");
		}
	}

	public void bajaEstadistica(int codigo, String temporada) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "delete from estadisticas where temporada like '" + temporada + "' and jugador = " + codigo;
			// System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error bdcontroller bajaEstadistica");
		}
	}

	public boolean existeEstadisticaTemporadaJugador(String temporada, int codigoJugador) {
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from estadisticas where temporada like '" + temporada
					+ "' and jugador = " + codigoJugador);
			if (rs.next()) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en existeEstadisticaTemporadaJugador en BDController");
		}
		return encontrado;
	}

	public ArrayList<Integer> codigosJugadores30PPP() {
		ArrayList<Integer> codigos = new ArrayList<Integer>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from estadisticas where Puntos_por_partido > 30");
			while (rs.next()) {
				codigos.add(rs.getInt("jugador"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en codigosJugadores30PPP en BDController");
		}
		return codigos;

	}

	// TODO Declaracion de metodos para la clase partidos

	/**
	 * Metodo que devuelve un partido pasandole su codigo
	 * 
	 * @param nombre
	 * @return
	 */
	public Partido damePartido(int cod) {
		Partido partido = new Partido();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from partidos where codigo like '" + cod + "'");
			if (rs.next()) {
				partido = new Partido(rs.getInt("codigo"), rs.getString("equipo_local"),
						rs.getString("equipo_visitante"), rs.getInt("puntos_local"), rs.getInt("puntos_visitante"),
						rs.getString("temporada"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en damePartido en BDController");
		}

		return partido;

	}

	public ArrayList<Integer> dameCodigosPartidos(String equipo1, String equipo2) {
		ArrayList<Integer> codigos = new ArrayList<Integer>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("select codigo from partidos where equipo_local like '" + equipo1
					+ "' and equipo_visitante like '" + equipo2 + "' or equipo_local like '" + equipo2
					+ "' and equipo_visitante like '" + equipo1 + "'");
			while (rs.next()) {
				codigos.add(rs.getInt("codigo"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en daameCodigosPartidos en BDController");
		}
		return codigos;

	}

	public ArrayList<Integer> dameCodigosPartidosGanados(String nombre) {
		ArrayList<Integer> codigos = new ArrayList<Integer>();

		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("select codigo from partidos where equipo_local like '" + nombre
					+ "' and puntos_local>puntos_visitante or equipo_visitante like '" + nombre
					+ "' and puntos_local<puntos_visitante");
			while (rs.next()) {
				codigos.add(rs.getInt("codigo"));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en daameCodigosPartidos en BDController");
		}

		return codigos;
	}

	/**
	 * Metodo que devuelve todos las estadisticas en un arrayList de estadisticas
	 * 
	 * @return
	 */
	public ArrayList<Partido> todosPartidos() {
		ArrayList<Partido> partidos = new ArrayList<Partido>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from partidos");
			while (rs.next()) {
				Partido partido = new Partido(rs.getInt("codigo"), rs.getString("equipo_local"),
						rs.getString("equipo_visitante"), rs.getInt("puntos_local"), rs.getInt("puntos_visitante"),
						rs.getString("temporada"));
				partidos.add(partido);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en todosPartidos en BDController");
		}

		return partidos;

	}

	/**
	 * Metodo que devuelve true o false dependiendo de si existe un partido buscado
	 * por su codigo
	 * 
	 * @param nombre
	 * @return
	 */
	public boolean existePartido(int cod) {
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from partidos where codigo like '" + cod + "'");
			if (rs.next()) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en existeEstadistica en BDController");
		}

		return encontrado;
	}

	public boolean existePartidoPorNombre(String equipo1, String equipo2) {
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("select codigo from partidos where equipo_local like '" + equipo1
					+ "' and equipo_visitante like '" + equipo2 + "' or equipo_local like '" + equipo2
					+ "' and equipo_visitante like '" + equipo1 + "'");
			if (rs.next()) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en existeEstadisticaPorNombres en BDController");
		}

		return encontrado;
	}
	
	public ArrayList<String> nombreEquiposApalizados() {
		ArrayList<String> equipos = new ArrayList<String>();
		
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs1 = miStatement.executeQuery("select DISTINCT(equipo_visitante) from partidos where (partidos.puntos_local - partidos.puntos_visitante) >40;");
			while (rs1.next()) {
				equipos.add(rs1.getString("equipo_visitante"));
			}
			ResultSet rs2 = miStatement.executeQuery("select DISTINCT(equipo_local) from partidos where (partidos.puntos_visitante - partidos.puntos_local) >40;");
			while (rs2.next()) {
				equipos.add(rs2.getString("equipo_local"));
			}
			miStatement.close();
			rs1.close();
			rs2.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en nombreEquiposApalizados en BDController");
		}

		
		return equipos;
		
	}
}
