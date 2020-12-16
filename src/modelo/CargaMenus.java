package modelo;

import java.sql.*;

public class CargaMenus {
	
	public ResultSet rs;
	public ResultSet rs2;
	private Conexion miConexion;

	public CargaMenus() {
		
		miConexion = new Conexion();
		
	}
	
	/*
	public String ejecutaConsultas() {
		
		Productos miProducto = null;
		Connection accesoBBDD = miConexion.dameConexion();
		
		try {
			Statement secciones = accesoBBDD.createStatement();
			rs = secciones.executeQuery("select distinctrow seccion from productos");
			rs.previous();
			miProducto = new Productos();
			miProducto.setSeccion(rs.getString("seccion"));
			rs.close();
		} catch(Exception e) {
			System.out.println(".: Error al consultar secciones:");
			e.printStackTrace();
		//} finally {
		//	miConexion.finalizaConexion(accesoBBDD);
		}
		
		return miProducto.getSeccion();
	}
	*/
	
	public ResultSet ejecutaConsultas() {
		Connection accesoBBDD = miConexion.dameConexion();
		try {
			//Consultar secciones
			Statement secciones = accesoBBDD.createStatement();
			rs = secciones.executeQuery("select distinctrow seccion from productos order by seccion asc");
			
			//Consultar paises
			Statement paises = accesoBBDD.createStatement();
			rs2 = paises.executeQuery("select distinctrow paisdeorigen from productos order by paisdeorigen asc");

		} catch (Exception e) {
			System.out.println(".: Error al consultar secciones y/o paises:");
			e.printStackTrace();
		} 
		return null;
	}

}
