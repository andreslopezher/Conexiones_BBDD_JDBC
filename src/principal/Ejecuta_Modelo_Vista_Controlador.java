package principal;

import javax.swing.JFrame;

import vista.Marco_Aplicacion2;

public class Ejecuta_Modelo_Vista_Controlador {

	public static void main(String[] args) {
		
		Marco_Aplicacion2 miMarco = new Marco_Aplicacion2();
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miMarco.setVisible(true);

	}

}
