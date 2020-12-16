package modelo;

import java.sql.*;

public class EjecutaConsultas {

	private Conexion miConexion;
	private ResultSet rs;
	private PreparedStatement enviaConsultaTodos;
	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaSeccionPais;
	private final String consultaTodos = "select nombrearticulo,seccion,precio,paisdeorigen from productos";
	private final String consultaSeccion = "select nombrearticulo,seccion,precio,paisdeorigen from productos where seccion = ?";
	private final String consultaPais = "select nombrearticulo,seccion,precio,paisdeorigen from productos where paisdeorigen = ?";
	private final String consultaSeccionPais = "select nombrearticulo,seccion,precio,paisdeorigen from productos where seccion = ? and paisdeorigen = ?";

	public EjecutaConsultas() {
		miConexion = new Conexion();
	}
	
	public ResultSet filtaBBDD(String seccion, String pais) {
	
		Connection conecta = miConexion.dameConexion();
		rs = null;

		try {
			if (!seccion.equals("Todos") && pais.equals("Todos")) {	
				enviaConsultaSeccion = conecta.prepareStatement(consultaSeccion);
				enviaConsultaSeccion.setString(1, seccion);
				rs = enviaConsultaSeccion.executeQuery();
			} else if (seccion.equals("Todos") && !pais.equals("Todos")) {
				enviaConsultaPais = conecta.prepareStatement(consultaPais);
				enviaConsultaPais.setString(1, pais);
				rs = enviaConsultaPais.executeQuery();
			} else if (!seccion.equals("Todos") && !pais.equals("Todos")){
				enviaConsultaSeccionPais = conecta.prepareStatement(consultaSeccionPais);
				enviaConsultaSeccionPais.setString(1, seccion);
				enviaConsultaSeccionPais.setString(2, pais);
				rs = enviaConsultaSeccionPais.executeQuery();
			} else {
				enviaConsultaTodos = conecta.prepareStatement(consultaTodos);
				rs = enviaConsultaTodos.executeQuery();
			}
		} catch (Exception e) {
			System.out.println(".: Error al ejecutar consultas:");
			e.printStackTrace();
		}

		return rs;
		
	}
	
	
}
