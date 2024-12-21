package controlador;

import dao.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import dao.DAOException;
import dao.FactoriaDAO;
import dominio.Usuario;
import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Mensaje;
import dominio.RepositorioUsuarios;

public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	private FactoriaDAO factoria;

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		ContactoDAO contactoDAO = factoria.getContactoDAO();
		List<Contacto> contactos = contactoDAO.getAll();
		System.out.println("Longitud "+contactos.size());
		//System.exit( 0);
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	public Usuario getUsuarioPorId(int id) {
		return RepositorioUsuarios.INSTANCE.findUsuario(id);
	}
	
	public Contacto getContactoPorId(int id) {
		ContactoDAO contacto = null;
		return contacto.get(id);
	}

	public boolean esUsuarioRegistrado(String login) {
		return RepositorioUsuarios.INSTANCE.findUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = RepositorioUsuarios.INSTANCE.findUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
		
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String telefono, String password,
			String fechaNacimiento) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, login, telefono,password, fechaNacimiento);

		UsuarioDAO usuarioDAO = factoria
				.getUsuarioDAO(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		usuarioDAO.create(usuario);
		
		RepositorioUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.delete(usuario);

		RepositorioUsuarios.INSTANCE.removeUsuario(usuario);
		return true;
	}
	
	/**
	 * 
	 * CONTACTOS
	 */
	
	public void añadirContacto(String nombre, String telefono) {
		
	}
	public ContactoIndividual añadirContactoIndividual(String nombre, String telefono) {
		
		 Usuario usuarioContacto = RepositorioUsuarios.INSTANCE.findUsuarioPorTelefono(telefono);
		    if (usuarioContacto == null) {
		        throw new IllegalArgumentException("El usuario con telefono " + telefono+ " no existe.");
		 }
		ContactoIndividual contacto = new ContactoIndividual(nombre, RepositorioUsuarios.INSTANCE.findUsuarioPorTelefono(telefono).getId());
		
		usuarioActual.añadirContacto(contacto);
		ContactoDAO contactoDAO = factoria
				.getContactoDAO(); 
		contactoDAO.create(contacto);
		
		UsuarioDAO usuarioDAO = factoria
				.getUsuarioDAO(); 
		usuarioDAO.update(usuarioActual);
		System.out.println("añade uncontacot indi \n");

		return (ContactoIndividual)contacto;
	}
	
	public ContactoIndividual getContactoIndividual(int codigo) {
		 System.out.printf("Usuario actual: %s \n", usuarioActual.getLogin());
		 ContactoIndividual c = usuarioActual.getContactoIndividual(codigo);
		 
		 if (c == null) {
		      
		      return null;  // o lanzar una excepción, dependiendo del caso
		    }
		    
		    return c;
	}
	//Envios de mensaje
	//Enviar Mensaje de tipo texto a Contacto registrado
	public boolean enviarMensaje(Contacto contacto, String texto, int tipo) {
		Mensaje mensaje = new Mensaje(texto, usuarioActual.getId(), contacto.getId(),LocalDateTime.now(),tipo );
		contacto.registrarMensaje(mensaje);
		MensajeDAO mensajeDAO = factoria
			.getMensajeDAO(); 
		mensajeDAO.registrar(mensaje);
		ContactoDAO contactoDAO = factoria.getContactoDAO();
		contactoDAO.update(contacto);
		
		int tipo2;
		if (tipo==1)
			tipo2 = 0;
		else
			tipo2 = 1;
		Mensaje mensaje2 = new Mensaje(texto, usuarioActual.getId(), contacto.getId(),LocalDateTime.now(), tipo2);
		ContactoIndividual c = (ContactoIndividual)contacto;
		ContactoIndividual contactoUsuarioActual = new ContactoIndividual(usuarioActual.getNumeroTelefono(),usuarioActual.getId());
		RepositorioUsuarios.INSTANCE.findUsuario(c.getUsuario()).añadirContacto(contactoUsuarioActual);
		contactoDAO.create( contactoUsuarioActual);
		System.out.println("823478942347834"+RepositorioUsuarios.INSTANCE.findUsuario(c.getUsuario()).getContactos().size());
		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO();
		usuarioDAO.update(RepositorioUsuarios.INSTANCE.findUsuario(c.getUsuario()));
		if(RepositorioUsuarios.INSTANCE.findUsuario(c.getUsuario()).getContactoIndividual(usuarioActual.getId()) !=null) {
			System.out.println("ENTRA////////////////////////////");
			contactoUsuarioActual.registrarMensaje(mensaje2);
			mensajeDAO.registrar(mensaje2);
			System.out.println("contacoofaefr"+contactoUsuarioActual.getId());
			contactoDAO.update(contactoUsuarioActual);
			
		}
		
		return true;
	}
	
	
	//Enviar Mensaje de tipo emoticono a Contacto registrado
	public boolean enviarMensaje(Contacto contacto, int emoticono,int tipo) {
		Mensaje mensaje = new Mensaje(emoticono, usuarioActual.getId(), contacto.getId(),LocalDateTime.now(),tipo );
		contacto.registrarMensaje(mensaje);
		return true;
	}
	
	//Enviar Mensaje de tipo texto a número de teléfono
	/*
	public boolean enviarMensaje(String telefonoContacto, String texto,int tipo) {
		System.out.println("Entra al metod2222o//////////////");
		Mensaje mensaje = new Mensaje(texto, usuarioActual, telefonoContacto,LocalDateTime.now(),tipo );
		return true;
	}
	
	//Enviar Mensaje de tipo emoticono a número de teléfono
	public boolean enviarMensaje(String telefonoContacto, int emoticono,int tipo) {
		Mensaje mensaje = new Mensaje(emoticono, usuarioActual, telefonoContacto,LocalDateTime.now(),tipo );
		return true;
	}
	*/
	public List<Mensaje> obtenerMensajes(Contacto contacto) {
		 if (contacto == null) {
		        throw new IllegalArgumentException("El contacto proporcionado no existe");
		    }
		    return contacto.getListaMensaje();
    }

	public List<Contacto> obtenerContactos(){
		return factoria.getContactoDAO().getAll();
	}


	
}
