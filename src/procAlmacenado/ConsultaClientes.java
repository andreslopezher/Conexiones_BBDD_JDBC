package procAlmacenado;

import java.sql.*;
import modelo.Conexion;

public class ConsultaClientes {

	public static void main(String[] args) {
		try {
			Conexion miConexion = new Conexion();
			Connection con = miConexion.dameConexion();
			CallableStatement miSentencia = con.prepareCall("{call muestra_clientes}");
			ResultSet rs = miSentencia.executeQuery();
			while (rs.next()){
				System.out.println(rs.getString(1) + ' ' +
						           rs.getString(2) + ' ' + 
						           rs.getString(3) + ' ' + 
						           rs.getString(4) + '\n');
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(".: Error Consultando Clientes:");
			e.printStackTrace();
		}
	}

}
