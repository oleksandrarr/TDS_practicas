package dominio;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
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
	private String saludo;

	//Como el saludo es opcional, decidimos tener dos constructores
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
		this.saludo = null;
	}
	
	public Usuario(String nombre, String apellidos, String email, String login,String numeroTelefono, String password,
			String fechaNacimiento, URL imagen, String saludo) {
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
		this.saludo = saludo;
	}
	
	public boolean isPremium() {
		return premium;
	}


	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public String getSaludo() {
		return saludo;
	}
	
	public void setSaludo(String saludo) {
		this.saludo = saludo;
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
	//Devuelve todos los contactos del usuario, de ambos tipos
	public List<ContactoIndividual> getContactosIndividuales(){
		List<ContactoIndividual> lista = new LinkedList<ContactoIndividual>();
		for(Contacto c : contactos) {
			if(c instanceof ContactoIndividual) {
				lista.add((ContactoIndividual) c);
			}
		}
		return lista;
	}
	//Devuelve los grupos del contacto
	public List<Grupo> getGrupos(){
		List<Grupo> lista = new LinkedList<Grupo>();
		for(Contacto c : contactos) {
			if(c instanceof Grupo) {
				lista.add((Grupo) c);
			}
		}
		return lista;
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
	        contactos.add(contacto);
	    }
	}

	public void eliminarContacto(Contacto contacto) {
		contactos.remove(contacto);
	}
    //Encuentra un contacto por su id
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
	//Metodo que encuentra los mensajes que se estan buscando 
	//El usuario tiene todos los contactos que a su vez tienen todos los mensajes
	public List<Mensaje> encontrarMensajes(String texto, int idContacto, String contacto){
		System.out.println("arer(//"+idContacto+"//"+contacto);
		List<Mensaje> mensajesEncontrados= new ArrayList<>();
		for(Contacto c: contactos) {
			if(c instanceof ContactoIndividual) {
				if(idContacto==0 && contacto.isEmpty()) {//Si no hay contacto y telefono
					mensajesEncontrados.addAll(c.encontrarMensaje(texto));
				}else if(idContacto==0 && (c.getNombre().equals(contacto)||contacto.isEmpty())) {//El telefono no esta o es igual al telefono del usario y el contacto no esta o es igual al contacto
					mensajesEncontrados.addAll(c.encontrarMensaje(texto));
				}else if(c.getId()==idContacto || c.getNombre().equals(contacto) || contacto.equals(((ContactoIndividual)c).getNumeroTelefono())) {
					mensajesEncontrados.addAll(c.encontrarMensaje(texto));
				}
			}else {
				if(c.getNombre().equals(contacto)) {
					mensajesEncontrados.addAll(c.encontrarMensaje(texto));
				}
			}
		}
		return mensajesEncontrados;
		
	}


	public URL getImagen() {
		return imagen;
	}


	public void setImagen(URL imagen) {
		this.imagen = imagen;
	}
	
    
	public double calcularDescuento() {
		double descuento = 0;
		int numMensajesUltMes = (int) contactos.stream().flatMap(cont -> cont.getListaMensaje().stream()).filter(m -> m.getFechaHoraEnvio().getMonthValue() == (LocalDateTime.now().getMonthValue())).count();
		long semanasRegistrado = fechaRegistro.until(LocalDate.now()).toTotalMonths();
		
		if(numMensajesUltMes >= 100) {
			descuento =  0.15;
		} 
		if(semanasRegistrado >= 4) {
			descuento = 0.20;
		}
		System.out.println(descuento);
		return descuento;
	}
	
	public int getMensajesEnviados() {
		int mes = LocalDateTime.now().getMonthValue();
		int mensajes = (int) contactos.stream().flatMap(c -> c.getListaMensaje().stream()).filter(m -> m.getFechaHoraEnvio().getMonthValue() == (mes)).count();
		return mensajes;
	}
}
