package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import controlador.Controlador;
import dominio.Contacto;
import interfaz.VentanaInicial;


public class Lanzador {
	public static void main(final String[] args){	
		Controlador controlador = Controlador.INSTANCE;
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					VentanaInicial ventana = new VentanaInicial();
					ventana.mostrarVentana();
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}
		});
	}
}