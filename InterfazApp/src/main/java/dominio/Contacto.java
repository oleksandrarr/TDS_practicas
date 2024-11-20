package dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Contacto {
	 protected String nombre;
	 protected List<Mensaje> listaMensaje;
		  

	    public Contacto(String nombre) {
	        this.nombre = nombre;
	        this.listaMensaje = new ArrayList<Mensaje>();
	    }

	    
	    public String getNombre() {
			return nombre;
		}


		public List<Mensaje> getListaMensaje() {
			return listaMensaje;
		}
		
		


		public abstract void registrarMensaje(Mensaje mensaje);
}
