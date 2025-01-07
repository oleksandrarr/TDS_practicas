package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controlador.Controlador;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class RegistroView extends JDialog {

	//private JFrame frmRegistroView;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblFechaNacimiento;
	private JLabel lblEmail;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblPasswordChk;
	private JLabel lblTelefono;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtFechaNacimiento;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JTextField txtTelefono;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordChk;
	private JButton btnRegistrar;
	private JButton btnCancelar;

	private JLabel lblNombreError;
	private JLabel lblApellidosError;
	private JLabel lblFechaNacimientoError;
	private JLabel lblEmailError;
	private JLabel lblUsuarioError;
	private JLabel lblPasswordError;
	private JLabel lblTelefonoError;
	private JPanel panelCampoNombre;
	private JPanel panel;
	private JPanel panelCampoApellidos;
	private JPanel panelCamposEmail;
	private JPanel panelCamposUsuario;
	private JPanel panelCamposFechaNacimiento;
	private JPanel panelCampoTelefono;
	
	
	//Campos para imagen
	private JLabel lblUrl;
	private JTextField txtUrl;
	private JLabel lblUrlError;
	private JPanel panelCampoUrl;
	
	public RegistroView(JFrame owner){
		super(owner, "Registro Usuario", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.crearPanelRegistro();
	}


	private void crearPanelRegistro() {
		this.getContentPane().setLayout(new BorderLayout());

		JPanel datosPersonales = new JPanel();
		datosPersonales.setBackground(Utilidades.VERDE_FONDO);
		this.getContentPane().add(datosPersonales);
		TitledBorder borde = new TitledBorder(new LineBorder(Color.BLACK),
				"Datos de Registro",
				TitledBorder.LEADING,
				TitledBorder.TOP,
				null,
				Color.BLACK);
		borde.setTitleFont(new Font("Times New Roman", Font.PLAIN, 12));
		datosPersonales.setBorder(borde);
		
		datosPersonales.setLayout(new BoxLayout(datosPersonales, BoxLayout.Y_AXIS));

		datosPersonales.add(creaLineaNombre());
		datosPersonales.add(crearLineaApellidos());
		datosPersonales.add(crearLineaEmail());
		datosPersonales.add(crearLineaUsuario());
		datosPersonales.add(crearLineaPassword());
		datosPersonales.add(crearLineaFechaNacimiento());
		datosPersonales.add(creaLineaTelefono());
		datosPersonales.add(creaLineaUrl());
		this.crearPanelBotones();

		this.ocultarErrores();

		this.revalidate();
		this.pack();
	}

	private JPanel creaLineaNombre() {
		JPanel lineaNombre = new JPanel();
		lineaNombre.setBackground(Utilidades.VERDE_FONDO);
		lineaNombre.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaNombre.setLayout(new BorderLayout(0, 0));
		
		panelCampoNombre = new JPanel();
		panelCampoNombre.setBackground(Utilidades.VERDE_FONDO);
		lineaNombre.add(panelCampoNombre, BorderLayout.CENTER);
		
		lblNombre = new JLabel("Nombre: ", JLabel.RIGHT);
		lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelCampoNombre.add(lblNombre);
		fixedSize(lblNombre, 75, 20);
		txtNombre = new JTextField();
		txtNombre.setBackground(Utilidades.VERDE_LABELS); 
		panelCampoNombre.add(txtNombre);
		fixedSize(txtNombre, 270, 20);
		
		lblNombreError = new JLabel("El nombre es obligatorio", SwingConstants.CENTER);
		lineaNombre.add(lblNombreError, BorderLayout.SOUTH);
		fixedSize(lblNombreError, 224, 15);
		lblNombreError.setForeground(Color.RED);
		
		return lineaNombre;
	}

	private JPanel crearLineaApellidos() {
		JPanel lineaApellidos = new JPanel();
		lineaApellidos.setBackground(Utilidades.VERDE_FONDO);
		lineaApellidos.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaApellidos.setLayout(new BorderLayout(0, 0));
		
		panelCampoApellidos = new JPanel();
		panelCampoApellidos.setBackground(Utilidades.VERDE_FONDO);
		lineaApellidos.add(panelCampoApellidos);
		
		lblApellidos = new JLabel("Apellidos: ", JLabel.RIGHT);
		lblApellidos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelCampoApellidos.add(lblApellidos);
		fixedSize(lblApellidos, 75, 20);
		txtApellidos = new JTextField();
		txtApellidos.setBackground(Utilidades.VERDE_LABELS); 
		panelCampoApellidos.add(txtApellidos);
		fixedSize(txtApellidos, 270, 20);

		
		lblApellidosError = new JLabel("Los apellidos son obligatorios", SwingConstants.CENTER);
		lineaApellidos.add(lblApellidosError, BorderLayout.SOUTH);
		fixedSize(lblApellidosError, 255, 15);
		lblApellidosError.setForeground(Color.RED);
		
		return lineaApellidos;
	}

	private JPanel crearLineaEmail() {
		JPanel lineaEmail = new JPanel();
		lineaEmail.setBackground(new Color(40, 167, 69));
		lineaEmail.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaEmail.setLayout(new BorderLayout(0, 0));
		
		panelCamposEmail = new JPanel();
		panelCamposEmail.setBackground(Utilidades.VERDE_FONDO);
		lineaEmail.add(panelCamposEmail, BorderLayout.CENTER);
		
		lblEmail = new JLabel("Email: ", JLabel.RIGHT);
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelCamposEmail.add(lblEmail);
		fixedSize(lblEmail, 75, 20);
		txtEmail = new JTextField();
		txtEmail.setBackground(Utilidades.VERDE_LABELS); 
		panelCamposEmail.add(txtEmail);
		fixedSize(txtEmail, 270, 20);
		lblEmailError = new JLabel("El Email es obligatorio", SwingConstants.CENTER);
		fixedSize(lblEmailError, 150, 15);
		lblEmailError.setForeground(Color.RED);
		lineaEmail.add(lblEmailError, BorderLayout.SOUTH);
		
		return lineaEmail;
	}
	
	private JPanel creaLineaTelefono() {
		JPanel lineaTelefono = new JPanel();
		lineaTelefono.setBackground(new Color(40, 167, 69));
		lineaTelefono.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaTelefono.setLayout(new BorderLayout(0, 0));
		
		panelCampoTelefono = new JPanel();
		panelCampoTelefono.setBackground(Utilidades.VERDE_FONDO);
		lineaTelefono.add(panelCampoTelefono, BorderLayout.CENTER);
		
		lblTelefono = new JLabel("Telefono: ", JLabel.RIGHT);
		lblTelefono.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelCampoTelefono.add(lblTelefono);
		fixedSize(lblTelefono, 75, 20);
		txtTelefono = new JTextField();
		txtTelefono.setBackground(Utilidades.VERDE_LABELS); 
		panelCampoTelefono.add(txtTelefono);
		fixedSize(txtTelefono, 270, 20);
		
		lblTelefonoError = new JLabel("El telefono es obligatorio", SwingConstants.CENTER);
		fixedSize(lblTelefonoError, 224, 15);
		lblTelefonoError.setForeground(Color.RED);
		lineaTelefono.add(lblTelefonoError, BorderLayout.SOUTH);
		lblTelefonoError.setVisible(false);
		
		return lineaTelefono;
	}


	private JPanel crearLineaUsuario() {
		JPanel lineaUsuario = new JPanel();
		lineaUsuario.setBackground(new Color(40, 167, 69));
		lineaUsuario.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaUsuario.setLayout(new BorderLayout(0, 0));
		
		panelCamposUsuario = new JPanel();
		panelCamposUsuario.setBackground(Utilidades.VERDE_FONDO);
		lineaUsuario.add(panelCamposUsuario, BorderLayout.CENTER);
		
		lblUsuario = new JLabel("Usuario: ", JLabel.RIGHT);
		lblUsuario.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelCamposUsuario.add(lblUsuario);
		fixedSize(lblUsuario, 75, 20);
		txtUsuario = new JTextField();
		txtUsuario.setBackground(Utilidades.VERDE_LABELS); 
		panelCamposUsuario.add(txtUsuario);
		fixedSize(txtUsuario, 270, 20);
		lblUsuarioError = new JLabel("El usuario ya existe", SwingConstants.CENTER);
		fixedSize(lblUsuarioError, 150, 15);
		lblUsuarioError.setForeground(Color.RED);
		lineaUsuario.add(lblUsuarioError, BorderLayout.SOUTH);
		
		return lineaUsuario;
	}

	private JPanel crearLineaPassword() {
		JPanel lineaPassword = new JPanel();
		lineaPassword.setBackground(new Color(40, 167, 69));
		lineaPassword.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaPassword.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(Utilidades.VERDE_FONDO);
		lineaPassword.add(panel, BorderLayout.CENTER);
		
		lblPassword = new JLabel("Password: ", JLabel.RIGHT);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel.add(lblPassword);
		fixedSize(lblPassword, 75, 20);
		txtPassword = new JPasswordField();
		txtPassword.setBackground(Utilidades.VERDE_LABELS); 
		panel.add(txtPassword);
		fixedSize(txtPassword, 100, 20);
		lblPasswordChk = new JLabel("Otra vez:", JLabel.RIGHT);
		lblPasswordChk.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel.add(lblPasswordChk);
		fixedSize(lblPasswordChk, 60, 20);
		txtPasswordChk = new JPasswordField();
		txtPasswordChk.setBackground(Utilidades.VERDE_LABELS); 
		panel.add(txtPasswordChk);
		fixedSize(txtPasswordChk, 100, 20);

		lblPasswordError = new JLabel("Error al introducir las contrase�as", JLabel.CENTER);
		lineaPassword.add(lblPasswordError, BorderLayout.SOUTH);
		lblPasswordError.setForeground(Color.RED);
		
		return lineaPassword;
	}

	private JPanel crearLineaFechaNacimiento() {
		JPanel lineaFechaNacimiento = new JPanel();
		lineaFechaNacimiento.setBackground(new Color(40, 167, 69));
		lineaFechaNacimiento.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaFechaNacimiento.setLayout(new BorderLayout(0, 0));
		
		panelCamposFechaNacimiento = new JPanel();
		panelCamposFechaNacimiento.setBackground(Utilidades.VERDE_FONDO);
		lineaFechaNacimiento.add(panelCamposFechaNacimiento, BorderLayout.CENTER);
		
		lblFechaNacimiento = new JLabel("Fecha de Nacimiento: ", JLabel.RIGHT);
		lblFechaNacimiento.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelCamposFechaNacimiento.add(lblFechaNacimiento);
		fixedSize(lblFechaNacimiento, 130, 20);
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBackground(Utilidades.VERDE_LABELS); 
		panelCamposFechaNacimiento.add(txtFechaNacimiento);
		fixedSize(txtFechaNacimiento, 215, 20);
		lblFechaNacimientoError = new JLabel("Introduce la fecha de nacimiento", SwingConstants.CENTER);
		fixedSize(lblFechaNacimientoError, 150, 15);
		lblFechaNacimientoError.setForeground(Color.RED);
		lineaFechaNacimiento.add(lblFechaNacimientoError, BorderLayout.SOUTH);
		
		return lineaFechaNacimiento;
	}
	
	private JPanel creaLineaUrl() {
	    JPanel lineaUrl = new JPanel();
	    lineaUrl.setBackground(Utilidades.VERDE_FONDO);
	    lineaUrl.setAlignmentX(JLabel.LEFT_ALIGNMENT);
	    lineaUrl.setLayout(new BorderLayout(0, 0));
	    
	    panelCampoUrl = new JPanel();
	    panelCampoUrl.setBackground(Utilidades.VERDE_FONDO);
	    lineaUrl.add(panelCampoUrl, BorderLayout.CENTER);
	    
	    lblUrl = new JLabel("URL: ", JLabel.RIGHT);
	    lblUrl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    panelCampoUrl.add(lblUrl);
	    fixedSize(lblUrl, 75, 20);
	    
	    txtUrl = new JTextField();
	    txtUrl.setBackground(Utilidades.VERDE_LABELS);
	    panelCampoUrl.add(txtUrl);
	    fixedSize(txtUrl, 270, 20);
	    
	    lblUrlError = new JLabel("La URL es obligatoria o inválida", SwingConstants.CENTER);
	    lineaUrl.add(lblUrlError, BorderLayout.SOUTH);
	    fixedSize(lblUrlError, 255, 15);
	    lblUrlError.setForeground(Color.RED);
	    lblUrlError.setVisible(false);
	    
	    return lineaUrl;
	}

	private void crearPanelBotones() {
		JPanel lineaBotones = new JPanel(); 
		lineaBotones.setBackground(Utilidades.VERDE_FONDO);
		this.getContentPane().add(lineaBotones, BorderLayout.SOUTH);
		lineaBotones.setBorder(new EmptyBorder(5, 0, 0, 0));
		lineaBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnRegistrar.setBackground(Utilidades.VERDE_BOTONES);
		btnRegistrar.setForeground(Color.BLACK); 
		btnRegistrar.setOpaque(true);
		btnRegistrar.setBorderPainted(false);
		lineaBotones.add(btnRegistrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCancelar.setBackground(Utilidades.VERDE_BOTONES);
		btnCancelar.setForeground(Color.BLACK); 
		btnCancelar.setOpaque(true);
		btnCancelar.setBorderPainted(false);
		lineaBotones.add(btnCancelar);

		this.crearManejadorBotonRegistrar();
		this.crearManejadorBotonCancelar();
	}

	private void crearManejadorBotonRegistrar() {
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean OK = false;
				OK = checkFields();
				if (OK) {
					boolean registrado = false;
					//Convierto el string proporcionado a una URL
					 URL url = null;
					try {
						url = new URL(txtUrl.getText());
					} catch (MalformedURLException e1) {
						
						e1.printStackTrace();
					}
					
					registrado = Controlador.INSTANCE.registrarUsuario(
							txtNombre.getText(),
							txtApellidos.getText(), 
							txtEmail.getText(), 
							txtUsuario.getText(),
							txtTelefono.getText(),
							new String(txtPassword.getPassword()), 
							txtFechaNacimiento.getText(),
							url);
					if (registrado) {
						JOptionPane.showMessageDialog(RegistroView.this, "Usuario registrado correctamente.", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
						
						LoginView loginView = new LoginView();
						loginView.mostrarVentana();
						RegistroView.this.dispose();
					} else {
						JOptionPane.showMessageDialog(RegistroView.this, "No se ha podido llevar a cabo el registro.\n",
								"Registro", JOptionPane.ERROR_MESSAGE);
						RegistroView.this.setTitle("Login Gestor Eventos");
					}
				}
			}
		});
	}

	private void crearManejadorBotonCancelar() {
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView loginView = new LoginView();
				loginView.mostrarVentana();
				RegistroView.this.dispose();
			}
		});
	}

	/**
	 * Comprueba que los campos de registro están bien
	 */
	private boolean checkFields() {
		boolean salida = true;
		/* borrar todos los errores en pantalla */
		ocultarErrores();
		if (txtNombre.getText().trim().isEmpty()) {
			lblNombreError.setVisible(true);
			lblNombre.setForeground(Color.RED);
			txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtApellidos.getText().trim().isEmpty()) {
			lblApellidosError.setVisible(true);
			lblApellidos.setForeground(Color.RED);
			txtApellidos.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtEmail.getText().trim().isEmpty()) {
			lblEmailError.setVisible(true);
			lblEmail.setForeground(Color.RED);
			txtEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtTelefono.getText().trim().isEmpty()) {
			lblTelefonoError.setVisible(true);
			lblTelefono.setForeground(Color.RED);
			txtTelefono.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtUsuario.getText().trim().isEmpty()) {
			lblUsuarioError.setText("El usuario es obligatorio");
			lblUsuarioError.setVisible(true);
			lblUsuario.setForeground(Color.RED);
			txtUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtUrl.getText().trim().isEmpty() || !esUrlValida(txtUrl.getText())) {
		    lblUrlError.setVisible(true);
		    lblUrl.setForeground(Color.RED);
		    txtUrl.setBorder(BorderFactory.createLineBorder(Color.RED));
		    salida = false;
		}
		String password = new String(txtPassword.getPassword());
		String password2 = new String(txtPasswordChk.getPassword());
		if (password.isEmpty()) {
			lblPasswordError.setText("El password no puede estar vacio");
			lblPasswordError.setVisible(true);
			lblPassword.setForeground(Color.RED);
			txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		if (password2.isEmpty()) {
			lblPasswordError.setText("El password no puede estar vacio");
			lblPasswordError.setVisible(true);
			lblPasswordChk.setForeground(Color.RED);
			txtPasswordChk.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		if (!password.equals(password2)) {
			lblPasswordError.setText("Los dos passwords no coinciden");
			lblPasswordError.setVisible(true);
			lblPassword.setForeground(Color.RED);
			lblPasswordChk.setForeground(Color.RED);
			txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			txtPasswordChk.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		 //Comprobar que no exista otro usuario con igual login 
		if (!lblUsuarioError.getText().isEmpty() && Controlador.INSTANCE.esUsuarioRegistrado(txtUsuario.getText())) {
			lblUsuarioError.setText("Ya existe ese usuario");
			lblUsuarioError.setVisible(true);
			lblUsuario.setForeground(Color.RED);
			txtUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtFechaNacimiento.getText().isEmpty()) {
			lblFechaNacimientoError.setVisible(true);
			lblFechaNacimiento.setForeground(Color.RED);
			txtFechaNacimiento.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}

		this.revalidate();
		this.pack();
		
		return salida;
	}

	/**
	 * Oculta todos los errores que pueda haber en la pantalla
	 */
	private void ocultarErrores() {
		lblNombreError.setVisible(false);
		lblApellidosError.setVisible(false);
		lblFechaNacimientoError.setVisible(false);
		lblEmailError.setVisible(false);
		lblUsuarioError.setVisible(false);
		lblPasswordError.setVisible(false);
		lblFechaNacimientoError.setVisible(false);
		
		Border border = new JTextField().getBorder();
		txtNombre.setBorder(border);
		txtApellidos.setBorder(border);
		txtEmail.setBorder(border);
		txtTelefono.setBorder(border);
		txtUsuario.setBorder(border);
		txtPassword.setBorder(border);
		txtPasswordChk.setBorder(border);
		txtFechaNacimiento.setBorder(border);
		
		lblNombre.setForeground(Color.BLACK);
		lblApellidos.setForeground(Color.BLACK);
		lblEmail.setForeground(Color.BLACK);
		lblTelefono.setForeground(Color.BLACK);
		lblUsuario.setForeground(Color.BLACK);
		lblPassword.setForeground(Color.BLACK);
		lblPasswordChk.setForeground(Color.BLACK);
		lblFechaNacimiento.setForeground(Color.BLACK);
	}
	
	
	//Comprueba si la URL proporcionado es valida:
	private boolean esUrlValida(String url) {
	    try {
	        new java.net.URL(url);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	/**
	 * Fija el tamaño de un componente
	 */
	private void fixedSize(JComponent o, int x, int y) {
		Dimension d = new Dimension(x, y);
		o.setMinimumSize(d);
		o.setMaximumSize(d);
		o.setPreferredSize(d);
	}
}