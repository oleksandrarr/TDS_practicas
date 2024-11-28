package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class AñadirContacto {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadirContacto window = new AñadirContacto();
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
	public AñadirContacto() {
		initialize();
	}

	public void mostrarVentana() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setTitle("Añadir Contacto");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(40, 167, 69));
        frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("Aceptar");
        btnNewButton.setBackground(new Color(0, 128, 0)); // Verde medio
        btnNewButton.setForeground(Color.BLACK); // Texto negro
        btnNewButton.setOpaque(true);
        btnNewButton.setBorderPainted(false);
        panelBotones.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Cancelar");
        btnNewButton_1.setBackground(new Color(0, 128, 0)); // Verde medio
        btnNewButton_1.setForeground(Color.BLACK); // Texto negro
        btnNewButton_1.setOpaque(true);
        btnNewButton_1.setBorderPainted(false);
        panelBotones.add(btnNewButton_1);

        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new Color(40, 167, 69));
        frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
        GridBagLayout gbl_panelCentro = new GridBagLayout();
        gbl_panelCentro.columnWidths = new int[]{100, 300, 25}; // Ancho de las columnas
        gbl_panelCentro.rowHeights = new int[]{50, 50, 50, 50, 50};
        gbl_panelCentro.columnWeights = new double[]{0.0, 1.0}; // Peso para permitir expansión horizontal
        gbl_panelCentro.rowWeights = new double[]{0, 0, 0.0, 0, 0};
        panelCentro.setLayout(gbl_panelCentro);

        JLabel lblNewLabel = new JLabel("Introduzca el nombre del contacto y su teléfono");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 2;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        panelCentro.add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 2;
        panelCentro.add(lblNombre, gbc_lblNombre);

        JLabel lblNewLabel_1 = new JLabel("Teléfono:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 3;
        panelCentro.add(lblNewLabel_1, gbc_lblNewLabel_1);

        JTextField textoNombre = new JTextField();
        textoNombre.setBackground(new Color(199, 235, 201));
        GridBagConstraints gbc_textoNombre = new GridBagConstraints();
        gbc_textoNombre.insets = new Insets(5, 5, 0, 0);
        gbc_textoNombre.gridx = 1;
        gbc_textoNombre.gridy = 2;
        gbc_textoNombre.weightx = 1.0; // Asigna peso para expansión horizontal
        gbc_textoNombre.fill = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
        panelCentro.add(textoNombre, gbc_textoNombre);

        JTextField textoTelefono = new JTextField();
        textoTelefono.setBackground(new Color(199, 235, 201));
        GridBagConstraints gbc_textoTelefono = new GridBagConstraints();
        gbc_textoTelefono.insets = new Insets(5, 5, 0, 0);
        gbc_textoTelefono.gridx = 1;
        gbc_textoTelefono.gridy = 3;
        gbc_textoTelefono.weightx = 1.0; // Asigna peso para expansión horizontal
        gbc_textoTelefono.fill = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
        panelCentro.add(textoTelefono, gbc_textoTelefono);
        
        
        //boton cancelar
        btnNewButton_1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); // Cerrar la ventana
                }
            });
        
    }

}
