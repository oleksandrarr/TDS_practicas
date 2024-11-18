package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class VentanaBuscar {

	private JFrame frame;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Buscar");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		crearPanelBuscar();
		crearPanelScroll();
	}

	private void crearPanelBuscar() {
	    JPanel panelBuscar = new JPanel();
	    frame.getContentPane().add(panelBuscar, BorderLayout.NORTH);
	    panelBuscar.setLayout(new BorderLayout(0, 0));
	    
	    // Panel con la imagen de lupa
	    JPanel panelImagen = new JPanel();
	    panelBuscar.add(panelImagen, BorderLayout.NORTH);
	    
	    try {
	        String path1 = "https://e7.pngegg.com/pngimages/464/384/png-clipart-computer-icons-magnifying-glass-magnifier-symbol-magnifying-glass-text-magnifier.png";
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
       
        
        //Panel con los campos de texto
        JPanel panelCampos = new JPanel();
        panelCampos.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBuscar.add(panelCampos, BorderLayout.SOUTH);
        panelCampos.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Buscar"));
        
        GridBagLayout gbl_panelCampos = new GridBagLayout();
        gbl_panelCampos.columnWidths = new int[]{60, 100, 60, 100}; 
        gbl_panelCampos.rowHeights = new int[]{0, 0}; 
        gbl_panelCampos.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0}; 
        gbl_panelCampos.rowWeights = new double[]{0.0, 0.0, 0.0}; 
        panelCampos.setLayout(gbl_panelCampos);
        
        //texto
        JLabel lblNewLabel_texto = new JLabel("Texto:");
        GridBagConstraints gbc_lblNewLabel_texto = new GridBagConstraints();
        gbc_lblNewLabel_texto.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_texto.insets = new Insets(5, 5, 5, 5);
        gbc_lblNewLabel_texto.gridx = 0; 
        gbc_lblNewLabel_texto.gridy = 0; 
        panelCampos.add(lblNewLabel_texto, gbc_lblNewLabel_texto);
        
        JTextField textField_texto = new JTextField();
        GridBagConstraints gbc_textField_texto = new GridBagConstraints();
        gbc_textField_texto.insets = new Insets(5, 5, 5, 5);
        gbc_textField_texto.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_texto.gridx = 1; 
        gbc_textField_texto.gridy = 0; 
        gbc_textField_texto.gridwidth = 4; 
        panelCampos.add(textField_texto, gbc_textField_texto);
        textField_texto.setColumns(10);
        
        //telefono
        JLabel lblNewLabel_telefono = new JLabel("Teléfono:");
        GridBagConstraints gbc_lblNewLabel_telefono = new GridBagConstraints();
        gbc_lblNewLabel_telefono.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_telefono.insets = new Insets(5, 5, 5, 5);
        gbc_lblNewLabel_telefono.gridx = 0; 
        gbc_lblNewLabel_telefono.gridy = 1;
        panelCampos.add(lblNewLabel_telefono, gbc_lblNewLabel_telefono);
        
        JTextField txtTelefono = new JTextField();
        GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
        gbc_txtTelefono.insets = new Insets(5, 5, 5, 5);
        gbc_txtTelefono.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtTelefono.gridx = 1; 
        gbc_txtTelefono.gridy = 1; 
        panelCampos.add(txtTelefono, gbc_txtTelefono);
        txtTelefono.setColumns(10);
        
        // Usuario
        JLabel lblNewLabel_usuario = new JLabel("Usuario:");
        GridBagConstraints gbc_lblNewLabel_usuario = new GridBagConstraints();
        gbc_lblNewLabel_usuario.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_usuario.insets = new Insets(5, 5, 5, 5);
        gbc_lblNewLabel_usuario.gridx = 2; 
        gbc_lblNewLabel_usuario.gridy = 1; 
        panelCampos.add(lblNewLabel_usuario, gbc_lblNewLabel_usuario);

        JTextField textField_usuario = new JTextField();
        GridBagConstraints gbc_textField_usuario = new GridBagConstraints();
        gbc_textField_usuario.insets = new Insets(5, 5, 5, 5);
        gbc_textField_usuario.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_usuario.gridx = 3; 
        gbc_textField_usuario.gridy = 1; 
        panelCampos.add(textField_usuario, gbc_textField_usuario);
        textField_usuario.setColumns(10);
        
        //boton
        JButton btnNewButton_Aceptar = new JButton("Aceptar");
        GridBagConstraints gbc_btnNewButton_Aceptar = new GridBagConstraints();
        gbc_btnNewButton_Aceptar.insets = new Insets(5, 5, 5, 5);
        gbc_btnNewButton_Aceptar.gridx = 4; 
        gbc_btnNewButton_Aceptar.gridy = 1; 
        gbc_btnNewButton_Aceptar.anchor = GridBagConstraints.WEST;
        panelCampos.add(btnNewButton_Aceptar, gbc_btnNewButton_Aceptar);
        
	}
	
	private void crearPanelScroll() {
		JPanel panelMensajes = new JPanel();
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
        
        JScrollPane scrollPaneBuscar = new JScrollPane(panelMensajes);
        scrollPaneBuscar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollPaneBuscar, BorderLayout.CENTER);
		
        
        //Bucle con mensajes
        for (int i=0; i<3; i++) {
        	panelMensajes.add(añadirPanelMensaje("Receptor", "Emisor "));
        }
	}
	
	private JPanel añadirPanelMensaje(String receptor, String emisor) {
		JPanel panelMensaje = new JPanel();
		panelMensaje.setBorder(new LineBorder(Color.BLACK, 1));
	    panelMensaje.setLayout(new GridBagLayout());

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(5, 5, 5, 5);
	    
	    //Receptor
	    JLabel lblReceptor = new JLabel(receptor);
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.NORTHWEST;
	    gbc.weightx = 1.0;
	    panelMensaje.add(lblReceptor, gbc);
	    
	    //Emisor
	    JLabel lblEmisor = new JLabel(emisor);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.NORTHEAST;
	    gbc.weightx = 1.0;
	    panelMensaje.add(lblEmisor, gbc);
	    
	    //Mensaje
	    JTextField txtMensaje = new JTextField(20); 
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.weightx = 1.0;
	    gbc.weighty = 0.0;
	    panelMensaje.add(txtMensaje, gbc);
	   
	    return panelMensaje;
	}
}
