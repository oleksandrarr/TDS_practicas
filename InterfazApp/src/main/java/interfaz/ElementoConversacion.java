package interfaz;
//Clase que represeta al chat que se muestra en cada momento en la ventanaPrincipal.
//Esta clase será la encargada de mostrar los mensajes del contacto que se cargue en cada momento
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import dominio.Contacto;
import dominio.Mensaje;
import dominio.Usuario;
import tds.BubbleText;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;

public class ElementoConversacion extends JPanel{
	

	
	public ElementoConversacion() throws IOException {
		
		this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(500, 700));
 		this.setMinimumSize(new Dimension(500, 700));
 		this.setPreferredSize(new Dimension(500, 700));
         
 		JPanel chat=new JPanel();
 		chat.setBackground(new Color(222, 184, 135));
 		chat.setLayout(new BoxLayout(chat,BoxLayout.Y_AXIS));
 		chat.setSize(320, 640);
 		chat.setMinimumSize(new Dimension(300, 400));
 		chat.setMaximumSize(new Dimension(700, 700));
 		

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
         bajoChat.add(btnNewButton);

         // Campo de texto
         JTextField textField_1 = new JTextField();
         textField_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
         bajoChat.add(textField_1);

         // Botón de enviar
         JButton btnNewButton_1 = new JButton("Sent");
         bajoChat.add(btnNewButton_1);

         // ScrollPane
         JScrollPane scroll=new JScrollPane(chat);
 		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
 		scroll.setSize(500,640);
 		this.add(scroll,BorderLayout.CENTER);
         // Añadir componentes al panel principal
         //this.add(chat,BorderLayout.CENTER);
         this.add(bajoChat, BorderLayout.SOUTH);
	}
}
