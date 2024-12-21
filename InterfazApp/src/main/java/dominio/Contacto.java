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

	    public String getNombre() {
			return nombre;
		}


	    public  List<Mensaje> getListaMensaje() {
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
		

	
		public  boolean registrarMensaje(Mensaje mensaje) {
			System.out.println("VA A REGISTRA444444444444444444444444444444R");
			return listaMensaje.add(mensaje);
		}

}
