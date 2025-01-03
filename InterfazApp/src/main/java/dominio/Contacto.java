package dominio;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controlador.Controlador;

public abstract class Contacto {
	protected int id;
	protected String nombre;
	protected List<Mensaje> listaMensaje;
	protected String tipoContacto;
	protected URL imagen;
	 

	    public String getTipoContacto() {
		return tipoContacto;
	}
	    
	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

		public Contacto(String nombre) {
	        this.nombre = nombre;
	        this.listaMensaje = new ArrayList<Mensaje>();
	        this.id = 0;
	    }

	    public int getId() {
			return id;
		}


		public void setListaMensaje(List<Mensaje> listaMensaje) {
			this.listaMensaje = listaMensaje;
		}

		public void setId(int id) {
			this.id = id;
		}

	    


	    public List<Mensaje> getListaMensaje() {
	    	System.out.println("va a devolver"+listaMensaje.size());
	        return listaMensaje;
	    }
	    
	    public Optional<Mensaje> getUltimoMensaje(){
	    	if(listaMensaje == null || listaMensaje.isEmpty()) {
	    		return Optional.empty();
	    	}else {
	    		return Optional.of(listaMensaje.getLast());
	    	}
	    }
		
		public String toString() {
			return nombre;
		}
		

	
		public  abstract boolean registrarMensaje(Mensaje mensaje);
		
		public String getNombre() {
		        return nombre; // Proporciona un comportamiento genérico para todas las subclases
		}
		
		public void setNombre(String nombre) {
			this.nombre=nombre;
		}
}
