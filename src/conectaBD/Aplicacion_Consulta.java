package conectaBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Aplicacion_Consulta{

	public static void main(String[] args) {
		
		JFrame mimarco=new Marco_Aplicacion();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mimarco.setVisible(true);

	}

}

class Marco_Aplicacion extends JFrame{
	
	public Marco_Aplicacion(){
		
		setTitle ("Consulta BBDD");
		
		setBounds(500,300,400,400);
		
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		
		menus.setLayout(new FlowLayout());
		
		secciones=new JComboBox();
		
		secciones.setEditable(false);
		
		secciones.addItem("Todos");
		
		paises=new JComboBox();
		
		paises.setEditable(false);
		
		paises.addItem("Todos");
		
		resultado= new JTextArea(4,50);
		
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(secciones);
		
		menus.add(paises);	
		
		add(menus, BorderLayout.NORTH);
		
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta = new JButton("Consulta");

		botonConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutaConsulta();
			}			
		});
		
		add(botonConsulta, BorderLayout.SOUTH);
		
		//=========================================
		try {
			
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pildoras_infor?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "12345");
			Statement sentencia = miConexion.createStatement();
			
			//Carga JComboBox Secciones
			String sql = "select distinctrow seccion from productos";
			ResultSet rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				secciones.addItem(rs.getString(1));
			}
			rs.close();

			//Carga JComboBox Paises
			sql = "select distinctrow paisdeorigen from productos";
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				paises.addItem(rs.getString(1));
			}
			rs.close();
		
		} catch (Exception e) {

		}		
		
	}
	
	private void ejecutaConsulta() {
		
		ResultSet rs = null;
		
		try {
			resultado.setText("");
			String seccion = (String) secciones.getSelectedItem();
			String pais = (String) paises.getSelectedItem();

			if (!seccion.equals("Todos") && pais.equals("Todos")) {	
				enviaConsultaSeccion = miConexion.prepareStatement(consultaSeccion);
				enviaConsultaSeccion.setString(1, seccion);
				rs = enviaConsultaSeccion.executeQuery();
			} else if (seccion.equals("Todos") && !pais.equals("Todos")) {
				enviaConsultaPais = miConexion.prepareStatement(consultaPais);
				enviaConsultaPais.setString(1, pais);
				rs = enviaConsultaPais.executeQuery();
			} else if (!seccion.equals("Todos") && !pais.equals("Todos")){
				enviaConsultaSeccionPais = miConexion.prepareStatement(consultaSeccionPais);
				enviaConsultaSeccionPais.setString(1, seccion);
				enviaConsultaSeccionPais.setString(2, pais);
				rs = enviaConsultaSeccionPais.executeQuery();
			} else {
				enviaConsultaTodos = miConexion.prepareStatement(consultaTodos);
				rs = enviaConsultaTodos.executeQuery();
			}
			
			while (rs.next()) {
				resultado.append(rs.getString("nombrearticulo") + ", " +
								 rs.getString("seccion") + ", " +
								 rs.getString("precio") + ", " +
								 rs.getString("paisdeorigen"));
				resultado.append("\n");
			}
			rs.close();
			
		} catch (Exception e) {

		}
		
	}

	private Connection miConexion;
	private PreparedStatement enviaConsultaTodos;
	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaSeccionPais;
	private final String consultaTodos = "select nombrearticulo,seccion,precio,paisdeorigen from productos";
	private final String consultaSeccion = "select nombrearticulo,seccion,precio,paisdeorigen from productos where seccion = ?";
	private final String consultaPais = "select nombrearticulo,seccion,precio,paisdeorigen from productos where paisdeorigen = ?";
	private final String consultaSeccionPais = "select nombrearticulo,seccion,precio,paisdeorigen from productos where seccion = ? and paisdeorigen = ?";
	private JComboBox<String> secciones;
	private JComboBox<String> paises;	
	private JTextArea resultado;
	
}
