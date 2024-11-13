package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;

public class ElementoChat extends JPanel{

	public ElementoChat(String fileName, String usuario,String mensaje) throws IOException {
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		fixSize(this,300,110);
		this.setBackground(Color.WHITE);
		this.setBorder(new TitledBorder(usuario));
		
		JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());
	    panel.setPreferredSize(new Dimension(400, 100));

	        
	    // Cargar la imagen desde una URL
	    String imageUrl = fileName;
	    URL url = new URL(imageUrl);
	    Image img = ImageIO.read(url).getScaledInstance(60, 60, Image.SCALE_SMOOTH);

	            // Crear un JLabel con la imagen
	    JLabel imageLabel = new JLabel(new ImageIcon(img));
	    panel.add(imageLabel, BorderLayout.WEST);
	        

	    //JPanel nombre usuario y último mensaje
	    JPanel panelDer = new JPanel();
	    panelDer.setLayout(new GridLayout(0, 1, 0, 0));
	    panelDer.setBorder(new EmptyBorder(15, 15, 15, 15));
	     
	     	       
	    JLabel contacto = new JLabel(usuario);
	    panelDer.add(contacto);
	    panel.add(panelDer, BorderLayout.CENTER);
	    contacto.setFont(new Font("Arial", Font.BOLD, 16));
	     
	    JLabel ultimoMensaje = new JLabel(mensaje);
	    ultimoMensaje.setBorder(new LineBorder(Color.BLACK,1));
	    ultimoMensaje.setFont(new Font("Arial", Font.BOLD, 16));
	    panelDer.add(ultimoMensaje);
	    
	  

		this.add(panel);
		
	}
	
	private void fixSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x,y));
		c.setMaximumSize(new Dimension(x,y));
		c.setPreferredSize(new Dimension(x,y));
	}
}
