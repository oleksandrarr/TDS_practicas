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
	        return listaMensaje;
	    }
	    
	    public Optional<Mensaje> getUltimoMensaje(){
	    	if(listaMensaje == null || listaMensaje.isEmpty()) {
	    		
	    		return Optional.empty();
	    	}else {
	    		System.out.println("Ultimo Mensaje de "+this.getNombre()+" es " + this.getListaMensaje().getLast().getTexto());
	    		return Optional.of(listaMensaje.getLast());
	    	}
	    }
		
		

		
		public String getNombre() {
		        return nombre; 
		}
		
		public void setNombre(String nombre) {
			this.nombre=nombre;
		}
		
		//Metodo para buscar mensajes en el contacto
		public List<Mensaje> encontrarMensaje(String texto){
		    List<Mensaje> mensajesEncontrados = new ArrayList<>();
			for(Mensaje m: this.listaMensaje) {
				if(m.encontrarMensaje(texto)==true) {
					mensajesEncontrados.add(m);
				}
			}
			return mensajesEncontrados;
		}
		
		public boolean registrarMensaje(Mensaje mensaje) {
			return this.listaMensaje.add(mensaje);
		}
		
		public abstract URL getImagen();
	
		public abstract String toString();
	
		
		
}
