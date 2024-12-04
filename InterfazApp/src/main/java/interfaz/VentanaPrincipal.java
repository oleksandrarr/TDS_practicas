package interfaz;



import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Controlador;
import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Mensaje;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.*;
import interfaz.*;


public class VentanaPrincipal {

	private JFrame ventana;
	private JPanel cajaArriba;
	private JPanel cajaIzquierda;
	private JPanel chat;
	private JPanel pantalla2;
	
	private JPanel contenedor;
	private JList<ElementoChat> lista;
	private JLabel personaje;
	private ArrayList<ImageIcon> imagenes;
	private JPanel pantalla;
	Controlador controlador = Controlador.INSTANCE;
	private JComboBox comboBox;
	private VentanaContactos ventanaContactos;
	private VentanaBuscar ventanaBuscar;
	private PremiumSin premiumSin;
	private PremiumCon premiumCon;
	


	public void mostrarVentana() {
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.ventana.setVisible(true);
					System.out.print(LocalDateTime.now());
					System.out.println(LocalDateTime.now().getHour());
					DateTimeFormatter soloHora = DateTimeFormatter.ofPattern("HH:mm");
					System.out.println(LocalDateTime.now().format(soloHora));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public VentanaPrincipal() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException { //////AÑADO UN CONTACTO///////
		//-------------------------------
		
		
		Contacto contacto = controlador.añadirContactoIndividual("puan", "juan");
		//Contacto contacto = controlador.INSTANCE.getUsuarioActual().getContactoIndividual(79);		//IMP
		if(contacto==null) {
			System.out.printf("NO HAY/////////LOS CONTACTOS AL EJECUTRAR %d \n",controlador.INSTANCE.getUsuarioActual().getContactos().size());
		}
		if (contacto == null) {
			System.out.printf("Usuario actual en uso %s \n",controlador.INSTANCE.getUsuarioActual().getNombre());
		    JOptionPane.showMessageDialog(ventana, "Contacto no encontrado.");
		    return;
		}
		
		if(controlador.INSTANCE.getUsuarioActual().getContactos().size()==0) {
			System.out.println("La lista tiene tamaño ,ayot que 0");
		}
		controlador.enviarMensaje(contacto, "Hola, ¿cómo estás?", Mensaje.ENVIADO);
		controlador.enviarMensaje(contacto, "Hola, bien", Mensaje.RECIBIDO);
		//ContactoIndividual contacto = Controlador.INSTANCE.getContactoIndividual("juan");
		
		pantalla2=new Chat(contacto);
			//pantalla2.setBorder(new TitledBorder(new LineBorder(Color.BLACK), contacto.toString()));
		
		//-------------------------------

		ventana = new JFrame();
		ventana.setTitle("AppChat");
		ventana.setSize(new Dimension(900, 900));
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contenedor=(JPanel) ventana.getContentPane();
		
		añadirMenuBar();
		añadirCajaArriba();
		//ButtonGroup grupo=new ButtonGroup();
		añadirPantalla();	
		añadirCajaIzquierda();
		List<Contacto> listaContactos = Controlador.INSTANCE.getUsuarioActual().getContactos();
		//list<Contactos>
		Contacto contacto1 = listaContactos.get(0);
		añadirChat(contacto1);
        
		
		
		//Lista de valores
		lista =new JList<ElementoChat>();
		lista.setBackground(new Color(111, 204, 115));
		lista.setBackground(new Color(111, 204, 115));
		DefaultListModel<ElementoChat> model=new DefaultListModel<ElementoChat>();
		
		//como debería estar:
		/*
		DefaultListModel<String> lista = new DefaultListModel<String>();
		List<ContactoIndividual> contactos = Controlador.getUnicaInstancia().getUsuarioActual().getContactosIndividuales();
		for (ContactoIndividual c : contactos){
			if(c.getListaMensajes().size() == 0){
				lista.addElement(c.getNombre());
		 */
		
		model.addElement(new ElementoChat("https://cdn-icons-png.flaticon.com/512/3135/3135768.png","Pablo","Hola"));
		model.addElement(new ElementoChat("https://cdn-icons-png.flaticon.com/512/3135/3135768.png","Jesus","Adios"));
		model.addElement(new ElementoChat("https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359554_960_720.png","Maria","Buenos dias"));
		model.addElement(new ElementoChat("https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359554_960_720.png","Paula","Hola"));
		lista.setModel(model);
		lista.setCellRenderer(new ElementoListRenderer());
		
		//para cambiar el chat
		lista.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2) { // Detectar doble clic
		            int selectedIndex = lista.getSelectedIndex();
		            if (selectedIndex != -1) { // Verificar que hay un elemento seleccionado
		                // Obtener el elemento seleccionado (ElementoChat)
		                ElementoChat elementoSeleccionado = lista.getModel().getElementAt(selectedIndex);

		                // Buscar el contacto correspondiente al nombre del elemento
		                Contacto contacto = buscarContactoPorNombre(elementoSeleccionado.getNombre());

		                if (contacto != null) {
		                    // Crear el panel de chat y mostrarlo
		                    JPanel nuevoChat = null;
							try {
								nuevoChat = new Chat(contacto);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} // Crear el panel de chat con el contacto
		                    cambiarPantallaChat(nuevoChat); // Cambiar la pantalla principal al nuevo chat
		                } else {
		                    JOptionPane.showMessageDialog(null, "Contacto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        }
		    }
		});
		
		JScrollPane scroll=new JScrollPane(lista);
		scroll.setMinimumSize(new Dimension(320,320));
		scroll.setMaximumSize(new Dimension(320,320));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cajaIzquierda.add(scroll);
	}
	
	
	
	
	private void añadirMenuBar() {
		/*--menu--*/
		JMenuBar menubar=new JMenuBar();
		ventana.setJMenuBar(menubar);
		JMenu menuArchivo=new JMenu("Archivo");
		menubar.add(menuArchivo);
		JMenu menuOtro=new JMenu("Otro");
		menubar.add(menuOtro);
		JMenuItem itemCerrar=new JMenuItem("Cerrar");
		JMenuItem itemSalir=new JMenuItem("Salir");
		
		menuArchivo.add(itemCerrar);
		menuArchivo.add(new JSeparator());
		menuArchivo.add(itemSalir);
		
		JMenuItem itemCambiar=new JMenuItem("Cambiar pantalla");
		menuOtro.add(itemCambiar);
		itemCambiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BorderLayout layout =(BorderLayout) contenedor.getLayout();			
				JPanel dentro=(JPanel) layout.getLayoutComponent(BorderLayout.CENTER);
				contenedor.remove(dentro);
				if (dentro==pantalla) {
					contenedor.add(pantalla2,BorderLayout.CENTER);
					ventana.revalidate();
					ventana.repaint();
				} else {
					contenedor.add(pantalla,BorderLayout.CENTER);
					ventana.revalidate();
					ventana.repaint();
				}
			}
			
		});
		
		itemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();
				System.exit(0);
			}
		});
	
		itemCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();
			}
		});
		
		/*--fin menu--*/
	}
	
	private void añadirCajaArriba() throws IOException {
		cajaArriba = new JPanel();
		cajaArriba.setBackground(Utilidades.VERDE_FONDO);
		cajaArriba.setPreferredSize(new Dimension(700, 80));
		cajaArriba.setMinimumSize(new Dimension(700, 80));
		cajaArriba.setMaximumSize(new Dimension(700, 80));
		ventana.getContentPane().add(cajaArriba, BorderLayout.NORTH);
		cajaArriba.setLayout(new BoxLayout(cajaArriba, BoxLayout.X_AXIS));
		Component horizontalStrut_1_1 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1_1);
		
		comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(200, 30));
		comboBox.setMinimumSize(new Dimension(200, 30));
		comboBox.setMaximumSize(new Dimension(200, 30));
		comboBox.setBackground(Utilidades.VERDE_LABELS); // Verde medio
		comboBox.setForeground(Color.BLACK); // Texto blanco para contraste
		comboBox.setOpaque(true);
		cajaArriba.add(comboBox);
		
		List<Contacto> listaContactos = Controlador.INSTANCE.getUsuarioActual().getContactos();
		for(Contacto c : listaContactos) {
			comboBox.addItem(c.toString());
		}
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1);
		
		//boton nuevo chat
		String path = "https://cdn-icons-png.flaticon.com/512/106/106733.png";
        URL url = new URL(path);
        BufferedImage image = ImageIO.read(url);
        JButton botonPanel = new JButton(new ImageIcon(image.getScaledInstance(40, 25, Image.SCALE_SMOOTH)));
		Utilidades.crearBoton(botonPanel, 40, 40, 12);
		cajaArriba.add(botonPanel);
		
		Component horizontalStrut_1_2 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1_2);
		
		
		String path1 = "https://png.pngtree.com/png-clipart/20230401/original/pngtree-magnifying-glass-line-icon-png-image_9015864.png";
        URL url1 = new URL(path1);
        
        BufferedImage image1 = ImageIO.read(url1);
        JButton botonBusqueda = new JButton(new ImageIcon(image1.getScaledInstance(40, 25, Image.SCALE_SMOOTH)));
        Utilidades.crearBoton(botonBusqueda, 40, 40, 12);
        cajaArriba.add(botonBusqueda);
		
		
		Component horizontalStrut_1_3 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1_3);
		//Contactos
		String path2 = "https://cdn-icons-png.freepik.com/512/47/47769.png";
	    URL url2 = new URL(path2);
	    Image img2 = ImageIO.read(url2).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    ImageIcon image2 = new ImageIcon(img2);  
	    JButton botonUsuarios = new JButton("Contactos", image2);
	    Utilidades.crearBoton(botonUsuarios, 160, 40, 14);
	    cajaArriba.add(botonUsuarios);
		
		Component horizontalStrut_1_4 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1_4);
		
		//Premium
		String path3 = "https://cdn-icons-png.flaticon.com/512/126/126179.png";
	    URL url3 = new URL(path3);
	    Image img3 = ImageIO.read(url3).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    //Controlador.INSTANCE.getUsuarioActual().getImagenPerfil();
	    ImageIcon image3 = new ImageIcon(img3);  
	    JButton botonPremium = new JButton("Premium", image3);
	    Utilidades.crearBoton(botonPremium, 160, 40, 14);
	    cajaArriba.add(botonPremium);
		
		Component horizontalStrut_1_5 = Box.createHorizontalStrut(20);
		cajaArriba.add(horizontalStrut_1_5);
		
		//Nombre del usuario actual
		String nombre = Controlador.INSTANCE.getUsuarioActual().getNombre();
		String apellido = Controlador.INSTANCE.getUsuarioActual().getApellidos();
		JButton botonUsuarioActual = new JButton(nombre+" "+apellido);
		Utilidades.crearBoton(botonUsuarioActual, 130, 40, 14);
		cajaArriba.add(botonUsuarioActual);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_2);
		
		//Foto Usuario
		String path4 = "https://cdn-icons-png.flaticon.com/512/3135/3135768.png";
        URL url4 = new URL(path4);
        Image img = ImageIO.read(url4).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JButton imagenPerfil = new JButton(new ImageIcon(img));
        imagenPerfil.setContentAreaFilled(false); 
        imagenPerfil.setFocusPainted(false); 
        imagenPerfil.setBorderPainted(false);
        imagenPerfil.setOpaque(false); 
		cajaArriba.add(imagenPerfil);
		
		
		//ActionListener boton Contactos
				botonUsuarios.addActionListener(new ActionListener() {
			         @Override
			         public void actionPerformed(ActionEvent e) {
			             VentanaContactos  ventanaContactos = new  VentanaContactos();
			             ventanaContactos.mostrarVentana();
			         }
			     });
				
				//ActionListener boton Buscar
				botonBusqueda.addActionListener(new ActionListener() {
			         @Override
			         public void actionPerformed(ActionEvent e) {
			             VentanaBuscar  ventanaBuscar = new  VentanaBuscar();
			             ventanaBuscar.mostrarVentana();
			         }
			     });
				
				//ActionListener boton Premium
				botonPremium.addActionListener(new ActionListener() {
			         @Override
			         public void actionPerformed(ActionEvent e) {
			        	 //if usuario premium
			             PremiumCon premiumCon = null;
						try {
							premiumCon = new PremiumCon();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
			             premiumCon.mostrarVentana();
			             //else if usuario no premium:
			             PremiumSin  premiumSin = new PremiumSin();
			             premiumSin.mostrarVentana();
			         }
			     });
				
				
				//ActionListener boton chat nuevo
				botonPanel.addActionListener(new ActionListener() {
			         @Override
			         public void actionPerformed(ActionEvent e) {
			        	 String contactoSeleccionado = (String) comboBox.getSelectedItem();
			        	 Contacto contacto = null;
			        	 for(Contacto c : Controlador.INSTANCE.getUsuarioActual().getContactos()) {
			        		 if(contacto.equals(c.toString())) {
			        			 contacto = c;
			        		 }
			        	 }
			        	 try {
							añadirChat(contacto);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			         }
			     });
		
	}
	
	private void añadirCajaIzquierda(){
		cajaIzquierda = new JPanel();
		cajaIzquierda.setBackground(new Color(40, 167, 69));
		
		cajaIzquierda.setPreferredSize(new Dimension(320, 700));
		cajaIzquierda.setMinimumSize(new Dimension(200, 200));
		cajaIzquierda.setMaximumSize(new Dimension(500, 1000));
		pantalla.add(cajaIzquierda);
		cajaIzquierda.setLayout(new BoxLayout(cajaIzquierda, BoxLayout.Y_AXIS));
		
	}
	
	private void añadirPantalla() {
		pantalla = new JPanel();
		pantalla.setBackground(new Color(40, 167, 69));
		ventana.getContentPane().add(pantalla, BorderLayout.CENTER);
		pantalla.setLayout(new BoxLayout(pantalla, BoxLayout.X_AXIS));
		
	}
	
	private void añadirChat(Contacto contacto) throws IOException{
		
		chat = new Chat(contacto);
		chat.setPreferredSize(new Dimension(500, 700));
		chat.setMinimumSize(new Dimension(500, 700));
		chat.setMaximumSize(new Dimension(500, 700));
		pantalla.add(chat);
	}
	
	private void cambiarPantallaChat(JPanel nuevoChat) {
	    BorderLayout layout = (BorderLayout) contenedor.getLayout();
	    Component componenteActual = layout.getLayoutComponent(BorderLayout.CENTER);

	    if (componenteActual != null) {
	        contenedor.remove(componenteActual); // Remover el panel actual
	    }

	    contenedor.add(nuevoChat, BorderLayout.CENTER); // Añadir el nuevo panel
	    contenedor.revalidate(); // Actualizar la vista
	    contenedor.repaint(); // Re-pintar la interfaz
	}
	
	
	private Contacto buscarContactoPorNombre(String nombre) {
	    for (Contacto contacto : Controlador.INSTANCE.obtenerContactos()) {
	        if (contacto.getNombre().equals(nombre)) {
	            return contacto;
	        }
	    }
	    return null; // Si no se encuentra el contacto
	}





}
