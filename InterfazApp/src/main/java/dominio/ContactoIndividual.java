package dominio;

import java.util.List;

public class ContactoIndividual extends Contacto {
	private int usuario;
	
	
	public ContactoIndividual(String nombre, int idUsuario) {
		super(nombre);
		this.usuario = idUsuario;
		this.tipoContacto = "Individual";
		
	}

	

	public int getUsuario() {
		return this.usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	
	
	
	

}
