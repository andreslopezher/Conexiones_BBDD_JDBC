package aplicacionFinal;

import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.jdbc.DatabaseMetaData;
import modelo.Conexion;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class AplicacionUniversal {
	
	public static void main (String[] args) {
		
		MarcoBBDD miMarco = new MarcoBBDD();
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miMarco.setVisible(true);

	}

}

class MarcoBBDD extends JFrame {
	
	public MarcoBBDD() {
	
		setBounds(300, 300, 700, 700);
		LaminaBBDD miLamina = new LaminaBBDD();
		add(miLamina);

	}
	
}

class LaminaBBDD extends JPanel {
	
	private JComboBox comboTablas;
	private JTextArea areaInformacion;
	private Connection miConexion;
	private FileReader entrada;
	
	public LaminaBBDD() {
		
		setLayout (new BorderLayout());
		comboTablas = new JComboBox();
		areaInformacion = new JTextArea();
		add(areaInformacion, BorderLayout.CENTER);
		conectarBBDD();
		obtenerTablas();
	
		comboTablas.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nombreTabla = (String)comboTablas.getSelectedItem();
				
				mostrarInfoTablas(nombreTabla);
				
			}
			
		});
		
		add(comboTablas, BorderLayout.NORTH);

	}
	
	public void conectarBBDD() {
		
		miConexion = null;
		
		String datos[] = new String[3];
		
		String rutaArchivo = "/Users/AndresLopez/Documents/eclipse-workspace/BBDD_JDBC/src/aplicacionFinal/datos_confgig.txt";

		try {
			
			entrada = new FileReader(rutaArchivo);

		} catch(IOException e) {
			
			//JOptionPane.showMessageDialog(this, "No se encontro el archivo para realizar conexión. Por favor Seleccione el mismo.");
			
			JFileChooser chooser = new JFileChooser();
			
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Texto", "txt");
		    
		    chooser.setFileFilter(filter);
		    
		    int returnVal = chooser.showOpenDialog(this);
		    
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		        
		    	rutaArchivo = chooser.getSelectedFile().getAbsolutePath();
		    	
		    	try {
				
		    		entrada = new FileReader(rutaArchivo);
				
		    	} catch (FileNotFoundException e1) {
					
		    		System.out.println(".: No se encontro el archivo para realizar conexión.");
					
					e1.printStackTrace();
				}
		    }
			
		}

		try {
						
			BufferedReader miBuffer = new BufferedReader(entrada);
			
			for (int i = 0; i <= 2; i++) {
				
				datos[i] = miBuffer.readLine();
				
			}
			
			//miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pildoras_infor?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "12345");
		    
			miConexion = DriverManager.getConnection(datos[0],datos[1],datos[2]);
			
			entrada.close();
				
			
		} catch(Exception e) {
			
			System.out.println(".: Error al conectar con BBDD:");
			e.printStackTrace();
			
		}
		
	}
	
	public void obtenerTablas() {
		
		ResultSet miResultSet = null;
		
		try {
			
			DatabaseMetaData datosBBDD = (DatabaseMetaData) miConexion.getMetaData();
			
			miResultSet = datosBBDD.getTables(null, null, null, null);
			
			while(miResultSet.next()) {
				
				comboTablas.addItem(miResultSet.getString("TABLE_NAME"));
			
			}
		
		} catch (Exception e) {
			
			System.out.println(".: Error al obtener tablas.");
			e.printStackTrace();
		
		}
		
	}
	
	public void mostrarInfoTablas(String tabla) {
		
		ArrayList<String> campos = new ArrayList<String>();
		String consulta = "select * from " + tabla;
		
		try {
			
			areaInformacion.setText("");
			Statement miStatement = miConexion.createStatement();
			ResultSet miResultSet = miStatement.executeQuery(consulta);
			ResultSetMetaData rsBBDD = miResultSet.getMetaData();
			
			for(int i = 1; i <= rsBBDD.getColumnCount(); i++) {
				
				campos.add(rsBBDD.getColumnLabel(i));
				
			}
			
			while (miResultSet.next()) {
				for (String nombreCampo:campos) {
					areaInformacion.append(miResultSet.getString(nombreCampo) + " ");
				}
				areaInformacion.append("\n");
			}

			
		} catch(Exception e) {
			
			System.out.println(".: Error al mostrar informacion tabla:");
			e.printStackTrace();
			
		}
		
	}
	
} 