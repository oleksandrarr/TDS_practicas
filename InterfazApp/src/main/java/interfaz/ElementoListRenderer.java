package interfaz;

//Para completar
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class ElementoListRenderer extends DefaultListCellRenderer{

	@Override
	public Component getListCellRendererComponent(JList<?> list, 
						Object value, int index, boolean isSelected, 
						boolean cellHasFocus) {
		if (value!=null && value instanceof ElementoChat) {
			ElementoChat ele=(ElementoChat) value;
			if (isSelected) {
				ele.setBackground(Color.GREEN);
			} else ele.setBackground(list.getBackground());
			
			return ele;
		} 
		else return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); 

	}
}

