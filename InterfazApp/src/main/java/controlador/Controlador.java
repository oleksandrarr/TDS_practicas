package controlador;

import dao.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import dao.DAOException;
import dao.FactoriaDAO;
import dominio.Usuario;
import interfaz.VentanaPrincipal;
import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Grupo;
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
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	public Usuario getUsuarioPorId(int id) {
		return RepositorioUsuarios.INSTANCE.findUsuario(id);
	}
	
	public Contacto getContactoPorId(int contactoId) {
		ContactoDAO c = factoria.getContactoDAO();
		return c.get(contactoId);
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
			String fechaNacimiento,URL imagenPerfil) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, login, telefono,password, fechaNacimiento,imagenPerfil);

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
	
	
	public ContactoIndividual añadirContactoIndividual(String nombre, String telefono) {
		
		 Usuario usuarioContacto = RepositorioUsuarios.INSTANCE.findUsuarioPorTelefono(telefono);
		    if (usuarioContacto == null) {
		        throw new IllegalArgumentException("El usuario con telefono " + telefono+ " no existe.");
		 }
		    if(usuarioActual.getContactoIndividual(usuarioContacto.getId())!=null) {
		    	return null;
		    }
		ContactoIndividual contacto = new ContactoIndividual(nombre, RepositorioUsuarios.INSTANCE.findUsuarioPorTelefono(telefono).getId(),telefono);
		contacto.setImagen(usuarioContacto.getImagen());
		usuarioActual.añadirContacto(contacto);
		ContactoDAO contactoDAO = factoria
				.getContactoDAO(); 
		contactoDAO.create(contacto);
		
		UsuarioDAO usuarioDAO = factoria
				.getUsuarioDAO(); 
		usuarioDAO.update(usuarioActual);
		

		return (ContactoIndividual)contacto;
	}
	
	public ContactoIndividual getContactoIndividual(int codigo) {
		 ContactoIndividual c = usuarioActual.getContactoIndividual(codigo);
		 
		 if (c == null) {
		      
		      return null;  // o lanzar una excepción, dependiendo del caso
		    }
		    
		    return c;
	}
	//Envios de mensaje
	//Enviar Mensaje de tipo texto a Contacto registrado
	public boolean enviarMensaje(Contacto contacto, String texto, int tipo) throws DAOException {
	    // Validaciones iniciales
	    if (usuarioActual == null) {
	        throw new IllegalStateException("No hay un usuario autenticado. Inicie sesión primero.");
	    }

	    if (contacto == null) {
	        throw new IllegalArgumentException("El contacto proporcionado es nulo.");
	    }

	    if (texto == null || texto.isEmpty()) {
	        throw new IllegalArgumentException("El texto del mensaje no puede ser nulo o vacío.");
	    }

	    Mensaje mensaje;
		// Crear el mensaje
	    if(tipo==dominio.Mensaje.ENVIADO) {
	    mensaje = new Mensaje(texto, usuarioActual.getId(), contacto.getId(), LocalDateTime.now(), tipo);
	    }else {
	    mensaje = new Mensaje(texto, contacto.getId(), usuarioActual.getId(), LocalDateTime.now(), tipo);
	    }
	    contacto.registrarMensaje(mensaje);
	    MensajeDAO mensajeDAO = factoria.getMensajeDAO();
	    mensajeDAO.registrar(mensaje);
	    ContactoDAO contactoDAO = factoria.getContactoDAO();
	    contactoDAO.update(contacto);

	    if(contacto instanceof ContactoIndividual) {
		    ContactoIndividual c = (ContactoIndividual) contacto;
		  
		    // Asegurar que el usuario existe
		    Usuario usuarioEncontrado = RepositorioUsuarios.INSTANCE.findUsuario(c.getUsuario());
		    if (usuarioEncontrado == null) {
		        throw new IllegalArgumentException("No se encontró el usuario asociado al contacto.");
		    }
		    
		    UsuarioDAO usuarioDAO = factoria.getUsuarioDAO();
		  
		    if (usuarioEncontrado.getContactoIndividual(usuarioActual.getId()) != null) {
		    	Mensaje mensaje2 ;
		    	if(tipo==Mensaje.ENVIADO) {
		    	 mensaje2 = new Mensaje(texto,usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).getId(),usuarioEncontrado.getId(),
		    			 LocalDateTime.now(), 1);
		    	}else {
		    		 mensaje2 = new Mensaje(texto,usuarioEncontrado.getId(),
				    			usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).getId(), LocalDateTime.now(), 0);
		    	}
		    	usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).registrarMensaje(mensaje2);
		        mensajeDAO.registrar(mensaje2);
		        contactoDAO.update(usuarioEncontrado.getContactoIndividual(usuarioActual.getId()));
		        usuarioDAO.update(usuarioEncontrado);
		        contactoDAO.update(contacto);
		        
		    } else {
		    	
		    	ContactoIndividual contactoUsuarioActual = new ContactoIndividual(usuarioActual.getNumeroTelefono(), 
		    			usuarioActual.getId(),usuarioActual.getNumeroTelefono());
		    	contactoUsuarioActual.setImagen(usuarioActual.getImagen());
		    	contactoDAO.create(contactoUsuarioActual);
		        usuarioEncontrado.añadirContacto(contactoUsuarioActual);
		        Mensaje mensaje2 ;
		    	if(tipo==Mensaje.ENVIADO) {
		    	 mensaje2 = new Mensaje(texto,usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).getId(),usuarioEncontrado.getId(),
		    			 LocalDateTime.now(), 1);
		    	}else {
		    		 mensaje2 = new Mensaje(texto,usuarioEncontrado.getId(),
				    			usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).getId(), LocalDateTime.now(), 0);
		    	}
		        contactoUsuarioActual.registrarMensaje(mensaje2);
		        mensajeDAO.registrar(mensaje2);
		        contactoDAO.update(contactoUsuarioActual);
		        usuarioDAO.update(usuarioEncontrado);
		        contactoDAO.update(contacto);
		    }
	    }else {
	    	Grupo g = (Grupo)contacto;
	    	
	    	for(ContactoIndividual c: g.getContactos()) {
			    enviarMensaje(c,mensaje.getTexto(),tipo);
	    	}
	    	
	    }
	    return true;
	}

	
	public boolean enviarMensajeEmoticono(Contacto contacto, int emoticono, int tipo) throws DAOException {
		// Validaciones iniciales
	    if (usuarioActual == null) {
	        throw new IllegalStateException("No hay un usuario autenticado. Inicie sesión primero.");
	    }

	    if (contacto == null) {
	        throw new IllegalArgumentException("El contacto proporcionado es nulo.");
	    }

	    Mensaje mensaje;
		// Crear el mensaje
	    if(tipo==1) {
	    mensaje = new Mensaje(emoticono, usuarioActual.getId(), contacto.getId(), LocalDateTime.now(), tipo);
	    }else {
	    mensaje = new Mensaje(emoticono, contacto.getId(), usuarioActual.getId(), LocalDateTime.now(), tipo);
	    }
	    contacto.registrarMensaje(mensaje);
	    MensajeDAO mensajeDAO = factoria.getMensajeDAO();
	    mensajeDAO.registrar(mensaje);
	    ContactoDAO contactoDAO = factoria.getContactoDAO();
	    contactoDAO.update(contacto);

	    if(contacto instanceof ContactoIndividual) {
		    ContactoIndividual c = (ContactoIndividual) contacto;
		    // Asegurar que el usuario existe
		    Usuario usuarioEncontrado = RepositorioUsuarios.INSTANCE.findUsuario(c.getUsuario());
		    if (usuarioEncontrado == null) {
		        throw new IllegalArgumentException("No se encontró el usuario asociado al contacto.");
		    }
	
		    
		    UsuarioDAO usuarioDAO = factoria.getUsuarioDAO();
		  
		    if (usuarioEncontrado.getContactoIndividual(usuarioActual.getId()) != null) {
		    	Mensaje mensaje2 ;
		    	if(tipo==Mensaje.ENVIADO) {
		    	 mensaje2 = new Mensaje(emoticono,usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).getId(),usuarioEncontrado.getId(),
		    			 LocalDateTime.now(), 1);
		    	}else {
		    		 mensaje2 = new Mensaje(emoticono,usuarioEncontrado.getId(),
				    			usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).getId(), LocalDateTime.now(), 1);
		    	}
		    	usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).registrarMensaje(mensaje2);
		        mensajeDAO.registrar(mensaje2);
		        contactoDAO.update(usuarioEncontrado.getContactoIndividual(usuarioActual.getId()));
		        usuarioDAO.update(usuarioEncontrado);
		        contactoDAO.update(contacto);
		        
		    } else {
		    	
		    	ContactoIndividual contactoUsuarioActual = new ContactoIndividual(usuarioActual.getNumeroTelefono(), 
		    			usuarioActual.getId(),usuarioActual.getNumeroTelefono());
		    	contactoUsuarioActual.setImagen(usuarioActual.getImagen());
		    	contactoDAO.create(contactoUsuarioActual);
		        usuarioEncontrado.añadirContacto(contactoUsuarioActual);
		        Mensaje mensaje2 ;
		    	if(tipo==Mensaje.ENVIADO) {
		    	 mensaje2 = new Mensaje(emoticono,usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).getId(),usuarioEncontrado.getId(),
		    			 LocalDateTime.now(), 1);
		    	}else {
		    		 mensaje2 = new Mensaje(emoticono,usuarioEncontrado.getId(),
				    			usuarioEncontrado.getContactoIndividual(usuarioActual.getId()).getId(), LocalDateTime.now(), 1);
		    	}
		        contactoUsuarioActual.registrarMensaje(mensaje2);
		        mensajeDAO.registrar(mensaje2);
		        contactoDAO.update(contactoUsuarioActual);
		        usuarioDAO.update(usuarioEncontrado);
		        contactoDAO.update(contacto);
		    }
	    }else {
	    	Grupo g = (Grupo)contacto;
	    	
	    	for(ContactoIndividual c: g.getContactos()) {
			    enviarMensajeEmoticono(c,mensaje.getEmoticono(),tipo);
	    	}
	    }
	    

		try {
			VentanaPrincipal.getInstance().actualizarListaContactos();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	    
	    return true;
	}
	
	public List<Mensaje> obtenerMensajes(int i) {
		 return getContactoPorId(i).getListaMensaje();
    }

	public List<Contacto> obtenerContactos(){
		return factoria.getContactoDAO().getAll();
	}
	

	public ContactoIndividual getContactoPorTelefono(String telefono) {
		for(Contacto c: usuarioActual.getContactos()) {
			if(c instanceof ContactoIndividual && ((ContactoIndividual)c).getNumeroTelefono().equals(telefono)) {
				return (ContactoIndividual)c;
			}
		}
		
		return null;
	}

	public void modificarContacto(String nombre, String tel) {
		ContactoIndividual contacto = getContactoPorTelefono(tel);
		contacto.setNombreOptiona(nombre);
		ContactoDAO contactoDAO = factoria.getContactoDAO();
		contactoDAO.update(contacto);
		
	}

	public void añadirGrupo(List<ContactoIndividual> contactos,String nombre,URL foto) {
		
		Grupo grupo = new Grupo(nombre);
		grupo.setNombre(nombre);
		grupo.setContactos(contactos);
		grupo.setImagen(foto);
		
		usuarioActual.añadirContacto(grupo);
		ContactoDAO contactoDAO = factoria
				.getContactoDAO(); 
		contactoDAO.create(grupo);
		
		UsuarioDAO usuarioDAO = factoria
				.getUsuarioDAO(); 
		usuarioDAO.update(usuarioActual);
		

	}
	
	public List<Mensaje> buscarMensaje(String texto,String telefono, String contacto){
		List<Mensaje> mensajesEncontrados = new ArrayList<>();
		int idContacto=0;
		if(!telefono.isEmpty() && !telefono.equals(usuarioActual.getNumeroTelefono())){
			 idContacto = 0; //Ya tengo el contacto
		}else if(!telefono.isEmpty()){
			idContacto=getContactoPorTelefono(telefono).getId();
		}
		
		
		mensajesEncontrados=usuarioActual.encontrarMensajes(texto, idContacto, contacto);
		return mensajesEncontrados;
		
	}
	
	public String generarPDF() {
	    try {
	        String rutaArchivo = "Contactos_de_" + usuarioActual.getNombre() + ".pdf";
	        PdfWriter writer = new PdfWriter(rutaArchivo);
	        PdfDocument pdf = new PdfDocument(writer);
	        Document document = new Document(pdf);

	        // Contactos individuales
	        Paragraph tituloIndividuales = new Paragraph("Contactos Individuales").setBold();
	        document.add(tituloIndividuales);

	        for (ContactoIndividual ci : usuarioActual.getContactosIndividuales()) {
	            Paragraph contactoInfo = new Paragraph("Nombre: " + ci.getNombre() + ", Teléfono: " + ci.getNumeroTelefono());
	            document.add(contactoInfo);
	        }

	        // Grupos
	        Paragraph tituloGrupos = new Paragraph("Grupos").setBold();
	        document.add(tituloGrupos);

	        for (Grupo g : usuarioActual.getGrupos()) {
	            Paragraph grupoInfo = new Paragraph("Grupo: " + g.getNombre());
	            document.add(grupoInfo);

	            for (ContactoIndividual ci : g.getContactos()) {
	                Paragraph miembroInfo = new Paragraph("  - Nombre: " + ci.getNombre() + ", Teléfono: " + ci.getNumeroTelefono());
	                document.add(miembroInfo);
	            }
	        }

	        document.close();
	        return rutaArchivo;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}

}
