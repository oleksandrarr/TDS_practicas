package dominio;

import java.util.ArrayList;
import java.util.List;

public class Grupo extends Contacto{
	
	private List<ContactoIndividual> integrantes;
	
	public Grupo(String nombre) {
		super(nombre);
		this.integrantes = new ArrayList<ContactoIndividual>();
	}

	@Override
	public boolean registrarMensaje(Mensaje mensaje) {
		this.listaMensaje.add(mensaje);
		for(ContactoIndividual u: integrantes) { //Recorre todos los contactos individuales del grupo y les envia un mensaje
			u.registrarMensaje(mensaje);
		}
		return true;
	}

	public List<ContactoIndividual> getContactos() {
		return integrantes;
	}

}
