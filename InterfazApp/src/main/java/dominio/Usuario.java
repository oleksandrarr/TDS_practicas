package dominio;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private String fechaNacimiento;
	private String numeroTelefono;
	private List<Contacto> contactos;
	private LocalDate fechaRegistro;	//para premium
	private URL imagen;
	private boolean premium;


	public Usuario(String nombre, String apellidos, String email, String login,String numeroTelefono, String password,
			String fechaNacimiento, URL imagen) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.numeroTelefono = numeroTelefono;
		this.contactos = new ArrayList<Contacto>();
		this.fechaRegistro = LocalDate.now();
		this.imagen=imagen;
		this.premium= false;
	}
	
	
	public boolean isPremium() {
		return premium;
	}


	public void setPremium(boolean premium) {
		this.premium = premium;
	}


	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(LocalDate fecha) {
	this.fechaRegistro = fecha;
	}
	
	public List<Contacto> getContactos() {
		return contactos;
	}


	public void setContactos(List<Contacto> list) {
		this.contactos = list;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public boolean esContacto(Usuario usuario) { //Método para saber si tiene un usuario registrado para cuando recibe un mensaje
		boolean valor = false;
		for(Contacto c: contactos) {
			if(c instanceof ContactoIndividual) {
				if(((ContactoIndividual) c).getUsuario()==usuario.getId()) {
					valor=true;
				}
			}
		}
		return valor;
	}
	
	public void setContacto(List<Contacto> contactos) {
	
		this.contactos=contactos;
	}
	
	public void añadirContacto(Contacto contacto) {
	    if (contacto != null) {
	    	System.out.println("Se añadeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	        contactos.add(contacto);
	    } else {
	        System.out.println("Intentando añadir un contacto nulo");
	    }
	}


	public ContactoIndividual getContactoIndividual(int id) {
	    for (Contacto c : contactos) {
	        if (c instanceof ContactoIndividual) { // Verifica el tipo antes de hacer el casting
	            ContactoIndividual ci = (ContactoIndividual) c;
	            if (ci.getUsuario() == id) { // Si getUsuario devuelve un int, esto está bien
	                return ci;
	            }
	        }
	    }
	    return null; // Devuelve null si no encuentra el contacto
	}

	public List<Mensaje> encontrarMensajes(String texto, String telefono, String contacto){
		List<Mensaje> mensajesEncontrados = contactos.stream()
			    .flatMap(c -> c.encontrarMensaje(texto, telefono, contacto).stream())
			    .collect(Collectors.toList());
		return mensajesEncontrados;
		
	}


	public URL getImagen() {
		return imagen;
	}


	public void setImagen(URL imagen) {
		this.imagen = imagen;
	}
	
	public int calcularDescuento() {
		int descuento = 0;
		int numMensajesUltMes = (int) contactos.stream().flatMap(cont -> cont.getListaMensaje().stream()).filter(m -> m.getFechaHoraEnvio().getMonthValue() == (LocalDateTime.now().getMonthValue())).count();
		long semanasRegistrado = fechaRegistro.until(LocalDate.now()).toTotalMonths();
		
		if(numMensajesUltMes >= 100) {
			descuento = 15;
		} 
		if(semanasRegistrado >= 4) {
			descuento = 20;
		}
		return descuento;
	}
}
