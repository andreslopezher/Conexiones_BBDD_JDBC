package conectaBD;
import java.sql.*;

public class Conecta_Pildoras_Infor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			//1. Crear conexión
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pildoras_infor?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "12345");
			
			//2. Crear objeto Statement
			Statement miStatement = miConexion.createStatement();
			
			//3. Ejecutar instrucción SQL
			ResultSet miResultSet = miStatement.executeQuery("select * from productos");
			
			//4. Recorrer el ResultSet
			while (miResultSet.next()) {
				System.out.println(miResultSet.getString("nombrearticulo") + " " + 
						           miResultSet.getString("codarticulo") + " " + 
						           miResultSet.getString("precio") + " " + 
						           miResultSet.getDate("fecha"));
			}
			
			miResultSet.close();
			
		} catch (Exception e) {
			System.out.println(".: No Conecta");
			e.printStackTrace();
		}

	}

}
