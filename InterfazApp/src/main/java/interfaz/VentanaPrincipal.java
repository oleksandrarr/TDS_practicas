package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.border.EmptyBorder;

import interfaz.RegistroView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.FlowLayout;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private JFrame frmVentanaPrincipal;
	private RegistroView pantallaRegistroView;
	
	public VentanaPrincipal() throws IOException {
		initialize();
	}


	public void mostrarVentana() {
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void initialize() throws IOException {
		frmVentanaPrincipal = new JFrame();
		frmVentanaPrincipal.setTitle("AppChat- Ventana Inicio");
		frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaPrincipal.setPreferredSize(new Dimension(800,600));
		
		pantallaRegistroView = new RegistroView(this);
		
		JPanel contentPane = (JPanel) frmVentanaPrincipal.getContentPane();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		frmVentanaPrincipal.getContentPane().setLayout(new BoxLayout(frmVentanaPrincipal.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel labelApp = new JLabel("Bienvenidos a AppVideo");
		labelApp.setFont(new Font("Arial", Font.PLAIN, 30));
		labelApp.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelApp);
		
		URL urlIconoLogin = new URL("https://c0.klipartz.com/pngpicture/81/570/gratis-png-perfil-logo-iconos-de-computadora-usuario-usuario.png"); // Sustituye con la URL real del icono
        URL urlIconoRegistro = new URL("https://w7.pngwing.com/pngs/869/794/png-transparent-computer-icons-registered-user-login-user-profile-others-blue-logo-registered-user-thumbnail.png"); // Sustituye con la URL real del icono
        
        Image imgLogin = ImageIO.read(urlIconoLogin).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    ImageIcon imgenLogin = new ImageIcon(imgLogin);  
	   
	    Image imgReg = ImageIO.read(urlIconoRegistro).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    ImageIcon imagenReg = new ImageIcon(imgReg);  
	    
	    JButton botonLogin = new JButton("Login", imgenLogin);
	    botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
	    botonLogin
	    .setFont(new Font("Arial", Font.BOLD, 18));  
	    botonLogin.setPreferredSize(new Dimension(200,60));
	    botonLogin.setMaximumSize(new Dimension(200,60));
	    botonLogin.setMinimumSize(new Dimension(150,400));
	    
	    JButton botonRegistro = new JButton("Registrarse", imagenReg);
	    botonRegistro.setFont(new Font("Arial", Font.BOLD, 18));  
	    botonRegistro.setPreferredSize(new Dimension(200,60));
	    botonRegistro.setMaximumSize(new Dimension(200,60));
	    botonRegistro.setMinimumSize(new Dimension(150,400));
	    
	     
	    Component verticalStrut = Box.createVerticalStrut(50);
	    frmVentanaPrincipal.getContentPane().add(verticalStrut);

	    contentPane.add(botonLogin);
	     
	    Component verticalStrut_1 = Box.createVerticalStrut(20);
	    frmVentanaPrincipal.getContentPane().add(verticalStrut_1);
	    contentPane.add(botonRegistro);
	     
	    labelApp.setAlignmentX(Component.CENTER_ALIGNMENT);
	    botonRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);

		frmVentanaPrincipal.pack();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
