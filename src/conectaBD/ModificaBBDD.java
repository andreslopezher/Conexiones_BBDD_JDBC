package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModificaBBDD {

	public static void main(String[] args) {

		try {

			// 1. Crear conexión
			Connection miConexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pildoras_infor?useLegacyDatetimeCode=false&serverTimezone=UTC", "root",
					"12345");

			// 2. Crear objeto Statement
			Statement miStatement = miConexion.createStatement();
			
			//String instruccionSql = "insert into productos (codarticulo,nombrearticulo,seccion,precio) values('AR33','TALADRO NEUMATICO','CONSTRUCCION',125000)";
            //String instruccionSql = "update productos set precio = precio * 2 where codarticulo = 'AR33'";
			String instruccionSql = "delete from productos where codarticulo = 'AR33'";
			miStatement.executeUpdate(instruccionSql);
            System.out.println(".: Datos insertados correctamente!");

		} catch (Exception e) {
			System.out.println(".: No Conecta");
			e.printStackTrace();
		}

	}

}
