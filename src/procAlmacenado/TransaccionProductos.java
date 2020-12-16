package procAlmacenado;

import java.sql.*;

import javax.swing.JOptionPane;

import modelo.Conexion;

public class TransaccionProductos {
	
	private static boolean ejecutar_transaccion() {
		String ejecutar = JOptionPane.showInputDialog("¿ Desea ejecutar la transacción (S/N)?");
		if (ejecutar.toUpperCase().equals("S"))
			return true;
		return false;
	}

	public static void main(String[] args) {
		Conexion miConexion = new Conexion();
		Connection con = miConexion.dameConexion();
		try {
			con.setAutoCommit(false);
			Statement miStatement = con.createStatement();
			String sql1 = "delete from productos where paisdeorigen = 'COLOMBIA'";
			String sql2 = "delete from productos where precio >= 7000";
			String sql3 = "update productos set precio = precio * 1.15";
			boolean ejecutar = ejecutar_transaccion();
			if (ejecutar) {
				miStatement.executeUpdate(sql1);
				miStatement.executeUpdate(sql2);
				miStatement.executeUpdate(sql3);
				con.commit();
				System.out.println(".: Actualizacion de productos realizada!");	
			} else {
				System.out.println(".: No se realizó cambio en BBDD.");
			}
			con.close();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(".: Error al realizar el rollback:");
				e1.printStackTrace();
			}
			System.out.println(".: Error al actualizar productos (No se realizó cambio en BBDD):");
			e.printStackTrace();
		}
	}

}
