package conectaBD;

import java.sql.*;


public class ConsultaPreparada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			// 1. Crear conexión
			Connection miConexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pildoras_infor?useLegacyDatetimeCode=false&serverTimezone=UTC", "root",
					"12345");
			
			//2. Prepara Consulta
			PreparedStatement miSentencia = miConexion.prepareStatement("select nombrearticulo,seccion,paisdeorigen from productos"
					+" where seccion = ? and paisdeorigen = ?");
			
			//3. Establecer los parametros de consulta
			miSentencia.setString(1, "FERRETERIA");
			miSentencia.setString(2, "COLOMBIA");
			
			//4. Ejecutar y recorrer consulta
			ResultSet rs = miSentencia.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("nombrearticulo") + " " +
						           rs.getString("seccion") + " " +
						           rs.getString("paisdeorigen"));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(".: Error al consultar");
			e.printStackTrace();
		}

	}

}
