package dominio;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Grupo extends Contacto{
	
	private List<ContactoIndividual> integrantes;
	private URL imagen;
	
	public Grupo(String nombre) {
		super(nombre != null ? nombre : "Sin nombre"); // Valor por defecto si es nulo
	    if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El nombre del grupo no puede estar vacío.");
	    }
		this.integrantes = new ArrayList<ContactoIndividual>();
		this.tipoContacto = "Grupo";
	}

	

	public List<ContactoIndividual> getContactos() {
		return integrantes;
	}

	public void setContactos(List<ContactoIndividual> contactos) {
		this.integrantes = contactos;
		
	}

	@Override
	public URL getImagen() {
		
		return this.imagen;
	}

	public void setImagen(URL imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
	
}
