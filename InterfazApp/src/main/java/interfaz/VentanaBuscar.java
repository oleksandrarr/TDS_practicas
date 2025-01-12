package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import beans.Entidad;
import beans.Mensaje;
import controlador.Controlador;
import dominio.Contacto;

public class VentanaBuscar {

	private JFrame frame;
	private String mensaje;
	private String telefono;
	private String usuario;
	private JPanel panelMensajes;
	private JTextField txtTelefono;
	private JTextField textField_texto;
	private JTextField textField_usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBuscar window = new VentanaBuscar();
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
	public VentanaBuscar() {
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
		frame.setTitle("Buscar");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		crearPanelBuscar();
		crearPanelScroll();
	}

	private void crearPanelBuscar() {
		JPanel panelBuscar = new JPanel();
		frame.getContentPane().add(panelBuscar, BorderLayout.NORTH);
		panelBuscar.setLayout(new BorderLayout(0, 0));

		// Panel con la imagen de lupa
		JPanel panelImagen = new JPanel();
		panelImagen.setBackground(Utilidades.VERDE_FONDO);
		panelBuscar.add(panelImagen, BorderLayout.NORTH);

		try {
			String path1 = "https://png.pngtree.com/png-clipart/20230401/original/pngtree-magnifying-glass-line-icon-png-image_9015864.png";
			URL url1 = new URL(path1);
			BufferedImage image1 = ImageIO.read(url1);
			int width = 40;
			int height = 40;
			Image resizedImage = image1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(resizedImage);
			JLabel labelImagen = new JLabel(icon);
			panelImagen.add(labelImagen);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Panel con los campos de texto
		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(Utilidades.VERDE_FONDO);
		panelCampos.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelBuscar.add(panelCampos, BorderLayout.SOUTH);
		TitledBorder border = new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Buscar");
		border.setTitleFont(new Font("Arial", Font.ITALIC, 12));
		panelCampos.setBorder(border);

		GridBagLayout gbl_panelCampos = new GridBagLayout();
		gbl_panelCampos.columnWidths = new int[] { 60, 100, 60, 100 };
		gbl_panelCampos.rowHeights = new int[] { 0, 0 };
		gbl_panelCampos.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
		gbl_panelCampos.rowWeights = new double[] { 0.0, 0.0 };
		panelCampos.setLayout(gbl_panelCampos);

		// texto
		JLabel lblNewLabel_texto = new JLabel("Texto:");
		lblNewLabel_texto.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_texto = new GridBagConstraints();
		gbc_lblNewLabel_texto.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_texto.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_texto.gridx = 0;
		gbc_lblNewLabel_texto.gridy = 0;
		panelCampos.add(lblNewLabel_texto, gbc_lblNewLabel_texto);

		textField_texto = new JTextField();
		textField_texto.setBackground(new Color(199, 235, 201));
		GridBagConstraints gbc_textField_texto = new GridBagConstraints();
		gbc_textField_texto.insets = new Insets(5, 5, 5, 5);
		gbc_textField_texto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_texto.gridx = 1;
		gbc_textField_texto.gridy = 0;
		gbc_textField_texto.gridwidth = 4;
		panelCampos.add(textField_texto, gbc_textField_texto);
		textField_texto.setColumns(10);

		// telefono
		JLabel lblNewLabel_telefono = new JLabel("Teléfono:");
		lblNewLabel_telefono.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_telefono = new GridBagConstraints();
		gbc_lblNewLabel_telefono.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_telefono.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_telefono.gridx = 0;
		gbc_lblNewLabel_telefono.gridy = 1;
		panelCampos.add(lblNewLabel_telefono, gbc_lblNewLabel_telefono);

		txtTelefono = new JTextField();
		txtTelefono.setBackground(new Color(199, 235, 201));
		GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
		gbc_txtTelefono.insets = new Insets(5, 5, 5, 5);
		gbc_txtTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefono.gridx = 1;
		gbc_txtTelefono.gridy = 1;
		panelCampos.add(txtTelefono, gbc_txtTelefono);
		txtTelefono.setColumns(10);

		// Usuario
		JLabel lblNewLabel_usuario = new JLabel("Contacto:");
		lblNewLabel_usuario.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_usuario = new GridBagConstraints();
		gbc_lblNewLabel_usuario.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_usuario.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_usuario.gridx = 2;
		gbc_lblNewLabel_usuario.gridy = 1;
		panelCampos.add(lblNewLabel_usuario, gbc_lblNewLabel_usuario);

		textField_usuario = new JTextField();
		textField_usuario.setBackground(new Color(199, 235, 201));
		GridBagConstraints gbc_textField_usuario = new GridBagConstraints();
		gbc_textField_usuario.insets = new Insets(5, 5, 5, 5);
		gbc_textField_usuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_usuario.gridx = 3;
		gbc_textField_usuario.gridy = 1;
		panelCampos.add(textField_usuario, gbc_textField_usuario);
		textField_usuario.setColumns(10);

		// boton
		JButton btnNewButton_Aceptar = new JButton("Buscar");
		Utilidades.crearBoton(btnNewButton_Aceptar, 80, 20, 12);
		GridBagConstraints gbc_btnNewButton_Aceptar = new GridBagConstraints();
		gbc_btnNewButton_Aceptar.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_Aceptar.gridx = 4;
		gbc_btnNewButton_Aceptar.gridy = 1;
		gbc_btnNewButton_Aceptar.anchor = GridBagConstraints.WEST;
		panelCampos.add(btnNewButton_Aceptar, gbc_btnNewButton_Aceptar);

		btnNewButton_Aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mensaje = textField_texto.getText();
				String telefono = txtTelefono.getText();
				String usuario = textField_usuario.getText();

				String emisor = null;
				String receptor = null;

				Contacto contacto = null;
				List<Contacto> lista = Controlador.INSTANCE.getUsuarioActual().getContactos();

				List<dominio.Mensaje> listaMensajes = Controlador.INSTANCE.buscarMensaje(mensaje, telefono, usuario);
				System.out.println("/////////////7277e3784722ii" + listaMensajes.size());
				panelMensajes.removeAll();
				for (dominio.Mensaje m : listaMensajes) {
					System.out.println("/////////////7277e3784722iittttt" + m.getReceptor());
					System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeMI:" + m.getEmisor() + "//"
							+ Controlador.INSTANCE.getUsuarioActual().getId() + "//" + m.getReceptor() + m.getTexto());
					mensaje = m.getTexto();
					if (m.getTipoMensaje() == dominio.Mensaje.ENVIADO) {
						emisor = Controlador.INSTANCE.getUsuarioPorId(m.getEmisor()).getNombre();
						receptor = Controlador.INSTANCE.getContactoPorId(m.getReceptor()).getNombre();
					} else {
						emisor = Controlador.INSTANCE.getContactoPorId(m.getEmisor()).getNombre();
						receptor = Controlador.INSTANCE.getUsuarioPorId(m.getReceptor()).getNombre();
					}
					panelMensajes.add(añadirPanelMensaje(receptor, emisor, mensaje));
					System.out.println("Buscando mensajes con:");
					System.out.println("Texto: " + mensaje);
					System.out.println("Teléfono: " + telefono);
					System.out.println("Usuario: " + usuario);

				}

				panelMensajes.revalidate();
				panelMensajes.repaint();

			}
		});

	}

	private void crearPanelScroll() {
		panelMensajes = new JPanel();
		panelMensajes.setBackground(Utilidades.VERDE_CLARO);
		panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));

		JScrollPane scrollPaneBuscar = new JScrollPane(panelMensajes);
		scrollPaneBuscar.setBackground(Utilidades.VERDE_CLARO);
		scrollPaneBuscar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPaneBuscar, BorderLayout.CENTER);

	}

	private JPanel añadirPanelMensaje(String receptor, String emisor, String mensaje) {
		JPanel panelMensaje = new JPanel();
		panelMensaje.setBackground(Utilidades.VERDE_CLARO);
		panelMensaje.setBorder(new LineBorder(Color.BLACK, 1));
		panelMensaje.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// Emisor
		JLabel lblEmisor = new JLabel("Emisor: " + emisor);
		gbc.gridx = 0; // Columna 0
		gbc.gridy = 0; // Fila 0
		gbc.anchor = GridBagConstraints.NORTHWEST; // Anclar arriba a la izquierda
		gbc.weightx = 1.0; // Expansión horizontal
		gbc.weighty = 0.0; // No expansión vertical
		gbc.insets = new Insets(10, 10, 0, 0); // Márgenes
		panelMensaje.add(lblEmisor, gbc);

		// Receptor
		JLabel lblReceptor = new JLabel("Receptor: " + receptor);
		gbc.gridx = 1; // Columna 1
		gbc.gridy = 0; // Fila 0
		gbc.anchor = GridBagConstraints.NORTHEAST; // Anclar arriba a la derecha
		gbc.weightx = 1.0; // Expansión horizontal
		gbc.insets = new Insets(10, 0, 0, 10); // Márgenes
		panelMensaje.add(lblReceptor, gbc);

		// Mensaje
		JLabel lblMensaje = new JLabel("Mensaje: " + mensaje);
		lblMensaje.setOpaque(true);
		lblMensaje.setBackground(new Color(199, 235, 201));
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
		gbc.gridx = 0; // Columna 0
		gbc.gridy = 1; // Fila 1
		gbc.gridwidth = 2; // Abarcar dos columnas
		gbc.anchor = GridBagConstraints.CENTER; // Centrar en el espacio disponible
		gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
		gbc.weightx = 1.0; // Expansión horizontal
		gbc.weighty = 1.0; // Expansión vertical
		gbc.insets = new Insets(10, 10, 10, 10); // Márgenes
		panelMensaje.add(lblMensaje, gbc);

		return panelMensaje;
	}
}
