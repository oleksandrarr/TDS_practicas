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
	 
	
	
	//Constructor de la clase
	public Contacto(String nombre) {
	        this.nombre = nombre;
	        this.listaMensaje = new ArrayList<Mensaje>();
	        this.id = 0;
	    }
	
	
	//Devuelve el id con el que se distingue el contacto en la persistencia
     public int getId() {
			return id;
    }
	    
	//Devuelve el tipo de contacto (Grupo o individual)
	public String getTipoContacto() {
		return tipoContacto;
	}
	 
	//Para definir el tipo de contacto
	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
		
	}

	//Cuando recuperamos el contacto persistido hay que añadirle los mensajes
	public void setListaMensaje(List<Mensaje> listaMensaje) {
			this.listaMensaje = listaMensaje;
	}
    
	//Cuando se crea el objeto en la base de datos le asignamos el id que nos da 
	//el servicio de persistencia
	public void setId(int id) {
			this.id = id;
	}

	    

    //Devuelve la lista de mensajes que tenemos con el contacto
	public List<Mensaje> getListaMensaje() {
	        return listaMensaje;
    }
	//Metodo que devuelve el ultimo mensaje si este existe
	//Este metodo es el que utilizamos en la lista de chats recientes
	public Optional<Mensaje> getUltimoMensaje(){
	    	if(listaMensaje == null || listaMensaje.isEmpty()) {
	    		
	    		return Optional.empty();
	    	}else {
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
			this.listaMensaje.add(mensaje);
			return true;
		}
		
		public abstract URL getImagen();
	
		public abstract String toString();
	
		
		
}
