package interfaz;

//Para completar
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ElementoListRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, 
                        Object value, int index, boolean isSelected, 
                        boolean cellHasFocus) {
    	 Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    	
        if (value instanceof ElementoChat) {
            ElementoChat ele = (ElementoChat) value;

            // Limpiar cualquier estilo previo
            ele.setOpaque(true); // Asegúrate de que el fondo sea visible
            ele.setFont(list.getFont());
            ele.setBorder(new EmptyBorder(5, 20, 5, 20)); // Márgenes internos

            if (isSelected) {
                ele.setBackground(new Color(200, 230, 255)); // Fondo azul claro para selección
                ele.setBorder(new LineBorder(Color.GREEN, 2)); // Borde verde para destacar
            } else {
                ele.setBackground(list.getBackground()); // Fondo predeterminado
                ele.setBorder(new EmptyBorder(5, 20, 5, 20)); // Sin borde al no estar seleccionado
            }

            return ele;
        } else {
            // Comportamiento predeterminado para otros tipos de elementos
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
}

