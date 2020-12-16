package modelo;

import java.sql.*;

public class Conexion {
	
	Connection miConexion = null;
	
	public Conexion() {
		
	}
	
	public Connection dameConexion() {
		
		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pildoras_infor?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "12345");
		} catch(Exception e) {
			System.out.println(".: Error al Conectar!!");
			e.printStackTrace();
		}
		
		return miConexion;
		
	}
	
	/*
	public void finalizaConexion(Connection conexion) {
	    if (conexion != null) {
	        try {
	            conexion.close();
	            conexion = null;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage() + ". >>> Error de Desconexion!!");
	            e.printStackTrace();
	        }
	    }
	}
	*/

}
