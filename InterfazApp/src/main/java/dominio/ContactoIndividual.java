package dominio;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controlador.Controlador;
import dao.FactoriaDAO;

public class ContactoIndividual extends Contacto {
	private int usuario;
	private String numeroTelefono;
	private Optional<String> nombre;
	
	public ContactoIndividual(String nombre, int idUsuario,String numeroTelefono) {
		super(nombre);
		this.usuario = idUsuario;
		this.tipoContacto = "Individual";
		this.numeroTelefono=numeroTelefono;
		this.nombre = Optional.ofNullable(nombre).or(() -> Optional.of(numeroTelefono));
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
	public URL getImagen() {
		return this.imagen;
	}

	public void setImagen(URL imagen) {
		this.imagen=imagen;
	}
	
	@Override
	public String toString() {
		return this.getNombreOptional().orElse(numeroTelefono);
	}

}
