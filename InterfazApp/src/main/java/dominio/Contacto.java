package dominio;

import java.util.ArrayList;
import java.util.List;

import controlador.Controlador;

public abstract class Contacto {
	protected int id;
	protected String nombre;
	protected List<Mensaje> listaMensaje;
	protected String tipoContacto;
	protected String imagen;
	 

	    public String getTipoContacto() {
		return tipoContacto;
	}
	    
	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

		public Contacto(String nombre) {
	        this.nombre = nombre;
	        this.listaMensaje = new ArrayList<Mensaje>();
	        this.imagen = Controlador.INSTANCE.getContactoIndividual(id).getImagen();
	        this.id = 0;
	    }

	    public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}

	    public String getNombre() {
			return nombre;
		}


		public List<Mensaje> getListaMensaje() {
			return listaMensaje;
		}
		
		public String toString() {
			return nombre;
		}
		


		public abstract boolean registrarMensaje(Mensaje mensaje);

		public String getImagen() {
			return imagen;
		}
}
