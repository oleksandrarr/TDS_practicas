package interfaz;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

public class VentanaAjustes {

	private JFrame frame;
	private JTextField urlField;
	private JTextField saludoField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAjustes window = new VentanaAjustes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaAjustes() {
		initialize();
	}
	
	public void mostrarVentana() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Ajustes");
		frame.setBounds(100, 100, 300, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBackground(Utilidades.VERDE_FONDO);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelPrincipal.setBackground(Utilidades.VERDE_FONDO);
		frame.getContentPane().add(panelPrincipal);

		//Foto
		JLabel etiquetaUrl = new JLabel("URL de la imagen:");
		etiquetaUrl.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		panelPrincipal.add(etiquetaUrl);

		urlField = new JTextField();
		urlField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		panelPrincipal.add(urlField);

		// Espaciador para separar secciones
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

		// Saludo
		JLabel etiquetaSaludo = new JLabel("Saludo:");
		etiquetaSaludo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		panelPrincipal.add(etiquetaSaludo);

		saludoField = new JTextField();
		saludoField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		panelPrincipal.add(saludoField);

		// Espaciador para separar secciones
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

		// Panel para los botones
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Utilidades.VERDE_FONDO);
		panelPrincipal.add(panelBotones);

		// Botón "Aceptar"
		JButton btnAceptar = new JButton("Aceptar");
		Utilidades.crearBoton(btnAceptar, 100, 30, 12);
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String saludo = saludoField.getText();
				String urlImagen = urlField.getText();

				URL img = null;
				try {
					if (!urlImagen.isEmpty()) {
						Controlador.INSTANCE.getUsuarioActual().setImagen(new URL(urlImagen));
					}
				} catch (MalformedURLException e1) {
					JOptionPane.showMessageDialog(frame, "La URL proporcionada no es válida.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!saludo.isEmpty()) {
					Controlador.INSTANCE.getUsuarioActual().setSaludo(saludo);
				frame.dispose();
			}
			}
		});
		panelBotones.add(btnAceptar);

		// Botón "Cancelar"
		JButton btnCancelar = new JButton("Cancelar");
		Utilidades.crearBoton(btnCancelar, 100, 30, 12);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panelBotones.add(btnCancelar);
		
	}

}
