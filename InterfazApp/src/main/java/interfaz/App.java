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
import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.EtchedBorder;
import javax.imageio.ImageIO;
import javax.swing.*;
import interfaz.*;


public class App {

	private JFrame ventana;
	private JPanel contenedor;
	private JList<ElementoChat> lista;
	private JLabel personaje;
	private ArrayList<ImageIcon> imagenes;
	private JPanel pantalla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
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
	public App() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		//-------------------------------
		JPanel pantalla2=new Chat();
		imagenes=new ArrayList<ImageIcon>();
		imagenes.add(new ImageIcon(getClass().getResource("/resources/pikachu.png")));
		imagenes.add(new ImageIcon(getClass().getResource("/resources/Mujermaravilla.png")));
		imagenes.add(new ImageIcon(getClass().getResource("/resources/shongoku.png")));
		imagenes.add(new ImageIcon(getClass().getResource("/resources/raro.png")));
		//-------------------------------
		ventana = new JFrame();
		ventana.setSize(new Dimension(900, 900));
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contenedor=(JPanel) ventana.getContentPane();
		/*--menu--*/
		JMenuBar menubar=new JMenuBar();
		ventana.setJMenuBar(menubar);
		JMenu menuArchivo=new JMenu("Archivo");
		menubar.add(menuArchivo);
		JMenu menuOtro=new JMenu("Otro");
		menubar.add(menuOtro);
		JMenuItem itemAbrir=new JMenuItem("Abrir");
		JMenuItem itemCerrar=new JMenuItem("Cerrar");
		JMenuItem itemSalir=new JMenuItem("Salir");
		
		menuArchivo.add(itemAbrir);
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
		
		/*--fin menu--*/
		JPanel cajaArriba = new JPanel();
		cajaArriba.setBackground(Color.GREEN);
		cajaArriba.setPreferredSize(new Dimension(700, 80));
		cajaArriba.setMinimumSize(new Dimension(700, 80));
		cajaArriba.setMaximumSize(new Dimension(700, 80));
		ventana.getContentPane().add(cajaArriba, BorderLayout.NORTH);
		cajaArriba.setLayout(new BoxLayout(cajaArriba, BoxLayout.X_AXIS));
		
		ButtonGroup grupo=new ButtonGroup();
		
		Component horizontalStrut_1_1 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(200, 30));
		comboBox.setMinimumSize(new Dimension(200, 30));
		comboBox.setMaximumSize(new Dimension(200, 30));
		cajaArriba.add(comboBox);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1);
		
		String path = "https://st.depositphotos.com/1000507/55277/v/380/depositphotos_552772436-stock-illustration-forward-email-arrow-icon.jpg";
        URL url = new URL(path);
        BufferedImage image = ImageIO.read(url);
        JButton botonPanel = new JButton(new ImageIcon(image.getScaledInstance(40, 25, Image.SCALE_SMOOTH)));
		botonPanel.setPreferredSize(new Dimension(40, 40));
		botonPanel.setMinimumSize(new Dimension(40, 40));
		botonPanel.setMaximumSize(new Dimension(40, 40));
		cajaArriba.add(botonPanel);
		
		Component horizontalStrut_1_2 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1_2);
		
		
	
		String path1 = "https://e7.pngegg.com/pngimages/464/384/png-clipart-computer-icons-magnifying-glass-magnifier-symbol-magnifying-glass-text-magnifier.png";
        URL url1 = new URL(path1);
        BufferedImage image1 = ImageIO.read(url1);
        JButton botonBusqueda = new JButton(new ImageIcon(image1.getScaledInstance(40, 25, Image.SCALE_SMOOTH)));
        botonBusqueda.setPreferredSize(new Dimension(40, 40));
		botonBusqueda.setMinimumSize(new Dimension(40, 40));
		botonBusqueda.setMaximumSize(new Dimension(40, 40));
        cajaArriba.add(botonBusqueda);
		
		
		Component horizontalStrut_1_3 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1_3);
		//Contactos
		String path2 = "https://cdn-icons-png.freepik.com/512/47/47769.png";
	    URL url2 = new URL(path2);
	    Image img2 = ImageIO.read(url2).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    ImageIcon image2 = new ImageIcon(img2);  
	    JButton botonUsuarios = new JButton("Contactos", image2);
	    botonUsuarios.setHorizontalTextPosition(SwingConstants.RIGHT); // Posicionar el texto a la derecha de la imagen
	    botonUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Opcional, para un estilo de texto
	    botonUsuarios.setPreferredSize(new Dimension(100, 40));
		botonUsuarios.setMinimumSize(new Dimension(100, 40));
		botonUsuarios.setMaximumSize(new Dimension(200, 40));
	    cajaArriba.add(botonUsuarios);
		
		Component horizontalStrut_1_4 = Box.createHorizontalStrut(10);
		cajaArriba.add(horizontalStrut_1_4);
		
		//Premium
		String path3 = "https://cdn-icons-png.flaticon.com/512/126/126179.png";
	    URL url3 = new URL(path3);
	    Image img3 = ImageIO.read(url3).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    ImageIcon image3 = new ImageIcon(img3);  
	    JButton botonPremium = new JButton("Premium", image3);
	    botonPremium.setHorizontalTextPosition(SwingConstants.RIGHT); // Posicionar el texto a la derecha de la imagen
	    botonPremium.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Opcional, para un estilo de texto
	    botonPremium.setPreferredSize(new Dimension(100, 40));
	    botonPremium.setMinimumSize(new Dimension(100, 40));
	    botonPremium.setMaximumSize(new Dimension(200, 40));
	    cajaArriba.add(botonPremium);
		
		Component horizontalStrut_1_5 = Box.createHorizontalStrut(20);
		cajaArriba.add(horizontalStrut_1_5);
		
		JLabel lblNewLabel = new JLabel("Pascual Angosto");
		cajaArriba.add(lblNewLabel);
		
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
		
		pantalla = new JPanel();
		pantalla.setBackground(Color.ORANGE);
		ventana.getContentPane().add(pantalla, BorderLayout.CENTER);
		pantalla.setLayout(new BoxLayout(pantalla, BoxLayout.X_AXIS));
		
		JPanel cajaIzquierda = new JPanel();
		cajaIzquierda.setBackground(Color.ORANGE);
		
		cajaIzquierda.setPreferredSize(new Dimension(320, 700));
		cajaIzquierda.setMinimumSize(new Dimension(200, 200));
		cajaIzquierda.setMaximumSize(new Dimension(500, 1000));
		pantalla.add(cajaIzquierda);
		cajaIzquierda.setLayout(new BoxLayout(cajaIzquierda, BoxLayout.Y_AXIS));
		
		
         
         
         
         
		JPanel chat = new Chat();
		chat.setPreferredSize(new Dimension(500, 700));
		chat.setMinimumSize(new Dimension(500, 700));
		chat.setMaximumSize(new Dimension(500, 700));
		pantalla.add(chat);
		
		personaje = new JLabel("");
		chat.add(personaje);
		
		//Elemento aux= new Elemento("SMV.png","Mujer Maravilla",50,85,85);
		//cajaIzquierda.add(aux);
		
		//Lista de valores
		lista =new JList<ElementoChat>();
		DefaultListModel<ElementoChat> model=new DefaultListModel<ElementoChat>();
		model.addElement(new ElementoChat("https://cdn-icons-png.flaticon.com/512/3135/3135768.png","Pablo","Hola"));
		model.addElement(new ElementoChat("https://cdn-icons-png.flaticon.com/512/3135/3135768.png","Jesus","Adios"));
		model.addElement(new ElementoChat("https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359554_960_720.png","Maria","Buenos dias"));
		model.addElement(new ElementoChat("https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359554_960_720.png","Paula","Hola"));
		lista.setModel(model);
		lista.setCellRenderer(new ElementoListRenderer());
		
		lista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2)
				  personaje.setIcon(imagenes.get(lista.getSelectedIndex()));	
			}
		});
		
		JScrollPane scroll=new JScrollPane(lista);
		scroll.setMinimumSize(new Dimension(320,320));
		scroll.setMaximumSize(new Dimension(320,320));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cajaIzquierda.add(scroll);
	}



}
