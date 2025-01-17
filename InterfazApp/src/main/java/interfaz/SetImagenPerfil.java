package interfaz;

import javax.swing.*;

import controlador.Controlador;

import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SetImagenPerfil {

    private URL urlSeleccionada; // URL seleccionada
    private VentanaPrincipal ventanaPrincipal; // Referencia a VentanaPrincipal

    public SetImagenPerfil(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal; // Guardar referencia
    }

    public void mostrarVentana(JFrame ventanaPadre) {
        // Crear el marco
        JFrame ventana = new JFrame("Arrastra y suelta una imagen aquí");
        ventana.setSize(400, 250);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        // Crear el panel para Drag and Drop
        JPanel panelDragDrop = new JPanel();
        panelDragDrop.setBackground(Color.LIGHT_GRAY);
        panelDragDrop.setBorder(BorderFactory.createTitledBorder("Arrastra y suelta una imagen aquí"));
        panelDragDrop.setLayout(new BorderLayout());

        // Etiqueta para mensajes
        JLabel etiquetaMensaje = new JLabel("Arrastra una imagen aquí", SwingConstants.CENTER);
        etiquetaMensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        panelDragDrop.add(etiquetaMensaje, BorderLayout.CENTER);

        // Botón de aceptar
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.setEnabled(false); // Deshabilitado hasta que se seleccione una imagen
        botonAceptar.addActionListener(e -> {
            if (urlSeleccionada != null) {
                try {
                    // Llamar al método en VentanaPrincipal para actualizar la imagen de perfil
                    ventanaPrincipal.actualizarImagenPerfil(new ImageIcon(urlSeleccionada));
                    // Llamar al Controlador para actualizar la imagen del usuario
                    Controlador.INSTANCE.getUsuarioActual().setImagen(urlSeleccionada);

                    // Mostrar mensaje de confirmación
                    JOptionPane.showMessageDialog(ventanaPadre,
                            "Imagen actualizada correctamente.",
                            "Confirmación",
                            JOptionPane.INFORMATION_MESSAGE);

                    ventana.dispose(); // Cierra la ventana después de aceptar
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ventanaPadre,
                            "Error al actualizar la imagen.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Habilitar Drag and Drop
        new DropTarget(panelDragDrop, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                panelDragDrop.setBackground(Color.GRAY); // Cambia el color para indicar que se puede soltar
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {
                // No es necesario implementar aquí
            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
                // No es necesario implementar aquí
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
                panelDragDrop.setBackground(Color.LIGHT_GRAY); // Restaurar el color original
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transferable = dtde.getTransferable();
                    if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        List<File> archivos = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        if (!archivos.isEmpty()) {
                            File archivoSeleccionado = archivos.get(0);
                            // Convertir el archivo a una URL
                            urlSeleccionada = archivoSeleccionado.toURI().toURL();
                            etiquetaMensaje.setText("Imagen seleccionada: " + archivoSeleccionado.getName());
                            panelDragDrop.setBackground(Color.GREEN); // Indicar éxito
                            botonAceptar.setEnabled(true); // Habilitar el botón de aceptar
                        }
                    } else {
                        etiquetaMensaje.setText("Formato no soportado");
                        panelDragDrop.setBackground(Color.RED); // Indicar error
                    }
                } catch (MalformedURLException ex) {
                    etiquetaMensaje.setText("Error al convertir a URL");
                    panelDragDrop.setBackground(Color.RED);
                    ex.printStackTrace();
                } catch (Exception ex) {
                    etiquetaMensaje.setText("Error al procesar la imagen");
                    panelDragDrop.setBackground(Color.RED);
                    ex.printStackTrace();
                }
            }
        });

        // Panel para el botón
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(botonAceptar);

        // Agregar componentes al marco
        ventana.add(panelDragDrop, BorderLayout.CENTER);
        ventana.add(panelBoton, BorderLayout.SOUTH);
        ventana.setLocationRelativeTo(ventanaPadre); // Centrar respecto a la ventana padre
        ventana.setVisible(true);
    }
}
