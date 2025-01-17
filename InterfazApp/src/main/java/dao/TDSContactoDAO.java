package dao;

import tds.driver.FactoriaServicioPersistencia;
import dominio.Mensaje;
import tds.driver.ServicioPersistencia;
import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Grupo;
import dominio.Usuario;
import beans.Entidad;
import beans.Propiedad;
import dao.UsuarioDAO;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class TDSContactoDAO implements ContactoDAO {

    private static TDSContactoDAO unicaInstancia;
    private ServicioPersistencia servPersistencia;

    private static final String CONTACTO = "Contacto";
    private static final String NOMBRE = "nombre";
    private static final String TIPO_CONTACTO = "tipo";  // Propiedad para determinar el tipo de contacto
    private static final String USUARIO = "usuario";     // ID del usuario
    private static final String CONTACTOS_GRUPO = "contactos";  // Lista de contactos en el grupo
    private static final String MENSAJES = "mensajes";
    private static final String TELEFONO = "numeroTelefono";
    private static final String IMAGEN = "imagen";
    public TDSContactoDAO() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }


    public static TDSContactoDAO getInstance() {
        if (unicaInstancia == null) {
            unicaInstancia = new TDSContactoDAO();
        }
        return unicaInstancia;
    }

    
    public Contacto get(int id) {
        Entidad eContacto = servPersistencia.recuperarEntidad(id);
        if (eContacto != null) {
            try {
				return entidadToContacto(eContacto);
			} catch (DAOException | MalformedURLException e) {
			
				e.printStackTrace();
			}
        }
        return null;  
    }
    
   
    
  
    private Contacto entidadToContacto(Entidad eContacto) throws DAOException, MalformedURLException {
        
        String nombre = servPersistencia.recuperarPropiedadEntidad(eContacto, NOMBRE);
        String tipoContacto = servPersistencia.recuperarPropiedadEntidad(eContacto, TIPO_CONTACTO);
        String imagen = servPersistencia.recuperarPropiedadEntidad(eContacto, IMAGEN);
        
     
        if ("Individual".equals(tipoContacto)) {
        	
            int idUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eContacto, USUARIO));
            String numeroTelefono = servPersistencia.recuperarPropiedadEntidad(eContacto, TELEFONO);
            ContactoIndividual contacto = new ContactoIndividual(Optional.ofNullable(nombre).orElse(null), idUsuario,numeroTelefono);
            contacto.setListaMensaje(obtenerMensajes(servPersistencia.recuperarPropiedadEntidad(eContacto,MENSAJES)));
            contacto.setId(eContacto.getId());
            contacto.setImagen(new URL(imagen));
            return contacto;
        } 
       
        else if ("Grupo".equals(tipoContacto)) {
        	String contactosIds = servPersistencia.recuperarPropiedadEntidad(eContacto, CONTACTOS_GRUPO);
            List<ContactoIndividual> contactos = obtenerContactosDelGrupo(eContacto);
            Grupo grupo = new Grupo(nombre);
            grupo.setContactos(contactos);
            String mensajesIds = servPersistencia.recuperarPropiedadEntidad(eContacto, MENSAJES);
            grupo.setListaMensaje(obtenerMensajes(mensajesIds));
            grupo.setId(eContacto.getId());
            grupo.setImagen(new URL(imagen));
            return grupo;
        }
     
        return null;  
    }

    private List<Mensaje> obtenerMensajes(String mensajesIds) throws DAOException {
        List<Mensaje> mensajes = new ArrayList<>();
       
        
        if (mensajesIds != null && !mensajesIds.isEmpty()) {
            String[] idsArray = mensajesIds.split(",");
            for (String id : idsArray) {
                if (id != null && !id.trim().isEmpty()) {  // Ignorar IDs vacíos
                    try {
                    	int idNumerico = Integer.parseInt(id.trim()); // Convertir ID a entero
                        if (idNumerico != 0) { // Verificar que no sea 0
	                        MensajeDAO mensajeDAO = FactoriaDAO.getInstancia().getMensajeDAO();
	                        Mensaje mensaje = mensajeDAO.get(Integer.parseInt(id.trim())); // Convertir ID
	                        if (mensaje != null) {
	                            mensajes.add(mensaje);
	                        }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir el ID del mensaje: " + id);
                    }
                }
            }
        }

        return mensajes;
    }
    
    private List<ContactoIndividual> obtenerContactosDelGrupo(Entidad eGrupo) throws DAOException {
        List<ContactoIndividual> contactos = new ArrayList<>();
        String contactosIds = servPersistencia.recuperarPropiedadEntidad(eGrupo, CONTACTOS_GRUPO);
      
        if (contactosIds != null && !contactosIds.isEmpty()) {
            String[] idsArray = contactosIds.split(",");
            for (String id : idsArray) {
                try {
                    ContactoIndividual contacto = (ContactoIndividual) get(Integer.parseInt(id));  // Recuperar cada contacto por su ID
                    if (contacto != null) {
                    	
                        contactos.add(contacto);
                    }
                } catch (NumberFormatException e) {
                    // Manejo de error si el ID no es válido
                    System.err.println("Error al convertir el ID de contacto: " + id);
                }
            }
        }
        return contactos;
    }
    
    private Entidad contactoToEntidad(Contacto contacto) throws DAOException {
    	StringBuilder mensajesIds = new StringBuilder();
        Entidad eContacto = new Entidad();
        eContacto.setNombre(CONTACTO);
    
        if (contacto.getListaMensaje() != null && !contacto.getListaMensaje().isEmpty()) {
        	  
	        for (Mensaje m : contacto.getListaMensaje()) {
	            mensajesIds.append(m.getId()).append(",");
	        }
	        // Elimina la última coma
	        if (mensajesIds.length() > 0) {
	        	mensajesIds.deleteCharAt(mensajesIds.length() - 1);
	        }
	        
	        StringBuilder contactosIds = new StringBuilder();
	        for (Contacto c : ((Grupo) contacto).getContactos()) {
	            if (contactosIds.length() > 0) {
	                contactosIds.append(",");
	            }
	            contactosIds.append(c.getId());
	        }
	    }
       
    
        // Establecemos las propiedade del contacto
        //(eContacto, NOMBRE, contacto.getNombre());
     
        // Si el contacto es de tipo 'ContactoIndividual'
        if (contacto instanceof ContactoIndividual) {
        	
        	  eContacto.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
        		        new Propiedad(NOMBRE, ((ContactoIndividual)contacto).getNombreOptional().orElse(null)),
        		        new Propiedad(USUARIO, String.valueOf(((ContactoIndividual) contacto).getUsuario())),
        		        new Propiedad(TIPO_CONTACTO,contacto.getTipoContacto()),
        		        new Propiedad(MENSAJES,mensajesIds.toString()),
        		        new Propiedad(IMAGEN,contacto.getImagen().toString()),
        		        new Propiedad(TELEFONO, ((ContactoIndividual)contacto).getNumeroTelefono())
        		    )));

            
            
        } 
        // Si el contacto es de tipo Grupo
        else if (contacto instanceof Grupo) {
            StringBuilder contactosIds = new StringBuilder();
            for (Contacto c : ((Grupo)contacto).getContactos()) {
                if (contactosIds.length() > 0) {
                    contactosIds.append(",");
                }
                contactosIds.append(c.getId());
            }
    
            eContacto.setPropiedades(new ArrayList<>(Arrays.asList(
                    new Propiedad(NOMBRE, contacto.getNombre()),
                    new Propiedad(TIPO_CONTACTO, "Grupo"),
                    new Propiedad(MENSAJES,mensajesIds.toString()),
                    new Propiedad(IMAGEN,contacto.getImagen().toString()),
                    new Propiedad(CONTACTOS_GRUPO, contactosIds.toString())
                )));
        }
        
        
     
        // Devolvemos la entidad preparada para ser persistida
         
        
        return eContacto;
    }
   
    @Override
    public void create(Contacto contacto) {
    	
		try {
			Entidad eContacto = this.contactoToEntidad(contacto);
			eContacto = servPersistencia.registrarEntidad(eContacto);
			contacto.setId(eContacto.getId());
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
	
	}

	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		return servPersistencia.borrarEntidad(eUsuario);
    }


    @Override
    public boolean delete(Contacto contacto) {
        try {
            // Recuperamos la entidad de contacto utilizando su ID
            Entidad eContacto = servPersistencia.recuperarEntidad(contacto.getId());
            if (eContacto != null) {
                // Si existe, eliminamos la entidad
                servPersistencia.borrarEntidad(eContacto);
                return true; // Eliminación exitosa
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Si no se pudo eliminar
    }
    
    public void update(Contacto contacto) {
        // Recupera la entidad asociada al contacto
        Entidad eContacto = servPersistencia.recuperarEntidad(contacto.getId());

        // Actualizar la lista de mensajes
        String mensajesExistentes = servPersistencia.recuperarPropiedadEntidad(eContacto, MENSAJES);
        List<String> listaMensajesExistentes = new ArrayList<>();

        if (mensajesExistentes != null && !mensajesExistentes.isEmpty()) {
            listaMensajesExistentes.addAll(List.of(mensajesExistentes.split(",")));
        }

        // Agregar nuevos mensajes si no están ya en la lista
        if(contacto.getListaMensaje()!=null) {
	        for (Mensaje m : contacto.getListaMensaje()) {
	            String idMensaje = String.valueOf(m.getId());
	            if (!listaMensajesExistentes.contains(idMensaje)) {
	                listaMensajesExistentes.add(idMensaje);
	            }
	        }
        }

        
        String mensajesConcatenados = String.join(",", listaMensajesExistentes);

        // Actualizar las propiedades de la entidad
        for (Propiedad prop : eContacto.getPropiedades()) {
            // Actualizar mensajes
            if (prop.getNombre().equals(MENSAJES)) {
                prop.setValor(mensajesConcatenados);
                servPersistencia.modificarPropiedad(prop);
            }
            if (prop.getNombre().equals(IMAGEN)) {
                prop.setValor(contacto.getImagen().toString());
                servPersistencia.modificarPropiedad(prop);
            }

            // Actualizar el nombre (sin modificar el teléfono)
            if (contacto instanceof ContactoIndividual) {
                ContactoIndividual contactoInd = (ContactoIndividual) contacto;

                if (prop.getNombre().equals(NOMBRE)) {
                    // Actualizar el nombre si existe
                    String nuevoNombre = contactoInd.getNombreOptional().orElse("");
                    if (!nuevoNombre.isEmpty()) {
                        prop.setValor(nuevoNombre);
                        servPersistencia.modificarPropiedad(prop);
                    }
                }
            }else {
            	 Grupo contactoGru = (Grupo) contacto;
            	 StringBuilder contactosIds = new StringBuilder();
                 for (Contacto c : ((Grupo)contacto).getContactos()) {
                     if (contactosIds.length() > 0) {
                         contactosIds.append(",");
                     }
                     contactosIds.append(c.getId());
                 }
            	  
                 if (prop.getNombre().equals(NOMBRE)) {
                     // Actualizar el nombre si existe
                         prop.setValor(contactoGru.getNombre());
                         servPersistencia.modificarPropiedad(prop);
                 }
                 if(prop.getNombre().equals(CONTACTOS_GRUPO)) {
                	 prop.setValor(contactosIds.toString());
                	 servPersistencia.modificarPropiedad(prop);
                 }
            }
            
        }
    }




    @Override
    public List<Contacto> getAll() {
        List<Contacto> contactos = new ArrayList<>();
        try {
        
            List<Entidad> entidades = servPersistencia.recuperarEntidades(CONTACTO);
            
        
            for (Entidad eContacto : entidades) {
                Contacto contacto = entidadToContacto(eContacto);
                if (contacto != null) {
                    contactos.add(contacto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactos;
    }
    
 
    
}