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
import java.net.URL;
import java.util.List;
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

import controlador.Controlador;
import dominio.Contacto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.border.EtchedBorder;
import javax.imageio.ImageIO;

public class PremiumCon {

    private JFrame frame;
    private JPanel panelNombre;
    private JPanel panelBotones;
    private JPanel panelCentro;
    
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
        frame.setBounds(75, 75, 350, 120);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Premium");
        frame.setBackground(Utilidades.VERDE_FONDO);
        
        añadirPanelNombre();
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
        JLabel lblNewLabel = new JLabel("Premium");
        lblNewLabel.setFont(new Font("Serif", Font.BOLD, 20));
        box.add(lblNewLabel); 
        box.add(Box.createHorizontalGlue()); 

        panelNombre.add(box);        
        panelNombre.add(Box.createVerticalStrut(10));
    }
    
    private void añadirPanelBotones() {
    	panelBotones = new JPanel();
        panelBotones.setBackground(Utilidades.VERDE_FONDO);
        frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);
        
        
        JButton btnNewButton_1 = new JButton("Cancelar");
        Utilidades.crearBoton(btnNewButton_1, 100, 30, 12);
        panelBotones.add(btnNewButton_1);
        
		 btnNewButton_1.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 frame.dispose();  // Cierra la ventana
	         }
	     });
        
        JButton btnNewButton = new JButton("Generar PDF");
        Utilidades.crearBoton(btnNewButton, 120, 30, 12);
        panelBotones.add(btnNewButton);
        
        btnNewButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 
	         }
	     });
        
    }
    
}
