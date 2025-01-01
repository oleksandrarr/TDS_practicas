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
    private Runnable onLoginSuccess;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}
	

    public LoginView(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
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
		frmLogin.setTitle("Login");
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout());

		crearPanelTitulo();
		crearPanelLogin();

		frmLogin.setResizable(false);
		frmLogin.pack();
	}

	private void crearPanelTitulo() {
		JPanel panel_Norte = new JPanel();
		panel_Norte.setBackground(Utilidades.VERDE_FONDO);
		frmLogin.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		panel_Norte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

		JLabel lblTitulo = new JLabel("AppChat");
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 30));
		lblTitulo.setForeground(Color.BLACK);
		panel_Norte.add(lblTitulo);
	}

	private void crearPanelLogin() {
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(Utilidades.VERDE_FONDO);
		panelLogin.setBorder(new EmptyBorder(10, 10, 10, 10));
		frmLogin.getContentPane().add(panelLogin, BorderLayout.CENTER);
		panelLogin.setLayout(new BorderLayout(0, 0));

		panelLogin.add(crearPanelUsuarioPassw(), BorderLayout.NORTH);
		panelLogin.add(crearPanelBotones(), BorderLayout.SOUTH);
	}

	private JPanel crearPanelUsuarioPassw() {
		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(Utilidades.VERDE_FONDO);
		TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK), 
                "Login", // Título del borde
                TitledBorder.LEADING, 
                TitledBorder.TOP, 
                null, 
                Color.BLACK);panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));

		// Panel Campo Login
		JPanel panelCampoUsuario = new JPanel();
		panelCampoUsuario.setBackground(Utilidades.VERDE_FONDO);
		panelCampos.add(panelCampoUsuario);
		panelCampoUsuario.setLayout(new BorderLayout(0, 0));

		JLabel lblUsuario = new JLabel("Usuario: ");
		panelCampoUsuario.add(lblUsuario);
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		textUsuario = new JTextField();
		textUsuario.setBackground(Utilidades.VERDE_LABELS); 
		panelCampoUsuario.add(textUsuario, BorderLayout.EAST);
		textUsuario.setColumns(15);

		// Panel Campo Password
		JPanel panelCampoPassword = new JPanel();
		panelCampoPassword.setBackground(Utilidades.VERDE_FONDO);
		panelCampos.add(panelCampoPassword);
		panelCampoPassword.setLayout(new BorderLayout(0, 0));

		JLabel lblPassword = new JLabel("Contrase\u00F1a: ");
		panelCampoPassword.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		textPassword = new JPasswordField();
		textPassword.setBackground(Utilidades.VERDE_LABELS); 
		panelCampoPassword.add(textPassword, BorderLayout.EAST);
		textPassword.setColumns(15);
		
		return panelCampos;
	}

	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Utilidades.VERDE_FONDO);
		panelBotones.setBorder(new EmptyBorder(5, 0, 5, 0));
		panelBotones.setLayout(new BorderLayout(0, 0));

		JPanel panelBotonesLoginRegistro = new JPanel();
		panelBotonesLoginRegistro.setBackground(Utilidades.VERDE_FONDO);
		panelBotones.add(panelBotonesLoginRegistro, BorderLayout.WEST);

		JButton btnLogin = new JButton("Login");
		Utilidades.crearBoton(btnLogin, 70, 30, 12);
		
		panelBotonesLoginRegistro.add(btnLogin);

		JButton btnRegistro = new JButton("Registro");
		Utilidades.crearBoton(btnRegistro, 80, 30, 12);
		panelBotonesLoginRegistro.add(btnRegistro);

		JPanel panelBotonSalir = new JPanel();
		panelBotonSalir.setBackground(Utilidades.VERDE_FONDO);
		panelBotones.add(panelBotonSalir, BorderLayout.EAST);

		JButton btnSalir = new JButton("Salir");
		Utilidades.crearBoton(btnSalir, 70, 30, 12);
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
						
						principal = new VentanaPrincipal();
						principal.mostrarVentana();
						frmLogin.dispose();
						if (onLoginSuccess != null) {
                            onLoginSuccess.run();
                        }
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
