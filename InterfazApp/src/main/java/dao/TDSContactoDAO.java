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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TDSContactoDAO implements ContactoDAO {

    private static TDSContactoDAO unicaInstancia;
    private ServicioPersistencia servPersistencia;

    private static final String CONTACTO = "Contacto";
    private static final String NOMBRE = "nombre";
    private static final String TIPO_CONTACTO = "tipo";  // Propiedad para determinar el tipo de contacto
    private static final String USUARIO = "usuario";     // ID del usuario
    private static final String CONTACTOS_GRUPO = "contactos";  // Lista de contactos en el grupo
    private static final String MENSAJES = "mensajes";
  
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
			} catch (DAOException e) {
			
				e.printStackTrace();
			}
        }
        return null;  
    }
    
   
    
  
    private Contacto entidadToContacto(Entidad eContacto) throws DAOException {
        
        String nombre = servPersistencia.recuperarPropiedadEntidad(eContacto, NOMBRE);
        String tipoContacto = servPersistencia.recuperarPropiedadEntidad(eContacto, TIPO_CONTACTO);
        
        
     
        if ("Individual".equals(tipoContacto)) {
            int idUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eContacto, USUARIO));
            
            ContactoIndividual contacto = new ContactoIndividual(nombre, idUsuario);
            contacto.setListaMensaje(obtenerMensajes(servPersistencia.recuperarPropiedadEntidad(eContacto,MENSAJES)));
            contacto.setId(eContacto.getId());
            return contacto;
        } 
       
        else if ("Grupo".equals(tipoContacto)) {
            List<Contacto> contactos = obtenerContactosDelGrupo(eContacto);
            Grupo grupo = new Grupo(nombre);
            grupo.setId(eContacto.getId());
            return grupo;
        }
     
        return null;  
    }

    private List<Mensaje> obtenerMensajes(String mensajesIds) throws DAOException {
        List<Mensaje> mensajes = new ArrayList<>();
       
        
        if (mensajesIds != null && !mensajesIds.isEmpty()) {
        	
            String[] idsArray = mensajesIds.split(",");
            for (String id : idsArray) {
                try {
                	System.out.println(id);
                	MensajeDAO mensajeDAO = FactoriaDAO.getInstancia().getMensajeDAO();
                    Mensaje mensaje = mensajeDAO.get(Integer.parseInt(id));  // Recuperar cada mensaje por su ID
                    if (mensaje != null) {
                    	
                        mensajes.add(mensaje);
                    }
                } catch (NumberFormatException e) {
                    // Manejo de error si el ID no es válido
                    System.err.println("Error al convertir el ID de con2tacto: " + id);
                }
            }
        }
        return mensajes;
    }
    
    private List<Contacto> obtenerContactosDelGrupo(Entidad eGrupo) throws DAOException {
        List<Contacto> contactos = new ArrayList<>();
        String contactosIds = servPersistencia.recuperarPropiedadEntidad(eGrupo, CONTACTOS_GRUPO);
        
        if (contactosIds != null && !contactosIds.isEmpty()) {
            String[] idsArray = contactosIds.split(",");
            for (String id : idsArray) {
                try {
                    Contacto contacto = get(Integer.parseInt(id));  // Recuperar cada contacto por su ID
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

        Entidad eContacto = new Entidad();
        eContacto.setNombre(CONTACTO);
        
        StringBuilder mensajesIds = new StringBuilder();
       
        if (contacto.getListaMensaje() != null && !contacto.getListaMensaje().isEmpty()) {
        	  
	        for (Mensaje m : contacto.getListaMensaje()) {
	            mensajesIds.append(m.getId()).append(",");
	        }
	        // Elimina la última coma
	        if (mensajesIds.length() > 0) {
	        	mensajesIds.deleteCharAt(mensajesIds.length() - 1);
	        }
	    }
       
    
        // Establecemos las propiedade del contacto
        //(eContacto, NOMBRE, contacto.getNombre());
     
        // Si el contacto es de tipo 'ContactoIndividual'
        if (contacto instanceof ContactoIndividual) {
        	System.out.println("CREA CONTACTO INDIVIDAK");
        	  eContacto.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
        		        new Propiedad(NOMBRE, contacto.getNombre()),
        		        new Propiedad(USUARIO, String.valueOf(contacto.getId())),
        		        new Propiedad(TIPO_CONTACTO,contacto.getTipoContacto()),
        		        new Propiedad(MENSAJES,mensajesIds.toString())
        		    )));

            
            
        } 
        // Si el contacto es de tipo 'Grupo'
        else if (contacto instanceof Grupo) {
            Grupo grupo = (Grupo) contacto;
            servPersistencia.anadirPropiedadEntidad(eContacto, TIPO_CONTACTO, "Grupo");

            // Guardamos los IDs de los contactos dentro del grupo en una cadena separada por comas
            StringBuilder contactosIds = new StringBuilder();
            for (Contacto c : grupo.getContactos()) {
                if (contactosIds.length() > 0) {
                    contactosIds.append(",");
                }
                contactosIds.append(c.getId());
            }

            // Establecemos esta lista de contactos en la propiedad CONTACTOS_GRUPO
            servPersistencia.anadirPropiedadEntidad(eContacto, CONTACTOS_GRUPO, contactosIds.toString());
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
	    Entidad eContacto = servPersistencia.recuperarEntidad(contacto.getId());
	
	    // Verifica que el usuario tenga contactos
	    StringBuilder MensajesConcatenados = new StringBuilder();
	    for (Mensaje m : contacto.getListaMensaje()) {
	    	
	        if (MensajesConcatenados.length() > 0) {
	            MensajesConcatenados.append(",");
	        }
	        MensajesConcatenados.append(m.getId());
	    }
	    

	    // Actualiza la propiedad 'contactos'
	    for (Propiedad prop : eContacto.getPropiedades()) {
	        if (prop.getNombre().equals(MENSAJES)) {
	        	
	            prop.setValor(MensajesConcatenados.toString());
	            servPersistencia.modificarPropiedad(prop);
	           
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