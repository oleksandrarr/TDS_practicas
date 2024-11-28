package interfaz;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.border.EtchedBorder;
import javax.imageio.ImageIO;

public class PremiumCon {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	PremiumCon window = new PremiumCon();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * @throws IOException 
     */
    public PremiumCon() throws IOException {
        initialize();
    }

    public void mostrarVentana() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
    
    /**
     * Initialize the contents of the frame.
     * @throws IOException 
     */
    private void initialize() throws IOException {
        frame = new JFrame();
        frame.setBounds(75, 75, 400, 165);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Premium");
        frame.setBackground(new Color(40, 167, 69));
        
        JPanel panelNombre = new JPanel();
        frame.getContentPane().add(panelNombre, BorderLayout.NORTH);
        panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.Y_AXIS));
        panelNombre.setBackground(new Color(40, 167, 69));
        
        panelNombre.add(Box.createVerticalStrut(10));
        
        Box box = Box.createHorizontalBox(); 
        box.add(Box.createHorizontalGlue()); 
        JLabel lblNewLabel = new JLabel("Premium");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        box.add(lblNewLabel); 
        box.add(Box.createHorizontalGlue()); 

        panelNombre.add(box);
        
        panelNombre.add(Box.createVerticalStrut(10));
        
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(40, 167, 69));
        frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("Generar PDF");
        btnNewButton.setBackground(new Color(0, 128, 0));
        btnNewButton.setForeground(Color.BLACK); // Texto blanco para contraste
        btnNewButton.setOpaque(true);
        btnNewButton.setBorderPainted(false);
        panelBotones.add(btnNewButton);
        
        
        JButton btnNewButton_1 = new JButton("Cancelar");
        btnNewButton_1.setBackground(new Color(0, 128, 0));
        btnNewButton_1.setForeground(Color.BLACK); // Texto blanco para contraste
        btnNewButton_1.setOpaque(true);
        btnNewButton_1.setBorderPainted(false);
        panelBotones.add(btnNewButton_1);
        
        JPanel panelI = new JPanel();
        panelI.setBackground(new Color(40, 167, 69));
        frame.getContentPane().add(panelI, BorderLayout.WEST);
        
        JPanel panelD = new JPanel();
        panelD.setBackground(new Color(40, 167, 69));
        frame.getContentPane().add(panelD, BorderLayout.EAST);
        
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new Color(111, 204, 115));
        frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
		
        JComboBox comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(200, 30));
		comboBox.setMinimumSize(new Dimension(200, 30));
		comboBox.setMaximumSize(new Dimension(200, 30));
		comboBox.setBackground(new Color(111, 204, 115)); // Verde medio
		comboBox.setForeground(Color.BLACK); // Texto blanco para contraste
		comboBox.setOpaque(true);
		panelCentro.add(comboBox);
		
		
		String path = "https://cdn-icons-png.flaticon.com/512/106/106733.png";
		URL url = new URL(path);
        BufferedImage image = ImageIO.read(url);
		
        JButton botonPanel = new JButton(new ImageIcon(image.getScaledInstance(40, 25, Image.SCALE_SMOOTH)));
		botonPanel.setPreferredSize(new Dimension(40, 40));
		botonPanel.setMinimumSize(new Dimension(40, 40));
		botonPanel.setMaximumSize(new Dimension(40, 40));
		botonPanel.setBackground(new Color(0, 128, 0)); // Verde medio
		botonPanel.setForeground(Color.WHITE); // Texto blanco para contraste
		botonPanel.setOpaque(true);
		botonPanel.setBorderPainted(false);
		panelCentro.add(botonPanel);
        
        
        
        
		 btnNewButton_1.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 frame.dispose();  // Cierra la ventana
	         }
	     });
       
    }
}
