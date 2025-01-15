package interfaz;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Controlador;
import dao.DAOException;
import dominio.Contacto;
import dominio.Mensaje;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.*;

import tds.BubbleText;
import java.awt.FlowLayout;

public class Chat extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel cajaEmoji;
	private VentanaPrincipal ventanaPrincipal;

	public Chat(Contacto contacto, VentanaPrincipal ventanaPrincipal) throws IOException {
		Controlador controlador = Controlador.INSTANCE;
		this.ventanaPrincipal = ventanaPrincipal;
		BubbleText.noZoom();

		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(500, 700));
		this.setMinimumSize(new Dimension(500, 700));
		this.setPreferredSize(new Dimension(500, 700));

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Utilidades.VERDE_LABELS); // Verde oscuro
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centrado horizontalmente

		JLabel labelNombreContacto = new JLabel(contacto.getNombre());
		labelNombreContacto.setFont(new Font("Arial", Font.BOLD, 20)); // Fuente personalizada
		labelNombreContacto.setForeground(Color.BLACK); // Texto blanco
		panelSuperior.add(labelNombreContacto); // Añadir el JLabel al panel

		this.add(panelSuperior, BorderLayout.NORTH); // Añadir el panel superior al Chat

		JPanel chat = new JPanel();
		chat.setBackground(new Color(122, 184, 135));
		chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
		chat.setSize(320, 640);
		// chat.setMinimumSize(new Dimension(300, 400));
		// chat.setMaximumSize(new Dimension(700, 700));

		// Panel inferior (bajo el chat)
		JPanel bajoChat = new JPanel();
		bajoChat.setPreferredSize(new Dimension(600, 50));
		bajoChat.setBackground(Color.gray);
		bajoChat.setLayout(new GridLayout(1, 3));

		// Botón con ícono
		String path = "https://cdn-icons-png.flaticon.com/512/5602/5602476.png";
		URL url = new URL(path);
		BufferedImage image = ImageIO.read(url);
		JButton btnNewButton = new JButton(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton.setBackground(new Color(185, 255, 128));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(true);
		bajoChat.add(btnNewButton);

		// Campo de texto
		JTextField textField_1 = new JTextField();
		textField_1.setBackground(new Color(199, 235, 201));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		bajoChat.add(textField_1);

		// Botón de enviar
		JButton btnNewButton_1 = new JButton("Sent");
		btnNewButton_1.setBackground(new Color(185, 255, 128));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setOpaque(true);
		btnNewButton_1.setBorderPainted(true);
		bajoChat.add(btnNewButton_1);

		// ScrollPane
		JScrollPane scroll = new JScrollPane(chat);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setSize(500, 640);
		fixSize(scroll, 280, 400);
		this.add(scroll, BorderLayout.CENTER);
		// Añadir componentes al panel principal
		// this.add(chat,BorderLayout.CENTER);
		this.add(bajoChat, BorderLayout.SOUTH);
		setVisible(true);

		DateTimeFormatter soloHora = DateTimeFormatter.ofPattern("HH:mm");
		System.out.println(LocalDateTime.now().format(soloHora));

		LocalDateTime hora = LocalDateTime.now();

		List<Mensaje> mensajes = Controlador.INSTANCE.obtenerMensajes(contacto);
		if (mensajes != null) {
			for (Mensaje m : mensajes) {
				BubbleText burbuja;
				
				if (m.getTexto() != null) {
					if (m.getTipoMensaje() == 0) {
						burbuja = new BubbleText(chat,m.getTexto(), Color.WHITE,
								Controlador.INSTANCE.getUsuarioPorId(m.getEmisor()).getNombre()
										+ m.getFechaHoraEnvio().format(soloHora),
								m.getTipoMensaje(), 11);
					} else {
						burbuja = new BubbleText(chat, m.getTexto(), Color.WHITE,
								Controlador.INSTANCE.getContactoPorId(m.getEmisor()).getNombre()
										+ m.getFechaHoraEnvio().format(soloHora),
								m.getTipoMensaje(), 11);
					}
				}
				else {
					if (m.getTipoMensaje() == 0) {
						burbuja = new BubbleText(chat,m.getEmoticono(), Color.WHITE,
								Controlador.INSTANCE.getUsuarioPorId(m.getEmisor()).getNombre()
										+ m.getFechaHoraEnvio().format(soloHora),
								m.getTipoMensaje(), 11);
					} else {
						burbuja = new BubbleText(chat, m.getEmoticono(), Color.WHITE,
								Controlador.INSTANCE.getContactoPorId(m.getEmisor()).getNombre()
										+ m.getFechaHoraEnvio().format(soloHora),
								m.getTipoMensaje(), 11);
					}
				}
				chat.add(burbuja);
				chat.scrollRectToVisible(new Rectangle(0, 640 + burbuja.getHeight(), 1, 1));
				
			}
		}

		repaint();
		revalidate();

		JPopupMenu emojiPopup = new JPopupMenu();
		JPanel cajaEmoji = new JPanel();
		cajaEmoji.setBackground(new Color(40, 167, 69));
		cajaEmoji.setBorder(new LineBorder(Color.BLACK));
		cajaEmoji.setLayout(new GridLayout(3, 6));

		for (int i = 0; i < 18; i++) {
			JButton emoticono = new JButton();
			fixSize(emoticono, 40, 40); // Tamaño de los botones
			emoticono.setIcon(BubbleText.getEmoji(i)); // Obtiene el emoticón según el índice
			final int indice = i;
			emoticono.addActionListener(e -> {
				try {
					Controlador.INSTANCE.enviarMensajeEmoticono(contacto, indice, 0);
					
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				emojiPopup.setVisible(false);

				LocalDateTime hora1 = LocalDateTime.now();

				BubbleText burbuja = new BubbleText(chat, indice, Color.WHITE,
						"Tú " + hora.format(soloHora), Mensaje.ENVIADO, 11);
				chat.add(burbuja); // Añadir la burbuja al panel de chat

				// Desplazar la vista hacia el final
				chat.scrollRectToVisible(new Rectangle(0, chat.getHeight(), 1, 1));
				// Refrescar la interfaz gráfica
				chat.revalidate();
				chat.repaint();
			});
			cajaEmoji.add(emoticono);
			
		}

		emojiPopup.add(cajaEmoji);
		// bajoChat.add(cajaEmoji, BorderLayout.SOUTH);

		btnNewButton.addActionListener(e -> {
			// Mostrar el JPopupMenu cerca del botón
			emojiPopup.show(btnNewButton, btnNewButton.getWidth() / 2, btnNewButton.getHeight());
		});

		// Action listener para enviar Mensaje
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mensaje = textField_1.getText(); // Obtener texto del campo
				if (!mensaje.trim().isEmpty()) { // Verificar que no esté vacío

					try {
						Controlador.INSTANCE.enviarMensaje(contacto, mensaje, Mensaje.ENVIADO);
						ventanaPrincipal.actualizarListaContactos();
					} catch (DAOException e1) {
						e1.printStackTrace();
					} // Llamar al método con el texto
					ventanaPrincipal.actualizarListaContactos();
					textField_1.setText(""); // Limpiar el campo después de enviar

					// Crear una nueva burbuja para mostrar el mensaje en el área de chat

					LocalDateTime hora = LocalDateTime.now();

					BubbleText burbuja = new BubbleText(chat, mensaje.toString(), Color.WHITE,
							"Tú " + hora.format(soloHora), Mensaje.ENVIADO, 11);
					chat.add(burbuja); // Añadir la burbuja al panel de chat

					// Desplazar la vista hacia el final
					chat.scrollRectToVisible(new Rectangle(0, chat.getHeight(), 1, 1));
					// Refrescar la interfaz gráfica
					chat.revalidate();
					chat.repaint();

				}
			}

		});

	}

	private void fixSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}
}
