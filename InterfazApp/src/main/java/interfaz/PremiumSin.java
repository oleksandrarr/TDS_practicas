package interfaz;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class PremiumSin {

	private JFrame frame;
	private JPanel panelNombre;
	private JPanel panelBotones;
	private JPanel panelCentro;

	/**
	 * Create the application.
	 */
	public PremiumSin() {
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Premium");
		frame.setBackground(Utilidades.VERDE_FONDO);

		añadirPanelNombre();
		añadirPanelCentro();
		añadirPanelBotones();
	}

	private void añadirPanelNombre() {
		panelNombre = new JPanel();
		frame.getContentPane().add(panelNombre, BorderLayout.NORTH);
		panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.Y_AXIS));
		panelNombre.setBackground(Utilidades.VERDE_FONDO);

		panelNombre.add(Box.createVerticalStrut(10));

		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());

		// Título "Premium"
		JLabel lblTitulo = new JLabel("Premium");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		box.add(lblTitulo);

		// Botón pequeño y redondo para información
		JButton infoButton = new JButton("i");
		infoButton.setFont(new Font("Arial", Font.BOLD, 10));
		infoButton.setToolTipText("Haz clic para más información");
		infoButton.setFocusPainted(false);
		infoButton.setBorderPainted(false);
		infoButton.setContentAreaFilled(false);
		infoButton.setOpaque(true);
		infoButton.setBackground(Color.LIGHT_GRAY);
		infoButton.setForeground(Color.BLACK);
		infoButton.setBorder(new LineBorder(Color.DARK_GRAY, 1, true)); // Bordes redondeados

		infoButton.setPreferredSize(new java.awt.Dimension(20, 20)); // Tamaño del botón
		infoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Mostrar información adicional en un JOptionPane
				JOptionPane.showMessageDialog(frame, "Información sobre el Premium:\n\n"
						+ "El precio normal es de 15 euros anuales.\n"
						+ "El descuento de 15% se aplica a los usuarios que habían enviado más de 100 mensajes \n en el último mes.\n"
						+ "El descuento de 20% se aplica a los usuarios que llevan más de 4 semanas registrados.",
						"Información Premium", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		box.add(Box.createHorizontalStrut(5)); // Espacio entre título y botón
		box.add(infoButton);

		box.add(Box.createHorizontalGlue());
		panelNombre.add(box);

		panelNombre.add(Box.createVerticalStrut(10));
	}

	private void añadirPanelBotones() {
		panelBotones = new JPanel();
		panelBotones.setBackground(Utilidades.VERDE_FONDO);
		frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		JButton btnNewButton_2 = new JButton("Calcular descuento");
		Utilidades.crearBoton(btnNewButton_2, 150, 30, 12);
		panelBotones.add(btnNewButton_2);

		JButton btnNewButton = new JButton("Cancelar");
		Utilidades.crearBoton(btnNewButton, 100, 30, 12);
		panelBotones.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Comprar");
		Utilidades.crearBoton(btnNewButton_1, 100, 30, 12);
		panelBotones.add(btnNewButton_1);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana
			}
		});

		// calcular descuento
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double descuento = Controlador.INSTANCE.getUsuarioActual().calcularDescuento();
				int descuentoPorcentaje = (int) (descuento * 100);
				int coste = (int) (15 - (15 * descuento));
				if (descuento == 0.0) {
					JOptionPane.showMessageDialog(frame,
							"No tiene descuento disponible.\n " + "Precio final a pagar: " + coste,
							"Información Descuento", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame,
							"Tiene el descuento disponible de: " + descuentoPorcentaje + "\n Precio final a pagar: " + coste,
							"Información Descuento", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// comprar
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.hacerPremium(true);
				JOptionPane.showMessageDialog(frame, "Tiene el Premium.", "Premium Pagado",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
			}
		});
	}

	private void añadirPanelCentro() {
		panelCentro = new JPanel();

		frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setBackground(Utilidades.VERDE_FONDO);

		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[] { 25, 250, 100, 25 };
		gbl_panelCentro.rowHeights = new int[] { 25, 75, 75, 25 };
		gbl_panelCentro.columnWeights = new double[] { 0.0, 1.0, 0.5, 0.0 };
		gbl_panelCentro.rowWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
		panelCentro.setLayout(gbl_panelCentro);

		

		JLabel lblNewLabel_1 = new JLabel("Fecha de registro:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panelCentro.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		Controlador.INSTANCE.getUsuarioActual().setFechaRegistro(LocalDate.of(2024, 9, 14));
		LocalDate fechaRegistro = Controlador.INSTANCE.getUsuarioActual().getFechaRegistro();
		JLabel lblNewLabel_4 = new JLabel(fechaRegistro.toString());
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 1;
		panelCentro.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_2 = new JLabel("Mensajes enviados en el último mes:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panelCentro.add(lblNewLabel_2, gbc_lblNewLabel_2);

		// mensajes enviados en el último mes
		int mensajes = Controlador.INSTANCE.getUsuarioActual().getMensajesEnviados();
		JLabel lblNewLabel_5 = new JLabel(String.valueOf(mensajes));
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 2;
		panelCentro.add(lblNewLabel_5, gbc_lblNewLabel_5);
	}

}
