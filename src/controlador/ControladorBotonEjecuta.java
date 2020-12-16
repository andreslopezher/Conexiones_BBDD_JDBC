package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import modelo.*;
import vista.*;

public class ControladorBotonEjecuta implements ActionListener {
	
	private Marco_Aplicacion2 elMarco;
	private ResultSet resultadoConsulta;
	EjecutaConsultas obj = new EjecutaConsultas();
	
	public ControladorBotonEjecuta(Marco_Aplicacion2 elMarco) {
		this.elMarco = elMarco;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String seccion = (String) elMarco.secciones.getSelectedItem();
		String pais = (String) elMarco.paises.getSelectedItem();
		elMarco.resultado.setText(null);
		resultadoConsulta = obj.filtaBBDD(seccion, pais);
		try {
			while (resultadoConsulta.next()) {
				elMarco.resultado.append(resultadoConsulta.getString(1) + ", ");
				elMarco.resultado.append(resultadoConsulta.getString(2) + ", ");
				elMarco.resultado.append(resultadoConsulta.getString(3) + ", ");
				elMarco.resultado.append(resultadoConsulta.getString(4));
				elMarco.resultado.append("\n");
			}	
		} catch (Exception ex) {
			System.out.println(".: Error al recorrer resultado de consulta:");
			ex.printStackTrace();
		}
	}
	

}
