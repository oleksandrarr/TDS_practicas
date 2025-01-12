package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class VentanaInicial {

	private JFrame frmVentanaInicial;
	private RegistroView pantallaRegistroView;

	public VentanaInicial() throws IOException {
		initialize();
	}

	public void mostrarVentana() {
		frmVentanaInicial.setLocationRelativeTo(null);
		frmVentanaInicial.setVisible(true);
	}

	public void initialize() throws IOException {
		frmVentanaInicial = new JFrame();
		frmVentanaInicial.setTitle("AppChat");
		frmVentanaInicial.getContentPane().setBackground(Utilidades.VERDE_FONDO);
		frmVentanaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaInicial.setPreferredSize(new Dimension(800, 600));

		JPanel contentPane = (JPanel) frmVentanaInicial.getContentPane();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		frmVentanaInicial.getContentPane()
				.setLayout(new BoxLayout(frmVentanaInicial.getContentPane(), BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(150);
		frmVentanaInicial.getContentPane().add(verticalStrut_2);

		JLabel labelApp = new JLabel("AppChat");
		labelApp.setFont(new Font("Serif", Font.BOLD, 50));
		labelApp.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelApp);

		URL urlIconoLogin = new URL(
				"https://png.pngtree.com/png-vector/20230528/ourmid/pngtree-avatar-icon-profile-member-login-vector-isolated-silhouette-transparent-png-image_7111828.png");
		URL urlIconoRegistro = new URL("https://cdn.icon-icons.com/icons2/2248/PNG/512/account_check_icon_135997.png");

		Image imgLogin = ImageIO.read(urlIconoLogin).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imgenLogin = new ImageIcon(imgLogin);

		Image imgReg = ImageIO.read(urlIconoRegistro).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imagenReg = new ImageIcon(imgReg);

		JButton botonLogin = new JButton("Login", imgenLogin);
		Utilidades.crearBoton(botonLogin, 200, 60, 18);
		botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton botonRegistro = new JButton("Registrarse", imagenReg);
		Utilidades.crearBoton(botonRegistro, 200, 60, 18);

		Component verticalStrut = Box.createVerticalStrut(50);
		frmVentanaInicial.getContentPane().add(verticalStrut);

		contentPane.add(botonLogin);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		frmVentanaInicial.getContentPane().add(verticalStrut_1);
		contentPane.add(botonRegistro);

		labelApp.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
		frmVentanaInicial.pack();

		// Action listener login
		botonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Instanciar y mostrar la pantalla de LoginView
				LoginView loginView = new LoginView(() -> frmVentanaInicial.dispose());
				loginView.mostrarVentana();
			}
		});
		
		// Action listener Registro
		botonRegistro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame registro = new JFrame();
				RegistroView registroView = new RegistroView(registro);
				registroView.setVisible(true);
				registro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

}
