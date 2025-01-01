package dominio;

import java.util.List;
import java.util.Optional;

public class ContactoIndividual extends Contacto {
	private int usuario;
	private String numeroTelefono;
	private Optional<String> nombre;
	
	public ContactoIndividual(String nombre, int idUsuario,String numeroTelefono) {
		super(nombre);
		this.usuario = idUsuario;
		this.tipoContacto = "Individual";
		this.numeroTelefono=numeroTelefono;
        this.nombre = Optional.ofNullable(nombre);
		
		
	}

	

	public String getNumeroTelefono() {
		return numeroTelefono;
	}



	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}



	public int getUsuario() {
		return this.usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	
	public Optional<String> getNombreOptional() {
        return nombre; 
    }

	public void setNombreOptiona(String nombre) {
		this.nombre=Optional.of(nombre);
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
