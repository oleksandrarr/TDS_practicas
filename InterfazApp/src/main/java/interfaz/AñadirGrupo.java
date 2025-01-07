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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBackground(Utilidades.VERDE_FONDO);

        // Crear el panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(Utilidades.VERDE_FONDO);
        frame.getContentPane().add(panelPrincipal);

        // Etiqueta para el nombre del grupo
        JLabel etiquetaNombre = new JLabel("Nombre del grupo:");
        etiquetaNombre.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(etiquetaNombre, BorderLayout.NORTH);

        // Campo de texto para el nombre del grupo
        nombreGrupoField = new JTextField();
        nombreGrupoField.setBackground(Utilidades.VERDE_LABELS);
        nombreGrupoField.setPreferredSize(new Dimension(300, 30));
        panelPrincipal.add(nombreGrupoField, BorderLayout.CENTER);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Utilidades.VERDE_FONDO);
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
        Utilidades.crearBoton(btnAceptar, 100, 30, 12);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreGrupo = nombreGrupoField.getText();
                String urlImagen = urlField.getText();

                if (nombreGrupo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, 
                        "El nombre del grupo no puede estar vacío.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (contactos.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, 
                        "No hay contactos elegidos.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                URL img = null;
                try {
                    if (urlImagen.isEmpty()) {
                        img = new URL("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png");
                    } else {
                        img = new URL(urlImagen);
                    }
                } catch (MalformedURLException e1) {
                    JOptionPane.showMessageDialog(frame, 
                        "La URL proporcionada no es válida.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Controlador.INSTANCE.añadirGrupo(contactos, nombreGrupo, img);
                ventanaPrincipal.actualizarListaContactos();
                frame.dispose();
            }
        });
        panelBotones.add(btnAceptar);

        // Botón "Cancelar"
        JButton btnCancelar = new JButton("Cancelar");
        Utilidades.crearBoton(btnCancelar, 100, 30, 12);
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