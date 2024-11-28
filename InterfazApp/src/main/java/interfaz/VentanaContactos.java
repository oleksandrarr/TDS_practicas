package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
	private DefaultListModel<String> modelContactos;
    private DefaultListModel<String> modelGrupo;

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		 modelContactos = new DefaultListModel<>();
		 modelGrupo = new DefaultListModel<>();
		
		JPanel panelContactos = new JPanel();
		panelContactos.setBackground(new Color(40, 167, 69));
		panelContactos.setLayout(new BoxLayout(panelContactos, BoxLayout.X_AXIS));
		panelContactos.setPreferredSize(new Dimension(200, 0));
		panelContactos.setBorder(new EmptyBorder(10, 10, 10, 10)); 
	    frame.add(panelContactos, BorderLayout.WEST);

		//Lista de contactos de ejemplo
        String[] contactos = {"Juan Pérez", "Ana Gómez", "Carlos Ruiz", "Maria López", "Luis Sánchez"};
        for (String contacto : contactos) {
            modelContactos.addElement(contacto);
        }
        //panelContactos.setLayout(new BoxLayout(panelContactos, BoxLayout.X_AXIS));
        JList<String> listaContactos = new JList<>(modelContactos);
        listaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
        JScrollPane scrollPaneContactos = new JScrollPane(listaContactos);
        scrollPaneContactos.setBackground(new Color(111, 204, 115));
        scrollPaneContactos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelContactos.add(scrollPaneContactos);
        
        
		JPanel panelGrupo = new JPanel();
        panelGrupo.setLayout(new BoxLayout(panelGrupo, BoxLayout.X_AXIS));
        panelGrupo.setBackground(new Color(40, 167, 69));
        panelGrupo.setPreferredSize(new Dimension(200, 0)); 
        panelGrupo.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        frame.add(panelGrupo, BorderLayout.EAST);
        
        JPanel panelG = new JPanel();
        panelG.setLayout(new BoxLayout(panelG, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneGrupo = new JScrollPane(panelG);
        scrollPaneGrupo.setBackground(new Color(111, 204, 115));
        scrollPaneGrupo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        //Lista contactos en el grupo
        //panelG.setLayout(new BoxLayout(panelG, BoxLayout.X_AXIS));
        JList<String> listaContactosGrupo = new JList<>(modelGrupo);
        listaContactosGrupo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
        scrollPaneGrupo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Grupo"));
        panelGrupo.add(scrollPaneGrupo);
        
        
		JPanel panelBotones = new JPanel();
        panelBotones.setPreferredSize(new Dimension(70, 0)); 
        panelBotones.setBackground(new Color(40, 167, 69));
        frame.add(panelBotones, BorderLayout.CENTER);
        GridBagLayout gbl_panelBotones = new GridBagLayout();
        gbl_panelBotones.columnWidths = new int[]{30}; 
        gbl_panelBotones.rowHeights = new int[]{50, 50, 50, 50, 50, 50}; 
        gbl_panelBotones.columnWeights = new double[]{1.0}; 
        gbl_panelBotones.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0}; 
        panelBotones.setLayout(gbl_panelBotones);
        
        JButton btnNewButton_1 = new JButton("<<");
        btnNewButton_1.setBackground(new Color(0, 128, 0));
        btnNewButton_1.setForeground(Color.BLACK); // Texto blanco para contraste
        btnNewButton_1.setOpaque(true);
        btnNewButton_1.setBorderPainted(false);
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_1.gridx = 0;
        gbc_btnNewButton_1.gridy = 2;
        panelBotones.add(btnNewButton_1, gbc_btnNewButton_1);
        
        JButton btnNewButton = new JButton(">>");
        btnNewButton.setBackground(new Color(0, 128, 0));
        btnNewButton.setForeground(Color.BLACK); // Texto blanco para contraste
        btnNewButton.setOpaque(true);
        btnNewButton.setBorderPainted(false);
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 3;
        panelBotones.add(btnNewButton, gbc_btnNewButton);
        
      
		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new EmptyBorder(0, 10, 10, 10)); 
		panelSouth.setBackground(new Color(40, 167, 69));
        frame.add(panelSouth, BorderLayout.SOUTH);
        
        JButton btnNewButton_4 = new JButton("Aceptar");
        btnNewButton_4.setBackground(new Color(0, 128, 0));
        btnNewButton_4.setForeground(Color.BLACK); // Texto blanco para contraste
        btnNewButton_4.setOpaque(true);
        btnNewButton_4.setBorderPainted(false);
        panelSouth.add(btnNewButton_4);
        
        JButton btnNewButton_2 = new JButton("Añadir Contacto");
        btnNewButton_2.setBackground(new Color(0, 128, 0));
        btnNewButton_2.setForeground(Color.BLACK); // Texto blanco para contraste
        btnNewButton_2.setOpaque(true);
        btnNewButton_2.setBorderPainted(false);
        panelSouth.add(btnNewButton_2);
        btnNewButton_2.setVerticalAlignment(SwingConstants.BOTTOM);
        
        JButton btnNewButton_3 = new JButton("Cancelar");
        btnNewButton_3.setBackground(new Color(0, 128, 0));
        btnNewButton_3.setForeground(Color.BLACK); // Texto blanco para contraste
        btnNewButton_3.setOpaque(true);
        btnNewButton_3.setBorderPainted(false);
        panelSouth.add(btnNewButton_3);
        btnNewButton_3.setVerticalAlignment(SwingConstants.BOTTOM);
        
        
        //actionListener << y >>
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String contactoSeleccionado = listaContactos.getSelectedValue();
                if (contactoSeleccionado != null) {
                    modelContactos.removeElement(contactoSeleccionado);
                    modelGrupo.addElement(contactoSeleccionado);
                    // Actualizamos la vista de ambas listas
                    listaContactos.repaint();
                    listaContactosGrupo.repaint();
                }
            }
        });
        
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String contactoSeleccionado = listaContactosGrupo.getSelectedValue();
                if (contactoSeleccionado != null) {
                    modelGrupo.removeElement(contactoSeleccionado);
                    modelContactos.addElement(contactoSeleccionado);
                    // Actualizamos la vista de ambas listas
                    listaContactos.repaint();
                    listaContactosGrupo.repaint();
                }
            }
        });
        
        //boton cancelar
        btnNewButton_3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); // Cerrar la ventana
                }
            });
        
        
        
	}
}
