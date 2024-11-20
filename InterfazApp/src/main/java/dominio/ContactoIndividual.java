package dominio;




public class ContactoIndividual extends Contacto {
	private Usuario usuario;
	
	
	public ContactoIndividual(String nombre, Usuario usuario) {
		super(nombre);
		this.usuario = usuario;
		
	}

	public void registrarMensaje(Mensaje mensaje) {
		this.listaMensaje.add(mensaje);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	

}
