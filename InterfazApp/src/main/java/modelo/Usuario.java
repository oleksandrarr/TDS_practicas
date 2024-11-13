package modelo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	 private String nombre;
	    private String telefono;
	    private URL imagen;
	    private List<Mensaje> mensajes;

	    // Constructor
	    public Usuario(String nombre, String telefono, URL imagen) {
	        this.nombre = nombre;
	        this.telefono = telefono;
	        this.imagen = imagen;
	        this.mensajes = new ArrayList<Mensaje>();
	    }

	    // Getters y Setters
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }

	    public URL getImagen() {
	        return imagen;
	    }

	    public void setImagen(URL imagen) {
	        this.imagen = imagen;
	    }

	    public List<Mensaje> getMensajes() {
	        return mensajes;
	    }

	    public void addMensaje(Mensaje mensaje) {
	        this.mensajes.add(mensaje);
	    }
	    
	    public List<Mensaje> getMensajeUsuario(String telefono){
	    	List<Mensaje> mensajesUsuario = new ArrayList<Mensaje>();
	    	for(Mensaje m: mensajes) {
	    		if(m.mensajeChat()==telefono) {
	    			mensajesUsuario.add(m);
	    		}
	    	}
			return mensajes;
	    }
}
