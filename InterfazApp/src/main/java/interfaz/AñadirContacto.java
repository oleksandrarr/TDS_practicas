package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controlador.Controlador;
import dominio.Contacto;
import dominio.ContactoIndividual;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class AñadirContacto {

	private JFrame frame;
	private JPanel panelBotones;
	private JPanel panelCentro;
	private JTextField textoNombre;
	private JTextField textoTelefono;
	private Runnable onContactoAñadido; // Callback
	private VentanaPrincipal ventanaPrincipal;

	public AñadirContacto(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		initialize();
	}

	public AñadirContacto(VentanaPrincipal ventanaPrincipal, Runnable onContactoAñadido) {
		this.onContactoAñadido = onContactoAñadido;
		this.ventanaPrincipal = ventanaPrincipal;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Añadir Contacto");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		añadirPanelBotones();
		añadirPanelCentro();
	}

	private void añadirPanelBotones() {
		panelBotones = new JPanel();
		panelBotones.setBackground(Utilidades.VERDE_FONDO);
		frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Aceptar");
		Utilidades.crearBoton(btnNewButton, 100, 30, 12);
		panelBotones.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		Utilidades.crearBoton(btnNewButton_1, 100, 30, 12);
		panelBotones.add(btnNewButton_1);

		// boton cancelar
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cerrar la ventana
			}
		});

		// boton aceptar
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = textoNombre.getText();
				String telefono = textoTelefono.getText();
				ContactoIndividual c = Controlador.INSTANCE.añadirContactoIndividual(nombre, telefono);
				if (c == null) {
					JOptionPane.showMessageDialog(frame, "Ya hay un contacto registrado con este teléfono.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {

					if (onContactoAñadido != null) {
						onContactoAñadido.run();
					}
					frame.dispose();
				}

				ventanaPrincipal.actualizarComboBox();
				frame.revalidate();
				frame.repaint();
			}
		});

	}

	private void añadirPanelCentro() {
		panelCentro = new JPanel();
		panelCentro.setBackground(Utilidades.VERDE_FONDO);
		frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[] { 100, 300, 25 }; // Ancho de las columnas
		gbl_panelCentro.rowHeights = new int[] { 50, 50, 50, 50, 50 };
		gbl_panelCentro.columnWeights = new double[] { 0.0, 1.0 }; // Peso para permitir expansión horizontal
		gbl_panelCentro.rowWeights = new double[] { 0, 0, 0.0, 0, 0 };
		panelCentro.setLayout(gbl_panelCentro);

		JLabel lblNewLabel = new JLabel("Introduzca el nombre del contacto y su teléfono");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panelCentro.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		panelCentro.add(lblNombre, gbc_lblNombre);

		JLabel lblNewLabel_1 = new JLabel("Teléfono:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		panelCentro.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textoNombre = new JTextField();
		textoNombre.setBackground(new Color(199, 235, 201));
		GridBagConstraints gbc_textoNombre = new GridBagConstraints();
		gbc_textoNombre.insets = new Insets(5, 5, 0, 0);
		gbc_textoNombre.gridx = 1;
		gbc_textoNombre.gridy = 2;
		gbc_textoNombre.weightx = 1.0; // Asigna peso para expansión horizontal
		gbc_textoNombre.fill = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
		panelCentro.add(textoNombre, gbc_textoNombre);

		textoTelefono = new JTextField();
		textoTelefono.setBackground(new Color(199, 235, 201));
		GridBagConstraints gbc_textoTelefono = new GridBagConstraints();
		gbc_textoTelefono.insets = new Insets(5, 5, 0, 0);
		gbc_textoTelefono.gridx = 1;
		gbc_textoTelefono.gridy = 3;
		gbc_textoTelefono.weightx = 1.0; // Asigna peso para expansión horizontal
		gbc_textoTelefono.fill = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
		panelCentro.add(textoTelefono, gbc_textoTelefono);

	}

}
