package modelo;

import java.awt.Color;

import tds.BubbleText;

public class Mensaje {
	 	private String contenido;
	 	private String color =  new String("Color.Green");
	    private String usuario;
	    private int sentRecib;  
	    private int fuente;
	    
		public Mensaje(String contenido, String color, String usuario, int sentRecib, int fuente) {
			super();
			this.contenido = contenido;
			this.color = color;
			this.usuario = usuario;
			this.sentRecib = sentRecib;
			this.fuente = fuente;
		}
	    
	    
	    
	
}
