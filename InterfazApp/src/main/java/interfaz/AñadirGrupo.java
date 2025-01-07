package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import dominio.Contacto;
import dominio.ContactoIndividual;

public class AñadirGrupo {

    private JFrame frame;
    private JTextField nombreGrupoField;
    private List<ContactoIndividual> contactos;
    private String nombreGrupo;
    private VentanaPrincipal ventanaPrincipal;
    private JTextField urlField; // Campo de texto para la URL
    private String imagen; // Variable para guardar el valor de la URL

    /**
     * Constructor para inicializar la GUI de Añadir Grupo.
     * @param ventanaPrincipal 
     */
    public AñadirGrupo(List<ContactoIndividual> contactos, VentanaPrincipal ventanaPrincipal) {
        initialize(contactos,ventanaPrincipal);
       
    }

    /**
     * Muestra la ventana.
     */
    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Inicializa el contenido de la ventana.
     */
    private void initialize(List<ContactoIndividual> contactos,VentanaPrincipal ventanaPrincipal) {
    	this.ventanaPrincipal=ventanaPrincipal;
        frame = new JFrame("Añadir Grupo");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear el panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(panelPrincipal);

        // Etiqueta para el nombre del grupo
        JLabel etiquetaNombre = new JLabel("Nombre del grupo:");
        panelPrincipal.add(etiquetaNombre, BorderLayout.NORTH);

        // Campo de texto para el nombre del grupo
        nombreGrupoField = new JTextField();
        nombreGrupoField.setPreferredSize(new Dimension(300, 30));
        panelPrincipal.add(nombreGrupoField, BorderLayout.CENTER);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
     // Etiqueta para la URL
        JLabel etiquetaUrl = new JLabel("URL de la imagen:");
        panelPrincipal.add(etiquetaUrl, BorderLayout.NORTH);

        // Campo de texto para la URL
        urlField = new JTextField();
        urlField.setPreferredSize(new Dimension(300, 30));
        panelPrincipal.add(urlField, BorderLayout.CENTER);

        // Botón "Aceptar"
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreGrupo = nombreGrupoField.getText();
                String urlImagen = urlField.getText(); // Obtener el valor de la URL

                if (!nombreGrupo.isEmpty() && !urlImagen.isEmpty()) {
                		URL img = null;
                		if(!urlImagen.isEmpty()) {
                			try {
								img = new URL("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png");
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                		}
                		else {
                        
						try {
							img = new URL(urlImagen);
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                		}
                        Controlador.INSTANCE.añadirGrupo(contactos, nombreGrupo, img); // Pasar la URL al controlador
                        frame.dispose();
                    
                } else {
                    System.out.println("El nombre del grupo y la URL no pueden estar vacíos.");
                }

                ventanaPrincipal.actualizarListaContactos();
            }
        });

        panelBotones.add(btnAceptar);

        // Botón "Cancelar"
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panelBotones.add(btnCancelar);
        this.contactos=contactos;
        //Controlador.INSTANCE.añadirGrupo(contactos,nombreGrupo);
    }

    
  
}