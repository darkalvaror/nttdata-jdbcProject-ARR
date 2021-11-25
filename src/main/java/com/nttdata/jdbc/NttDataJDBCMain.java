package com.nttdata.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author arivarin
 * 
 * Conexión con MySQL
 *
 */
public class NttDataJDBCMain {
	private static void stablishODBConnection() {
		try {

			// Se establece el driver de conexión a BBDD.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Apertura de conexión con BBDD
			final Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/alquiler", "root", "rootroot");

			// Realización de consulta
			final Statement sentencia = conexion.createStatement();
//			final String consulta = "SELECT sp.name AS playerName, st.name AS teamName, sp.first_rol AS rol1, sp.second_rol AS rol2, sp.birth_date AS birthD FROM nttdata_mysql_soccer_player sp JOIN nttdata_mysql_soccer_team st ON sp.id_soccer_team = st.id_soccer_team";
			final String consulta = "SELECT cl.Adress AS Adress, cl.Credit_Card_Number AS CreditCard, cl.Driver_Name AS DriverName, cl.Phone_Number AS PhoneNumber FROM cliente cl";

			final ResultSet resultadoConsulta = sentencia.executeQuery(consulta);

			// Iteración de resultados
			StringBuilder playerInfo = new StringBuilder();
			while (resultadoConsulta.next()) {
				
				// Dirección del cliente
				playerInfo.append("Adress: ");
				playerInfo.append(resultadoConsulta.getString("Adress"));
				
				// Tarejta de credito del cliente
				playerInfo.append(" /Credit card: ");
				playerInfo.append(resultadoConsulta.getString("CreditCard"));
				
				// Nombre del conductor
				playerInfo.append(" /Driver name: ");
				playerInfo.append(resultadoConsulta.getString("DriverName"));
				
				// Teléfono del cliente
				playerInfo.append(" /Phone number: ");
				playerInfo.append(resultadoConsulta.getString("PhoneNumber"));
				
				playerInfo.append("\n");
			}

			System.out.println(playerInfo.toString());

			// Cierre de conexión con BBDD.
			conexion.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		stablishODBConnection();
	}
}
