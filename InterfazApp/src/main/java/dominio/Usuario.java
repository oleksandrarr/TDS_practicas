package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private String fechaNacimiento;
	private static String numeroTelefono;
	

	public Usuario(String nombre, String apellidos, String email, String login,String numeroTelefono, String password,
			String fechaNacimiento) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.numeroTelefono = numeroTelefono;
		this.mensajes = new ArrayList<Mensaje>();
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
	
	public static void enviarMensajeAGrupo(String texto, String telefonoReceptor, List<Usuario> contactos,LocalDateTime fechaHoraEnvio) {
        for (Usuario receptor : contactos) {
            Mensaje mensaje = new Mensaje(texto, numeroTelefono , receptor.getNumeroTelefono(),  fechaHoraEnvio);
            mensajes.add(mensaje);
        }
        
    }
	
	public static void enviarMensajeAGrupo(int emoticono, String telefonoReceptor, List<Usuario> contactos,LocalDateTime fechaHoraEnvio) {
        for (Usuario receptor : contactos) {
            Mensaje mensaje = new Mensaje(emoticono, numeroTelefono, receptor.getNumeroTelefono(),  fechaHoraEnvio);
            mensajes.add(mensaje);
        }
        
    }
	
	public static void enviarMensaje(String texto,  String telefonoReceptor ,LocalDateTime fechaHoraEnvio) {
        
        Mensaje mensaje = new Mensaje(texto, numeroTelefono,  telefonoReceptor,  fechaHoraEnvio);
        mensajes.add(mensaje);
        
	}


	public static void enviarMensaje(int emoticono,  String telefonoReceptor ,LocalDateTime fechaHoraEnvio) {
	
        Mensaje mensaje = new Mensaje(emoticono,numeroTelefono, telefonoReceptor,  fechaHoraEnvio);
        mensajes.add(mensaje);
}



	public static List<Mensaje> getMensajes() {
		return mensajes;
	}



	public static void setMensajes(List<Mensaje> mensajes) {
		Usuario.mensajes = mensajes;
	}

	
}
