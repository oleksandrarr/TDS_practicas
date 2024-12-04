package dominio;


public class ContactoIndividual extends Contacto {
	private Usuario usuario;
	
	
	public ContactoIndividual(String nombre, Usuario usuario) {
		super(nombre);
		this.usuario = usuario;
		this.tipoContacto = "Individual";
		
	}

	public boolean registrarMensaje(Mensaje mensaje) {
		this.listaMensaje.add(mensaje);
		return true;
		
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	

}
