package dominio;

import java.util.ArrayList;
import java.util.List;

public class Grupo extends Contacto{
	
	private List<ContactoIndividual> integrantes;
	
	public Grupo(String nombre) {
		super(nombre);
		this.integrantes = new ArrayList<ContactoIndividual>();
		this.tipoContacto = "Grupo";
	}

	@Override
	public boolean registrarMensaje(Mensaje mensaje) {
		this.listaMensaje.add(mensaje);
		for(ContactoIndividual c: integrantes) { //Recorre todos los contactos individuales del grupo y les envia un mensaje
			c.registrarMensaje(mensaje);
		}
		return true;
	}

	public List<ContactoIndividual> getContactos() {
		return integrantes;
	}

	public void setContactos(List<ContactoIndividual> contactos) {
		this.integrantes = contactos;
		
	}

}
