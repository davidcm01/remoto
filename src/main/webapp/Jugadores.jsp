<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="NBA.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
				<%BDController bdcontroller = new BDController();
				ArrayList<Jugador> jugadores = bdcontroller.todosJugadores();%>
	<div class="container">
        <table>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Procedencia</th>
                    <th>Altura</th>
                    <th>Peso</th>
                    <th>posicion</th>
                    <th>Nombre del equipo</th>
                </tr>
            </thead>
            <tbody>
             <% for(int i = 0;i<jugadores.size();i++){%>
                <tr>
                    <td><%out.print(jugadores.get(i).getCod()); %></td>
                    <td><%out.print(jugadores.get(i).getNombre()); %></td>
                    <td><%out.print(jugadores.get(i).getProcedencia()); %></td>
                    <td><%out.print(jugadores.get(i).getAltura()); %></td>
                    <td><%out.print(jugadores.get(i).getPeso()); %></td>
                    <td><%out.print(jugadores.get(i).getPosicion()); %></td>
                    <td><%out.print(jugadores.get(i).getNombre_equipo()); %></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
				
</body>
</html>