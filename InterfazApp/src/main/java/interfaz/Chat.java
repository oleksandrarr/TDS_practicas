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
import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.EtchedBorder;
import javax.imageio.ImageIO;
import javax.swing.*;

import tds.BubbleText;
import java.awt.FlowLayout;

public class Chat extends JPanel {

    
    	private static final long serialVersionUID = 1L;

        public Chat() throws IOException {
        	BubbleText.noZoom();
            
            this.setLayout(new BorderLayout());
            this.setMaximumSize(new Dimension(500, 700));
    		this.setMinimumSize(new Dimension(500, 700));
    		this.setPreferredSize(new Dimension(500, 700));
            
    		JPanel chat=new JPanel();
    		chat.setBackground(new Color(122, 184, 135));
    		chat.setLayout(new BoxLayout(chat,BoxLayout.Y_AXIS));
    		chat.setSize(320, 640);
    		//chat.setMinimumSize(new Dimension(300, 400));
    		//chat.setMaximumSize(new Dimension(700, 700));
   

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
    		fixSize(scroll,280,400);
    		this.add(scroll,BorderLayout.CENTER);
            // Añadir componentes al panel principal
            //this.add(chat,BorderLayout.CENTER);
            this.add(bajoChat, BorderLayout.SOUTH);
            setVisible(true);
            
            DateTimeFormatter soloHora = DateTimeFormatter.ofPattern("HH:mm");
			System.out.println(LocalDateTime.now().format(soloHora));
            
			LocalDateTime hora = LocalDateTime.now();
            BubbleText bubbleText = new BubbleText(chat, "Hola grupo", Color.WHITE, "Pascual "+hora.format(soloHora), BubbleText.SENT, 11);
            chat.add(bubbleText);
            chat.add(new BubbleText(chat, "Hola grupo", Color.WHITE, "Pablo "+hora.format(soloHora), 1, 11));
            chat.add(new BubbleText(chat, "Hola grupo", Color.WHITE, "Pablo "+hora.format(soloHora), BubbleText.RECEIVED, 11));
            chat.add(new BubbleText(chat, 0, Color.WHITE, "Pablo "+hora.format(soloHora), BubbleText.RECEIVED, 11));
            chat.add(new BubbleText(chat, 3, Color.WHITE, "Pablo "+hora.format(soloHora), BubbleText.RECEIVED, 11));
            chat.add(new BubbleText(chat, 1, Color.WHITE, "Pablo "+hora.format(soloHora), BubbleText.RECEIVED, 11));
            chat.add(new BubbleText(chat, "Hola grupo", Color.WHITE, "Pablo "+hora.format(soloHora), BubbleText.RECEIVED, 11));
            chat.add(new BubbleText(chat, "Hola grupo", Color.WHITE, "Pablo"+hora.format(soloHora), BubbleText.RECEIVED, 11));
            BubbleText mensaje2 = new BubbleText(chat, "Hola grupo", Color.WHITE, "Pascual "+hora.format(soloHora), BubbleText.SENT, 11);
            chat.add(bubbleText);
            chat.scrollRectToVisible(new Rectangle(0,640+mensaje2.getHeight(),1,1));
            repaint();
            revalidate();
            //Para tabla de emoticonos
            /*
            JPanel cajaEmoji = new JPanel();
            cajaEmoji.setBackground(Color.WHITE);
            cajaEmoji.setBorder(new LineBorder(Color.BLACK));
            fixSize(cajaEmoji,240,120);
            cajaEmoji.setLayout(new GridLyout(3,6));
            //Cada boton se añade para formar un cuadricula.
            JButton emoticono=new JButton();
            fixSize(emoticono(40,40));
            emoticono.setIcon(BubbleText.getEmoji(7));
            cajaEmoji.add(Box.createRigidArea(new Dimension(40,40)));
            cajaIzq.add(cajaEmoji);
            
            
            
            
            */
        }
        
        private void fixSize(JComponent c, int x, int y) {
    		c.setMinimumSize(new Dimension(x,y));
    		c.setMaximumSize(new Dimension(x,y));
    		c.setPreferredSize(new Dimension(x,y));
    	}
 }
   