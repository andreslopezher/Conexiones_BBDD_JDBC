package metaDatos;

import java.sql.*;
import modelo.Conexion;

public class InforMetaDatos {

	public static void main(String[] args) {
		//mostrarInfo_BBDD();
		mostrarInfo_Tablas();
	}
	
	static void mostrarInfo_BBDD() {
		Conexion con = new Conexion();
		Connection miConexion = null;
		try {
			miConexion = con.dameConexion();
			
			//Obtener metadados:
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			
			//Mostrar información de la BBDD
			System.out.println(" Gestor de BBDD: " + datosBBDD.getDatabaseProductName());
			System.out.println(" Versión del gestor: " + datosBBDD.getDatabaseProductVersion());
			System.out.println(" Nombre del driver: " + datosBBDD.getDriverName());
			System.out.println(" Versión del driver: " + datosBBDD.getDriverVersion());
			
		} catch (Exception e) {
			System.out.println(".: Error al mostrar información BBDD:");
			e.printStackTrace();
		} finally {
			try {
				miConexion.close();
			} catch (SQLException e) {
				System.out.println(".: Error al cerrar conexión (mostrar información BBDD):");
				e.printStackTrace();
			}
		}
	}
	
	static void mostrarInfo_Tablas() {
		Conexion con = new Conexion();
		Connection miConexion = null;
		ResultSet rs = null;
		try {
			miConexion = con.dameConexion();
			
			//Obtener metadados:
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			
			//Lista de tablas
			System.out.println(" Lista de Tablas:");
			rs = datosBBDD.getTables(null, null, null, null);
			
			while (rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}
			
			rs.close();
			
            //Lista de columnas tabla Productos:
			System.out.println(" Columnas Tabla Productos:");
			rs = datosBBDD.getColumns(null, null, "productos", null);
			
			while (rs.next()) {
				System.out.println(rs.getString("COLUMN_NAME"));
			}
			
			rs.close();
			
		} catch (Exception e) {
			System.out.println(".: Error al mostrar información Tablas:");
			e.printStackTrace();
		} finally {
			try {
				miConexion.close();
			} catch (SQLException e) {
				System.out.println(".: Error al cerrar conexión (mostrar información Tablas):");
				e.printStackTrace();
			}
		}
		
	}
	

}
