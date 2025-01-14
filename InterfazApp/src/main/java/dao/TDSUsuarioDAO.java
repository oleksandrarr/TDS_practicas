package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Usuario;
import beans.Entidad;
import beans.Propiedad;
import java.net.URL;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */
public final class TDSUsuarioDAO implements UsuarioDAO {

	private static final String USUARIO = "Usuario";
	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String TELEFONO_USUARIO = "numeroUsuario";
	private static final String CONTACTO = "contactos";
	private static final String IMAGEN = "imagen";
	private static final String PREMIUM = "premium";
	private static final String SALUDO = "saludo";
	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {

		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String numeroUsuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, TELEFONO_USUARIO);
		String contactosIds = servPersistencia.recuperarPropiedadEntidad(eUsuario, CONTACTO);
		String imagen = servPersistencia.recuperarPropiedadEntidad(eUsuario, IMAGEN); // Recuperar la URL
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM); 
		String saludo = servPersistencia.recuperarPropiedadEntidad(eUsuario, SALUDO); 
		URL imagenPerfil = null;
		    try {
		       
		        if (imagen != null && !imagen.isEmpty()) {
		            imagenPerfil = new URL(imagen);
		        }
		    } catch (Exception e) {
		        System.err.println("Error al convertir la URL de la imagen de perfil: " + e.getMessage());
		    }
		Usuario usuario;
		if(saludo!=null) {
			usuario = new Usuario(nombre, apellidos, email, login,numeroUsuario, password, fechaNacimiento,imagenPerfil,saludo);
		}else {
			usuario = new Usuario(nombre, apellidos, email, login,numeroUsuario, password, fechaNacimiento,imagenPerfil);
		}
		usuario.setId(eUsuario.getId());
		usuario.setPremium(Boolean.parseBoolean(premium));
		
		List<Contacto> contactos;
		try {
			contactos = obtenerContactos(contactosIds);
			System.out.println("La lista de contactos"+contactosIds);
			usuario.setContactos(contactos);
			System.out.printf("Al recuperar el usuario '%s' ha encontrado %d contactos. \n",nombre,contactos.size());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	    
		
		return usuario;
	}

	private List<Contacto> obtenerContactos(String contactosIds) throws DAOException {
		
		 List<Contacto> contactos = new ArrayList<>();
		    if (contactosIds != null && !contactosIds.isEmpty()) {

		        String[] idsArray = contactosIds.split(",");
		        for (String id : idsArray) {
		            try {
		            	
		                int contactoId = Integer.parseInt(id.trim()); // Eliminar espacios en blanco
		                Contacto contacto = TDSContactoDAO.getInstance().get(contactoId);
		                if (contacto != null) {
		                    contactos.add(contacto);
		                } else {
		                    System.err.println("Contacto no encontrado para ID: " + contactoId);
		                }
		            } catch (NumberFormatException e) {
		                System.err.println("Error al convertir el ID de contacto: " + id);
		            }
		        }
		    }
		    
		    return contactos;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
	    Entidad eUsuario = new Entidad();
	    eUsuario.setNombre(USUARIO);
	   
	   
	    StringBuilder contactosIds = new StringBuilder();

	    if (usuario.getContactos() != null && !usuario.getContactos().isEmpty()) {
	  
	        for (Contacto contacto : usuario.getContactos()) {
	            contactosIds.append(contacto.getId()).append(",");
	        }
	        // Elimina la última coma
	        if (contactosIds.length() > 0) {
	            contactosIds.deleteCharAt(contactosIds.length() - 1);
	        }
	    }
	    
	    
	    // Registrar las propiedades del usuario
	    eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
	        new Propiedad(NOMBRE, usuario.getNombre()),
	        new Propiedad(APELLIDOS, usuario.getApellidos()),
	        new Propiedad(EMAIL, usuario.getEmail()),
	        new Propiedad(LOGIN, usuario.getLogin()),
	        new Propiedad(TELEFONO_USUARIO, usuario.getNumeroTelefono()),
	        new Propiedad(PASSWORD, usuario.getPassword()),
	        new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento().toString()),
	        new Propiedad(IMAGEN,usuario.getImagen().toString()),
	        new Propiedad(PREMIUM,String.valueOf(usuario.isPremium())),
	        new Propiedad(CONTACTO, contactosIds.toString()),
	        new Propiedad(SALUDO, contactosIds.toString()) // Aquí se registra la propiedad 'contactos'
	    )));

	    return eUsuario;
	}

	public void create(Usuario usuario) {
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
		System.out.println("Propiedades al registrar la entidad Usuario:"); //PARA COMPROBAR LAS PROPIEDADES
		for (Propiedad prop : eUsuario.getPropiedades()) {
		    System.out.printf("Propiedad: %s, Valor: %s\n", prop.getNombre(), prop.getValor());
		}
	}

	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		return servPersistencia.borrarEntidad(eUsuario);
	}

	/**
	 * Permite que un Usuario modifique su perfil: password y email
	 */
	public void update(Usuario usuario) {
	    Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
	
	    // Verifica que el usuario tenga contactos
	    StringBuilder nombresConcatenados = new StringBuilder();
	    for (Contacto contacto : usuario.getContactos()) {
	        if (nombresConcatenados.length() > 0) {
	            nombresConcatenados.append(", ");
	        }
	        nombresConcatenados.append(contacto.getId());
	    }
	    

	    // Actualiza la propiedad 'contactos'
	    for (Propiedad prop : eUsuario.getPropiedades()) {
	        if (prop.getNombre().equals(CONTACTO)) {
	        	
	            prop.setValor(nombresConcatenados.toString());
	           
	           
	        }
	    }
	    
	    // Actualiza todas las demás propiedades
	    for (Propiedad prop : eUsuario.getPropiedades()) {
	        if (prop.getNombre().equals(PASSWORD)) {
	            prop.setValor(usuario.getPassword());
	        } else if (prop.getNombre().equals(EMAIL)) {
	            prop.setValor(usuario.getEmail());
	        } else if (prop.getNombre().equals(NOMBRE)) {
	            prop.setValor(usuario.getNombre());
	        } else if (prop.getNombre().equals(APELLIDOS)) {
	            prop.setValor(usuario.getApellidos());
	        } else if (prop.getNombre().equals(LOGIN)) {
	            prop.setValor(usuario.getLogin());
	        } else if (prop.getNombre().equals(TELEFONO_USUARIO)) {
	            prop.setValor(usuario.getNumeroTelefono());
	        } else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
	            prop.setValor(usuario.getFechaNacimiento());
	        }else if (prop.getNombre().equals(IMAGEN)) {
	            prop.setValor(usuario.getImagen().toString());
	        }else if (prop.getNombre().equals(PREMIUM)) {
	            prop.setValor(String.valueOf(usuario.isPremium()));
	        }else if (prop.getNombre().equals(SALUDO)) {
	            prop.setValor(usuario.getSaludo());
	        }


	        // Guarda la propiedad modificada
	        servPersistencia.modificarPropiedad(prop);
	    }
	}


	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		return entidadToUsuario(eUsuario);
	}
	
	

	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
	}

	

}