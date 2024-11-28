package interfaz;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class PremiumSin {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	PremiumSin window = new PremiumSin();
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
    public PremiumSin() {
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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Premium");
        frame.setBackground(new Color(40, 167, 69));
        
        JPanel panelNombre = new JPanel();
        frame.getContentPane().add(panelNombre, BorderLayout.NORTH);
        panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.Y_AXIS));
        panelNombre.setBackground(new Color(40, 167, 69));
        
        panelNombre.add(Box.createVerticalStrut(10));
        
        Box box = Box.createHorizontalBox(); 
        box.add(Box.createHorizontalGlue()); 
        JLabel lblNewLabel = new JLabel("Premium");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        box.add(lblNewLabel); 
        box.add(Box.createHorizontalGlue()); 

        panelNombre.add(box);
        
        panelNombre.add(Box.createVerticalStrut(10));
        
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(40, 167, 69));
        frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("Cancelar");
        btnNewButton.setBackground(new Color(0, 128, 0));
        btnNewButton.setForeground(Color.BLACK); // Texto blanco para contraste
        btnNewButton.setOpaque(true);
        btnNewButton.setBorderPainted(false);
        panelBotones.add(btnNewButton);
        
        
        JButton btnNewButton_1 = new JButton("Comprar");
        btnNewButton_1.setBackground(new Color(0, 128, 0));
        btnNewButton_1.setForeground(Color.BLACK); // Texto blanco para contraste
        btnNewButton_1.setOpaque(true);
        btnNewButton_1.setBorderPainted(false);
        panelBotones.add(btnNewButton_1);
        
        JPanel panelI = new JPanel();
        panelI.setBackground(new Color(40, 167, 69));
        frame.getContentPane().add(panelI, BorderLayout.WEST);
        
        JPanel panelD = new JPanel();
        panelD.setBackground(new Color(40, 167, 69));
        frame.getContentPane().add(panelD, BorderLayout.EAST);
        
        JPanel panelCentro = new JPanel();
        panelCentro.setBorder(new LineBorder(Color.BLACK));
        
        frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
        panelCentro.setBackground(new Color(111, 204, 115));
        frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
        GridBagLayout gbl_panelCentro = new GridBagLayout();
        gbl_panelCentro.columnWidths = new int[]{75, 175, 200};
        gbl_panelCentro.rowHeights = new int[]{25,50,50,50,25};
        gbl_panelCentro.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_panelCentro.rowWeights = new double[]{Double.MIN_VALUE};
        panelCentro.setLayout(gbl_panelCentro);
        
        JLabel lblNewLabel_1 = new JLabel("Fecha de registro:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 1;
        panelCentro.add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JLabel lblNewLabel_4 = new JLabel("dd/MM/yyyy");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_4.gridx = 2;
        gbc_lblNewLabel_4.gridy = 1;
        panelCentro.add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("0");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_5.gridx = 2;
        gbc_lblNewLabel_5.gridy = 2;
        panelCentro.add(lblNewLabel_5, gbc_lblNewLabel_5);
        
        JLabel lblNewLabel_2 = new JLabel("Mensajes enviados:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 2;
        panelCentro.add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Descuento disponible:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_3.gridx = 1;
        gbc_lblNewLabel_3.gridy = 3;
        panelCentro.add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        JLabel lblNewLabel_6 = new JLabel("Descuento");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_6.gridx = 2;
        gbc_lblNewLabel_6.gridy = 3;
        panelCentro.add(lblNewLabel_6, gbc_lblNewLabel_6);
        
        
        btnNewButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 frame.dispose();  // Cierra la ventana
	         }
	     });
       
    }
}
