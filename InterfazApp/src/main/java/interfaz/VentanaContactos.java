package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

import controlador.Controlador;
import dominio.Contacto;
import dominio.ContactoIndividual;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class VentanaContactos {

	private JFrame frame;
	private DefaultListModel<Contacto> modeloLista;
	private DefaultListModel<Contacto> modeloGrupo;
	private JList<Contacto> listaContactos;
	private JList<Contacto> listaContactosGrupo;
	private List<Contacto> listaGrupo;
	private AñadirContacto añadirContacto;
	private JPanel panelContactos;
	private JPanel panelGrupo;
	private JPanel panelBotones;
	private JPanel panelSouth;
	private JPanel panelG;
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
					VentanaContactos window = new VentanaContactos(ventanaPrincipal); // Pasar la referencia
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
	public VentanaContactos(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal; // Inicializar la referencia
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

		añadirPanelContactos();
		añadirPanelGrupo();
		añadirPanelBotones();
		añadirPanelSouth();
	}

	private void añadirPanelContactos() {
		panelContactos = new JPanel();
		panelContactos.setBackground(Utilidades.VERDE_FONDO);
		panelContactos.setLayout(new BoxLayout(panelContactos, BoxLayout.X_AXIS));
		panelContactos.setPreferredSize(new Dimension(200, 0));
		panelContactos.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame.getContentPane().add(panelContactos, BorderLayout.WEST);

		modeloLista = new DefaultListModel<>();
		List<Contacto> contactos = Controlador.INSTANCE.getUsuarioActual().getContactos();
		for (Contacto c : contactos) {

			if (c instanceof ContactoIndividual) {
				modeloLista.addElement(c);
			}

		}
		listaContactos = new JList<>(modeloLista);
		listaContactos.setBackground(Utilidades.VERDE_CLARO);
		listaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneContactos = new JScrollPane(listaContactos);
		scrollPaneContactos.setBackground(Utilidades.VERDE_LABELS);
		scrollPaneContactos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelContactos.add(scrollPaneContactos);
	}

	private void añadirPanelGrupo() {
		panelGrupo = new JPanel();
		panelGrupo.setLayout(new BoxLayout(panelGrupo, BoxLayout.X_AXIS));
		panelGrupo.setBackground(Utilidades.VERDE_FONDO);
		panelGrupo.setPreferredSize(new Dimension(200, 0));
		panelGrupo.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame.getContentPane().add(panelGrupo, BorderLayout.EAST);

		modeloGrupo = new DefaultListModel<>();
		listaContactosGrupo = new JList<>(modeloGrupo);
		listaContactosGrupo.setBackground(Utilidades.VERDE_CLARO);
		listaContactosGrupo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneGrupo = new JScrollPane(listaContactosGrupo);
		scrollPaneGrupo.getViewport().setBackground(Utilidades.VERDE_LABELS);
		scrollPaneGrupo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelGrupo.add(scrollPaneGrupo);

		scrollPaneGrupo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Grupo"));
		// panelGrupo.add(scrollPaneGrupo);
	}

	private void añadirPanelBotones() {
		panelBotones = new JPanel();
		panelBotones.setPreferredSize(new Dimension(70, 0));
		panelBotones.setBackground(Utilidades.VERDE_FONDO);
		frame.getContentPane().add(panelBotones, BorderLayout.CENTER);
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[] { 30 };
		gbl_panelBotones.rowHeights = new int[] { 50, 50, 50, 50, 50, 50 };
		gbl_panelBotones.columnWeights = new double[] { 1.0 };
		gbl_panelBotones.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 0.0 };
		panelBotones.setLayout(gbl_panelBotones);

		JButton btnNewButton_1 = new JButton("<<");
		Utilidades.crearBoton(btnNewButton_1, 60, 40, 14);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		panelBotones.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton = new JButton(">>");
		Utilidades.crearBoton(btnNewButton, 60, 40, 14);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		panelBotones.add(btnNewButton, gbc_btnNewButton);

		// actionListener >>
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contacto contactoSeleccionado = listaContactos.getSelectedValue();
				if (contactoSeleccionado != null) {
					modeloLista.removeElement(contactoSeleccionado);
					modeloGrupo.addElement(contactoSeleccionado);
					// Actualizamos la vista de ambas listas
					listaContactos.repaint();
					listaContactosGrupo.repaint();
				}
			}
		});

		// actionListener <<

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contacto contactoSeleccionado = listaContactosGrupo.getSelectedValue();
				if (contactoSeleccionado != null) {
					modeloGrupo.removeElement(contactoSeleccionado);
					modeloLista.addElement(contactoSeleccionado);
					// Actualizamos la vista de ambas listas
					listaContactos.repaint();
					listaContactosGrupo.repaint();
				}
			}
		});
	}

	private void añadirPanelSouth() {
		panelSouth = new JPanel();
		panelSouth.setBorder(new EmptyBorder(0, 10, 10, 10));
		panelSouth.setBackground(Utilidades.VERDE_FONDO);
		frame.add(panelSouth, BorderLayout.SOUTH);

		JButton btnNewButton_4 = new JButton("Aceptar");
		Utilidades.crearBoton(btnNewButton_4, 100, 30, 12);
		panelSouth.add(btnNewButton_4);

		JButton btnNewButton_2 = new JButton("Añadir Contacto Nuevo");
		Utilidades.crearBoton(btnNewButton_2, 170, 30, 12);
		panelSouth.add(btnNewButton_2);
		btnNewButton_2.setVerticalAlignment(SwingConstants.BOTTOM);

		JButton btnNewButton_3 = new JButton("Cancelar");
		Utilidades.crearBoton(btnNewButton_3, 100, 30, 12);
		panelSouth.add(btnNewButton_3);
		btnNewButton_3.setVerticalAlignment(SwingConstants.BOTTOM);

		// Action listener Añadir Contacto
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AñadirContacto añadirContacto = new AñadirContacto(() -> {
					// Actualizar la lista de contactos
					modeloLista.clear();
					List<Contacto> contactos = Controlador.INSTANCE.getUsuarioActual().getContactos();
					for (Contacto c : contactos) {
						modeloLista.addElement(c);
					}
					listaContactos.repaint(); // Refrescar la vista
				});
				añadirContacto.mostrarVentana();
				ventanaPrincipal.actualizarComboBox();
			}
		});

		// Action Listener del boton "Aceptar"
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una lista para almacenar los contactos del grupo
				List<ContactoIndividual> listaGrupo = new ArrayList<>();

				// Iterar sobre el modelo del grupo (panel derecho)
				for (int i = 0; i < modeloGrupo.size(); i++) {
					Contacto contacto = modeloGrupo.getElementAt(i);
					if (contacto instanceof ContactoIndividual) {
						listaGrupo.add((ContactoIndividual) contacto);
					}
				}

				// Pasar la lista de contactos al constructor de AñadirGrupo
				AñadirGrupo ventanaGrupo = new AñadirGrupo(listaGrupo, ventanaPrincipal);
				ventanaGrupo.mostrarVentana();

			}
		});

		// boton cancelar
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cerrar la ventana
			}
		});
	}
}
