package procAlmacenado;

import java.sql.*;

import javax.swing.JOptionPane;

import modelo.Conexion;

public class ActualizaProductos {
	
	public static void main(String[] args) {
		int precio = Integer.parseInt(JOptionPane.showInputDialog("Introduce el precio."));
		String articulo = JOptionPane.showInputDialog("Introduce el nombre del articulos.");
		try {
			Conexion miConexion = new Conexion();
			Connection con = miConexion.dameConexion();
			CallableStatement miSentencia = con.prepareCall("{call actualiza_producto(?,?)}");
			miSentencia.setInt(1,precio);
			miSentencia.setString(2,articulo);
			miSentencia.execute();
			System.out.println(".: Actualizacion realizada");
		} catch (Exception e) {
			System.out.println(".: Error al realizar actualizacion del producto.");
			e.printStackTrace();
		}
	}
	

}
