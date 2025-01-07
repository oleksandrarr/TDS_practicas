package interfaz;



import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
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
import java.util.Optional;
import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Controlador;
import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Grupo;
import dominio.Mensaje;
import dominio.RepositorioUsuarios;
import dominio.Usuario;

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
	private String nombreChatActual;
	


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
		
		ventana = new JFrame();
		ventana.setTitle("AppChat");
		ventana.setSize(new Dimension(900, 900));
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contenedor=(JPanel) ventana.getContentPane();
		contenedor.setBackground(Utilidades.VERDE_FONDO);
		ventana.getContentPane().setBackground(Utilidades.VERDE_FONDO);
		
		añadirMenuBar();
		añadirCajaArriba();
		//ButtonGroup grupo=new ButtonGroup();
		añadirPantalla();	
		añadirCajaIzquierda();
		
		
		//Contacto contacto = Controlador.INSTANCE.getUsuarioActual().getContactos().get(0);
		//pantalla2=new Chat(contacto);
		List<Contacto> listaContactos = Controlador.INSTANCE.getUsuarioActual().getContactos();
		//list<Contactos>
		if(listaContactos.size()>0) {
			Contacto contacto1 = listaContactos.get(0);
			
			añadirChat(contacto1);
		}
		añadirListaContactos();
	}
	
	
	public void añadirListaContactos() throws IOException {
	    // Crear el modelo de la lista
	    DefaultListModel<ElementoChat> model = new DefaultListModel<>();

	    // Obtener contactos del usuario actual
	    List<Contacto> listaContactos = Controlador.INSTANCE.getUsuarioActual().getContactos();
	    //////QUITAR CODIGO QUE SOBRA////
	    // Agregar contactos al modelo
	    for (Contacto contacto : listaContactos) {

	    	if(!Controlador.INSTANCE.obtenerMensajes(contacto).isEmpty()) {
	    		

		        if (contacto instanceof ContactoIndividual) {
		            //ContactoIndividual c = (ContactoIndividual) contacto;
		            //String nombre = c.getNombreOptional().orElse(c.getNumeroTelefono());
		            model.addElement(new ElementoChat(contacto,this));
		        } else if (contacto instanceof Grupo) {
		            //Grupo g = (Grupo) contacto;
		        	
		            model.addElement(new ElementoChat(contacto,this));
		        }
	    	}
	    }

	    // Crear la lista visual
	    lista = new JList<>(model);
	    lista.setCellRenderer(new ElementoChatRenderer());

	    // Agregar un MouseListener para manejar clics en los botones
	    
	    lista.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int index = lista.locationToIndex(e.getPoint());
	            if (index != -1) {
	                ElementoChat elemento = lista.getModel().getElementAt(index);
	                Rectangle cellBounds = lista.getCellBounds(index, index);
	                Point pointInCell = new Point(e.getX() - cellBounds.x, e.getY() - cellBounds.y);
	                JButton boton = elemento.getBotonNombre();
	                if (boton != null && boton.getBounds().contains(pointInCell)) {
	                    boton.doClick(); // Simula el clic
	                }
	                try {
	                	//Esta primera linea solo sirve para CI
						//cambiarPantallaChat(Controlador.INSTANCE.getContactoPorTelefono(elemento.getTelefono()));
						cambiarPantallaChat(Controlador.INSTANCE.getContactoPorId(elemento.getIdContacto()));
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
	                
	            }
	        }
	    });

	    // Envolver la lista en un JScrollPane
	    JScrollPane scroll = new JScrollPane(lista);
	    scroll.setPreferredSize(new Dimension(320, 320));
	    scroll.getViewport().setBackground(Utilidades.VERDE_FONDO); 
	    scroll.setBorder(null); // Eliminar bordes blancos si los hay
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
		ventana.setBackground(Utilidades.VERDE_FONDO);
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
			comboBox.addItem(c);
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
		String path4 = Controlador.INSTANCE.getUsuarioActual().getImagen().toString();
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
			        	 VentanaContactos ventanaContactos = new VentanaContactos(VentanaPrincipal.this);
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
			            
			             //Para probar venta Prremium
			             //Controlador.INSTANCE.getUsuarioActual().setesPremium(true);
			             boolean esPremium = Controlador.INSTANCE.getUsuarioActual().isPremium();
						try {
							if(esPremium) {
							
								premiumCon = new PremiumCon();
								premiumCon.mostrarVentana();
							}else {
							
								premiumSin = new PremiumSin();
					            premiumSin.mostrarVentana();
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
			             
			         }
			     });
				
				
				//ActionListener boton chat nuevo
				botonPanel.addActionListener(new ActionListener() {
			         @Override
			         public void actionPerformed(ActionEvent e) {

			        	 Contacto contactoSeleccionado = (Contacto)comboBox.getSelectedItem();
			        	 //Contacto contacto = null;
			        	 /*for(Contacto c : Controlador.INSTANCE.getUsuarioActual().getContactos()) {
			        		 if(contacto.equals(c.toString())) {
			        			 contacto = c;
			        		 }
			        	 }*/
			        	 try {
							cambiarPantallaChat(contactoSeleccionado);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

/*
			        	 String contactoSeleccionado = (String) comboBox.getSelectedItem();
			        	 nombreChatActual = contactoSeleccionado;
			        	 if (contactoSeleccionado != null && !contactoSeleccionado.isEmpty()) {
			                 // Buscar el contacto correspondiente en la lista de contactos
			                 Contacto contacto = null;
			                 for (Contacto c : Controlador.INSTANCE.getUsuarioActual().getContactos()) {
			                     if (c.toString().equals(contactoSeleccionado)) {
			                         contacto = c;
			                         break;
			                     }
			                 }

			                 // Cambiar a la pantalla de chat si se encuentra el contacto
			                 if (contacto != null) {
			                     try {
			                         cambiarPantallaChat(contacto); 
			                     } catch (IOException ex) {
			                         ex.printStackTrace();
			                     }
			                 }
			         }*/

			         }
			     });
		
	}
	
	private void añadirCajaIzquierda(){
		cajaIzquierda = new JPanel();
		cajaIzquierda.setBackground(Utilidades.VERDE_FONDO);
		
		cajaIzquierda.setPreferredSize(new Dimension(380, 700));
		cajaIzquierda.setLayout(new BoxLayout(cajaIzquierda, BoxLayout.Y_AXIS));
	    pantalla.add(cajaIzquierda, BorderLayout.WEST);
		
	}
	
	private void añadirPantalla() {
		pantalla = new JPanel();
		pantalla.setBackground(Utilidades.VERDE_FONDO);
		pantalla.setLayout(new BorderLayout()); // Cambiar a BorderLayout
	    ventana.getContentPane().add(pantalla, BorderLayout.CENTER);
		
	}
	
	private void añadirChat(Contacto contacto) throws IOException{
		chat = new Chat(contacto);
		chat.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pantalla.add(chat, BorderLayout.CENTER);
	}
	
	private void cambiarPantallaChat(Contacto contacto) throws IOException {
	    
		
	    if (chat != null) {
	       pantalla.remove(chat); 
	    }
	    añadirChat(contacto);
	    pantalla.revalidate(); // Actualizar la vista
	    pantalla.repaint(); // Re-pintar la interfaz
	    
	}
	
	public void actualizarListaContactos() {
	    DefaultListModel<ElementoChat> model = (DefaultListModel<ElementoChat>) lista.getModel();

	    // Limpia el modelo actual
	    model.clear();

	    // Vuelve a cargar los contactos
	    List<Contacto> listaContactos = Controlador.INSTANCE.getUsuarioActual().getContactos();
	    for (Contacto contacto : listaContactos) {
	    	if(Controlador.INSTANCE.obtenerMensajes(contacto)!=null) {
		        if (contacto instanceof ContactoIndividual) {
		            //ContactoIndividual c = (ContactoIndividual) contacto;
		            //String nombre = c.getNombreOptional().orElse(c.getNumeroTelefono());
		            model.addElement(new ElementoChat(contacto,this));
		        } else if (contacto instanceof Grupo) {
		            //Grupo g = (Grupo) contacto;
		            model.addElement(new ElementoChat(contacto,this));
		        }
	    	}
	    }

	    // Refresca la lista
	    lista.revalidate();
	    lista.repaint();
	}
	
	
	





}
