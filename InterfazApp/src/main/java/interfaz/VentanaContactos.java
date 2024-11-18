package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class VentanaContactos {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaContactos window = new VentanaContactos();
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
	public VentanaContactos() {
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
		frame.setTitle("Contactos");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		crearPanelContactos();
		crearPanelGrupo();
		crearPanelBotones();
		crearPanelSouth();

	}

	private void crearPanelContactos() {
		JPanel panelContactos = new JPanel();
		panelContactos.setLayout(new BoxLayout(panelContactos, BoxLayout.X_AXIS));
		panelContactos.setPreferredSize(new Dimension(200, 0));
		panelContactos.setBorder(new EmptyBorder(10, 10, 10, 10)); 
	    frame.add(panelContactos, BorderLayout.WEST);

		//Lista de contactos de ejemplo
        String[] contactos = {"Juan Pérez", "Ana Gómez", "Carlos Ruiz", "Maria López", "Luis Sánchez"};
        panelContactos.setLayout(new BoxLayout(panelContactos, BoxLayout.X_AXIS));
        JList<String> listaContactos = new JList<>(contactos);
        listaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
        JScrollPane scrollPaneContactos = new JScrollPane(listaContactos);
        scrollPaneContactos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelContactos.add(scrollPaneContactos);
	}
	
	private void crearPanelGrupo() {
		JPanel panelGrupo = new JPanel();
        panelGrupo.setLayout(new BoxLayout(panelGrupo, BoxLayout.X_AXIS));
        panelGrupo.setPreferredSize(new Dimension(200, 0)); 
        panelGrupo.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        frame.add(panelGrupo, BorderLayout.EAST);
        
        JPanel panelG = new JPanel();
        panelG.setLayout(new BoxLayout(panelG, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneGrupo = new JScrollPane(panelG);
        scrollPaneGrupo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        scrollPaneGrupo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Grupo"));
        panelGrupo.add(scrollPaneGrupo);
	}
	
	private void crearPanelBotones() {
		JPanel panelBotones = new JPanel();
        panelBotones.setPreferredSize(new Dimension(70, 0)); 
        frame.add(panelBotones, BorderLayout.CENTER);
        GridBagLayout gbl_panelBotones = new GridBagLayout();
        gbl_panelBotones.columnWidths = new int[]{30}; 
        gbl_panelBotones.rowHeights = new int[]{50, 50, 50, 50, 50, 50}; 
        gbl_panelBotones.columnWeights = new double[]{1.0}; 
        gbl_panelBotones.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0}; 
        panelBotones.setLayout(gbl_panelBotones);
        
        JButton btnNewButton_1 = new JButton("<<");
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_1.gridx = 0;
        gbc_btnNewButton_1.gridy = 2;
        panelBotones.add(btnNewButton_1, gbc_btnNewButton_1);
        
        JButton btnNewButton = new JButton(">>");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 3;
        panelBotones.add(btnNewButton, gbc_btnNewButton);
	}

	private void crearPanelSouth() {
		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new EmptyBorder(0, 10, 10, 10)); 
        frame.add(panelSouth, BorderLayout.SOUTH);
        
        JButton btnNewButton_4 = new JButton("Aceptar");
        panelSouth.add(btnNewButton_4);
        
        JButton btnNewButton_2 = new JButton("Añadir Contacto");
        panelSouth.add(btnNewButton_2);
        btnNewButton_2.setVerticalAlignment(SwingConstants.BOTTOM);
        
        JButton btnNewButton_3 = new JButton("Cancelar");
        panelSouth.add(btnNewButton_3);
        btnNewButton_3.setVerticalAlignment(SwingConstants.BOTTOM);
	}
}
