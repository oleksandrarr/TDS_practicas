package interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ElementoChatRenderer implements ListCellRenderer<ElementoChat> {
    @Override
    public Component getListCellRendererComponent(
            JList<? extends ElementoChat> list,
            ElementoChat value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        // Actualizar el fondo según si la celda está seleccionada
        if (isSelected) {
            value.setBackground(new Color(184, 207, 229)); // Fondo azul claro
        } else {
            value.setBackground(Color.GREEN); // Fondo blanco
        }

        // Actualizar el borde según si la celda tiene el foco
        if (cellHasFocus) {
            value.setBorder(new LineBorder(Color.BLUE, 1)); // Borde azul para el foco
        } else {
            value.setBorder(new LineBorder(Color.LIGHT_GRAY, 1)); // Borde gris claro
        }

        // Asegurar que los componentes internos reflejen el estado actual
        value.repaint();

        // Devolver el componente ajustado
        return value;
    }
}
