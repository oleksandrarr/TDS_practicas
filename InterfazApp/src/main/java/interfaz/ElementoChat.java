package interfaz;

import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controlador.Controlador;
import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Grupo;

public class ElementoChat extends JPanel {
	private String nombre;
	private String telefono;
	private String ultimoMensaje;
	private JButton botonNombre;
	private JPanel panelFoto;
	private int idContacto;

	public ElementoChat(Contacto contacto, VentanaPrincipal ventanaPrincipal) {

		if (contacto instanceof ContactoIndividual) {
			this.telefono = ((ContactoIndividual) contacto).getNumeroTelefono();
			this.nombre = ((ContactoIndividual) contacto).getNombreOptional().orElse(this.telefono);
		} else {
			this.nombre = ((Grupo) contacto).getNombre();
		}

		this.idContacto = contacto.getId();
		if (Controlador.INSTANCE.obtenerMensajes(contacto.getId()) != null
				&& !Controlador.INSTANCE.obtenerMensajes(contacto.getId()).isEmpty()) { // La lista no es null y no está vacía
			this.ultimoMensaje = Controlador.INSTANCE.obtenerMensajes(contacto.getId()).getLast().getTexto();//Controlador.INSTANCE.obtenerMensajes(contacto).getLast().getTexto(); 
		}
		// Configurar el layout del panel principal
		this.setLayout(new BorderLayout(10, 10));
		this.setBackground(Utilidades.VERDE_FONDO); // Fondo verde

		this.setPreferredSize(new Dimension(300, 100)); // Ancho: 300, Alto: 100
		this.setMinimumSize(new Dimension(300, 100));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Alto fijo, ancho dinámico

		// Crear el panel para la foto
		panelFoto = new JPanel();
		panelFoto.setBackground(Utilidades.VERDE_FONDO);
		panelFoto.setPreferredSize(new Dimension(60, 60));

		// Cargar la imagen desde la URL
		try {
			URL url = contacto.getImagen();
			Image img = ImageIO.read(url).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			ImagenCircular imagenCircular = new ImagenCircular(img);
			imagenCircular.setPreferredSize(new Dimension(60, 60));
			panelFoto.add(imagenCircular);
		} catch (Exception e) {
			JLabel labelFoto = new JLabel("No Foto");
			panelFoto.add(labelFoto);
		}

		// Crear el panel para el texto
		JPanel panelTexto = new JPanel(new GridLayout(2, 1));
		panelTexto.setBackground(Utilidades.VERDE_LABELS);
		panelTexto.setOpaque(false);

		JLabel labelNombre = new JLabel(nombre);
		labelNombre.setBackground(Utilidades.VERDE_FONDO);
		labelNombre.setFont(new Font("Arial", Font.BOLD, 16));
		labelNombre.setForeground(Color.BLACK);
		labelNombre.setBorder(new LineBorder(Color.BLACK, 1)); // Borde azul de 1 píxel
		JLabel labelUltimoMensaje = new JLabel(ultimoMensaje);
		labelUltimoMensaje.setFont(new Font("Arial", Font.PLAIN, 16));
		labelUltimoMensaje.setForeground(Color.BLACK);
		labelUltimoMensaje.setBorder(new LineBorder(Color.BLACK, 1)); // Borde azul de 1 píxel

		panelTexto.add(labelNombre);
		panelTexto.add(labelUltimoMensaje);

		// Crear el botón "+"
		if (contacto instanceof ContactoIndividual) {	//contacto no agregado

			if (((ContactoIndividual) contacto).getNombreOptional().isPresent() && ((ContactoIndividual) contacto)
					.getNombreOptional().get().equals(((ContactoIndividual) contacto).getNumeroTelefono())) {
				botonNombre = new JButton("+");
				botonNombre.setPreferredSize(new Dimension(30, 30));
				botonNombre.setBackground(Color.WHITE);
				botonNombre.setBorder(new LineBorder(Color.BLACK, 1));
				botonNombre.setFocusPainted(false);

				// Agregar funcionalidad al botón
				botonNombre.addActionListener(e -> {
					new AgregarContactoSinNombre(telefono, this, ventanaPrincipal).setVisible(true);
				});

				add(botonNombre, BorderLayout.EAST); // Botón a la derecha
			}
		} else if (contacto instanceof Grupo) {	//grupo para añadir más miembros
			botonNombre = new JButton("+");
			botonNombre.setPreferredSize(new Dimension(30, 30));
			botonNombre.setBackground(Color.WHITE);
			botonNombre.setBorder(new LineBorder(Color.BLACK, 1));
			botonNombre.setFocusPainted(false);

			// Agregar funcionalidad al botón
			botonNombre.addActionListener(e -> {
				new AgregarContactoGrupo(contacto.getId(), this, ventanaPrincipal).setVisible(true);
			});

			add(botonNombre, BorderLayout.EAST); // Botón a la derecha
		}
		// Agregar componentes al panel principal
		add(panelFoto, BorderLayout.WEST); // Foto a la izquierda
		add(panelTexto, BorderLayout.CENTER); // Texto en el centro

	}

	public int getIdContacto() {
		return idContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public JButton getBotonNombre() {
		return botonNombre;
	}

	class ImagenCircular extends JLabel {
		private Image image;

		public ImagenCircular(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (image != null) {
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Crear un área circular
				int diameter = Math.min(getWidth(), getHeight());
				Shape clip = new java.awt.geom.Ellipse2D.Double(0, 0, diameter, diameter);
				g2d.setClip(clip);

				// Dibujar la imagen
				g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);

				g2d.dispose();
			}
		}
	}

	public void actualizarContacto(String nuevoNombre, String nuevoTelefono) {
		this.nombre = nuevoNombre;
		this.telefono = nuevoTelefono;

		// Actualiza los componentes de la GUI relacionados
		for (Component component : this.getComponents()) {
			if (component instanceof JPanel) {
				JPanel panel = (JPanel) component;
				panel.setBackground(Utilidades.VERDE_FONDO);
				for (Component inner : panel.getComponents()) {
					if (inner instanceof JLabel) {
						JLabel label = (JLabel) inner;
						if (label.getText().equals(nombre)) {
							label.setText(nuevoNombre);
						}
					}
				}
			}
		}
		// Re-renderiza el panel
		revalidate();
		repaint();
	}
}
