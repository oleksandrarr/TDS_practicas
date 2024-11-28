package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LoginView {

	private JFrame frmLogin;
	private JTextField textUsuario;
	private JPasswordField textPassword;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	public void mostrarVentana() {
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}
	
	/********************************************************************** 
	 * Procurar organizar la creación de una ventana en varios métodos
	 * con el fin de facilitar su comprensión. Esta clase muestra un ejemplo
	 **********************************************************************/
	
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login AppVideo");
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout());

		crearPanelTitulo();
		crearPanelLogin();

		frmLogin.setResizable(false);
		frmLogin.pack();
	}

	private void crearPanelTitulo() {
		JPanel panel_Norte = new JPanel();
		panel_Norte.setBackground(new Color(40, 167, 69));
		frmLogin.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		panel_Norte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

		JLabel lblTitulo = new JLabel("AppVideo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setForeground(Color.DARK_GRAY);
		panel_Norte.add(lblTitulo);
	}

	private void crearPanelLogin() {
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(40, 167, 69));
		panelLogin.setBorder(new EmptyBorder(10, 10, 10, 10));
		frmLogin.getContentPane().add(panelLogin, BorderLayout.CENTER);
		panelLogin.setLayout(new BorderLayout(0, 0));

		panelLogin.add(crearPanelUsuarioPassw(), BorderLayout.NORTH);
		panelLogin.add(crearPanelBotones(), BorderLayout.SOUTH);
	}

	private JPanel crearPanelUsuarioPassw() {
		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(new Color(40, 167, 69));
		TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK), 
                "Login", // Título del borde
                TitledBorder.LEADING, 
                TitledBorder.TOP, 
                null, 
                Color.BLACK);panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));

		// Panel Campo Login
		JPanel panelCampoUsuario = new JPanel();
		panelCampoUsuario.setBackground(new Color(40, 167, 69));
		panelCampos.add(panelCampoUsuario);
		panelCampoUsuario.setLayout(new BorderLayout(0, 0));

		JLabel lblUsuario = new JLabel("Usuario: ");
		panelCampoUsuario.add(lblUsuario);
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textUsuario = new JTextField();
		textUsuario.setBackground(new Color(111, 204, 115)); 
		panelCampoUsuario.add(textUsuario, BorderLayout.EAST);
		textUsuario.setColumns(15);

		// Panel Campo Password
		JPanel panelCampoPassword = new JPanel();
		panelCampoPassword.setBackground(new Color(40, 167, 69));
		panelCampos.add(panelCampoPassword);
		panelCampoPassword.setLayout(new BorderLayout(0, 0));

		JLabel lblPassword = new JLabel("Contrase\u00F1a: ");
		lblPassword.setBackground(new Color(111, 204, 115)); 
		panelCampoPassword.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textPassword = new JPasswordField();
		textPassword.setBackground(new Color(111, 204, 115)); 
		panelCampoPassword.add(textPassword, BorderLayout.EAST);
		textPassword.setColumns(15);
		
		return panelCampos;
	}

	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(40, 167, 69));
		panelBotones.setBorder(new EmptyBorder(5, 0, 5, 0));
		panelBotones.setLayout(new BorderLayout(0, 0));

		JPanel panelBotonesLoginRegistro = new JPanel();
		panelBotonesLoginRegistro.setBackground(new Color(40, 167, 69));
		panelBotones.add(panelBotonesLoginRegistro, BorderLayout.WEST);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(0, 128, 0));
		btnLogin.setForeground(Color.BLACK); // Texto blanco para contraste
		btnLogin.setOpaque(true);
		btnLogin.setBorderPainted(false);
		
		panelBotonesLoginRegistro.add(btnLogin);

		JButton btnRegistro = new JButton("Registro");
		btnRegistro.setBackground(new Color(0, 128, 0));
		btnRegistro.setForeground(Color.BLACK); // Texto blanco para contraste
		btnRegistro.setOpaque(true);
		btnRegistro.setBorderPainted(false);
		panelBotonesLoginRegistro.add(btnRegistro);

		JPanel panelBotonSalir = new JPanel();
		panelBotonSalir.setBackground(new Color(40, 167, 69));
		panelBotones.add(panelBotonSalir, BorderLayout.EAST);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(new Color(0, 128, 0));
		btnSalir.setForeground(Color.BLACK); // Texto blanco para contraste
		btnSalir.setOpaque(true);
		btnSalir.setBorderPainted(false);
		panelBotonSalir.add(btnSalir);

		addManejadorBotonLogin(btnLogin);
		addManejadorBotonRegistro(btnRegistro);
		addManejadorBotonSalir(btnSalir);
		
		return panelBotones;
	}

	private void addManejadorBotonSalir(JButton btnSalir) {
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				System.exit(0);
			}
		});
	}

	private void addManejadorBotonRegistro(JButton btnRegistro) {
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroView registro = new RegistroView (frmLogin);
				registro.setLocationRelativeTo(frmLogin);                              	
				registro.setVisible(true);
				frmLogin.dispose();
			}
		});
	}

	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean login = Controlador.INSTANCE.loginUsuario(
						textUsuario.getText(),
						new String(textPassword.getPassword()));

				if (login) {
					VentanaPrincipal principal;
					try {
						System.out.println("Entra bien");
						principal = new VentanaPrincipal();
						principal.mostrarVentana();
						frmLogin.dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				} else
					JOptionPane.showMessageDialog(frmLogin, "Nombre de usuario o contraseña no valido",
							"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

}
