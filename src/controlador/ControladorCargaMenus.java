package controlador;

import java.awt.event.*;
import modelo.*;
import vista.*;

public class ControladorCargaMenus extends WindowAdapter {

	CargaMenus obj = new CargaMenus();
	private Marco_Aplicacion2 elMarco;
	
	public ControladorCargaMenus(Marco_Aplicacion2 elMarco) {
		
		this.elMarco = elMarco;
		
	}
	
	public void windowOpened(WindowEvent e) {

		obj.ejecutaConsultas();
		
		try {
			while (obj.rs.next()) {
				elMarco.secciones.addItem(obj.rs.getString(1));
			}
		} catch(Exception ex) {
			System.out.println(".: Error al cargar secciones:");
			ex.printStackTrace();
		}

		try {
			while(obj.rs2.next()) {
				elMarco.paises.addItem(obj.rs2.getString(1));
			}
		} catch(Exception ex) {
			System.out.println(".: Error al cargar paises:");
			ex.printStackTrace();
		}

	}
	
}
