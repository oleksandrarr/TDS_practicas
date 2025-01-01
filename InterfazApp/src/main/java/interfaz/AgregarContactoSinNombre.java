package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controlador.Controlador;

import javax.swing.*;
import java.awt.*;

public class AgregarContactoSinNombre extends JFrame {
    public AgregarContactoSinNombre(String telefono,ElementoChat elementoChat,VentanaPrincipal ventanaPrincipal) {
        setTitle("Agregar Contacto");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Encabezado
        JLabel labelAlert = new JLabel("ALERT", SwingConstants.CENTER);
        labelAlert.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelAlert, BorderLayout.NORTH);

        // Instrucciones
        JLabel labelInstructions = new JLabel("Introduzca el nombre del contacto y el teléfono", SwingConstants.CENTER);
        add(labelInstructions, BorderLayout.CENTER);

        // Panel para nombre y teléfono
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Teléfono:"));
        JTextField telefonoField = new JTextField(telefono);
        inputPanel.add(telefonoField);
        add(inputPanel, BorderLayout.CENTER);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");

        buttonPanel.add(aceptarButton);
        buttonPanel.add(cancelarButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Eventos
        cancelarButton.addActionListener(e -> dispose()); // Cierra la ventana
        aceptarButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String tel = telefonoField.getText();
            System.out.println("/////////////Nombre: " + nombre + ", Teléfono: " + tel);
            Controlador.INSTANCE.modificarContacto(nombre,tel);
            elementoChat.actualizarContacto(nombre, tel);
            ventanaPrincipal.actualizarListaContactos();
            
            dispose();
        });
    }
}
