package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import controlador.Controlador;
import dominio.Contacto;
import interfaz.VentanaInicial;


public class Lanzador {
	public static void main(final String[] args){	
		Controlador controlador = Controlador.INSTANCE;
		
		controlador.registrarUsuario("Maria","Perez","maria.p@um.es", "mary", "624673910","maria","22.03");
		controlador.registrarUsuario("Paula","Gonzales", "paula.g@um.es", "paulaGo","647479157", "papapa","30.12");
		controlador.registrarUsuario("Antonio","Sanchez", "antony@um.es", "antony", "633567819", "123", "4.06");
		controlador.registrarUsuario("Daniel", "Lopez", "daniL@um.es", "dani", "6337894238", "contraseña", "07.09");
		controlador.registrarUsuario("Carlos", "Alonso", "charly@um.es", "charly", "622785638", "chachi", "19.07");
		controlador.registrarUsuario("Carmen", "Alvarez", "carmen.a@um.es", "carmen", "645385911", "CaRmEn", "01.02");
		controlador.registrarUsuario("Alejandro", "Jimenez", "alejandro.j@um.es", "alex", "634568319", "1111", "09.03");
		controlador.registrarUsuario("Jose", "Garcia", "jose.g@um.es", "jose.g", "655224817", "joseg", "17.08");
		System.out.println("Todos los usuarios registrados.");
		
		/*
		controlador.loginUsuario("Maria", "maria");
		System.out.println("Login exitoso.");
		
		controlador.añadirContactoIndividual("Paula", "647479157");
		controlador.añadirContactoIndividual("Dani", "6337894238");
		System.out.println("Todos los contactos añadidos.");
		
		Controlador.INSTANCE.enviarMensaje("Paula", "Hola!", 0);
		Controlador.INSTANCE.enviarMensaje("Maria", "Hola. Qué tal?", 1);
		System.out.println("Todos los mensajes enviados.");
		*/
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