package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class Utilidades {
	// Definición de colores como constantes
	public static final Color VERDE_FONDO = new Color(111, 204, 115);
	public static final Color VERDE_CLARO = new Color(199, 235, 201);
	public static final Color VERDE_LABELS = new Color(215, 255, 215);
	public static final Color VERDE_BOTONES = new Color(40, 167, 69);

	// Método para configurar los botones
	public static void crearBoton(JButton boton, int minDimension, int maxDimension, int tamano) {
		boton.setFont(new Font("Arial", Font.BOLD, tamano));
		boton.setBackground(VERDE_BOTONES);
		boton.setForeground(Color.BLACK);
		boton.setOpaque(true);
		boton.setBorderPainted(false);
		boton.setPreferredSize(new Dimension(minDimension, maxDimension));
		boton.setMinimumSize(new Dimension(minDimension, maxDimension));
		boton.setMaximumSize(new Dimension(minDimension, maxDimension));
	}
}
