package dao;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Grupo;
import dominio.Usuario;
import beans.Entidad;
import dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;

public final class TDSContactoDAO implements ContactoDAO {

    private static TDSContactoDAO instancia;
    private ServicioPersistencia servPersistencia;

    private static final String CONTACTO = "Contacto";
    private static final String NOMBRE = "nombre";
    private static final String TIPO_CONTACTO = "tipo";  // Propiedad para determinar el tipo de contacto
    private static final String USUARIO = "usuario";     // ID del usuario
    private static final String CONTACTOS_GRUPO = "contactos";  // Lista de contactos en el grupo

  
    public TDSContactoDAO() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }


    public static TDSContactoDAO getInstance() {
        if (instancia == null) {
            instancia = new TDSContactoDAO();
        }
        return instancia;
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

      
        if ("ContactoIndividual".equals(tipoContacto)) {
            int idUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eContacto, USUARIO));
            UsuarioDAO usuarioDAO = FactoriaDAO.getInstancia().getUsuarioDAO();
            Usuario usuario = usuarioDAO.get(idUsuario);
            ContactoIndividual contacto = new ContactoIndividual(nombre, usuario);
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


    @Override
    public void create(Contacto contacto) {
    	try {
            // Convertimos el contacto en una entidad para ser persistida
            Entidad eContacto = new Entidad();
            eContacto.setNombre(CONTACTO); // Establecemos el tipo de entidad como 'Contacto'

            // Establecemos las propiedades del contacto
            servPersistencia.anadirPropiedadEntidad(eContacto, NOMBRE, contacto.getNombre());
            
            if (contacto instanceof ContactoIndividual) {
                ContactoIndividual contactoIndividual = (ContactoIndividual) contacto;
                servPersistencia.anadirPropiedadEntidad(eContacto, TIPO_CONTACTO, "ContactoIndividual");
                servPersistencia.anadirPropiedadEntidad(eContacto, USUARIO, String.valueOf(contactoIndividual.getUsuario().getId()));
            } else if (contacto instanceof Grupo) {
                Grupo grupo = (Grupo) contacto;
                servPersistencia.anadirPropiedadEntidad(eContacto, TIPO_CONTACTO, "Grupo");
                // Guardar los contactos en el grupo como una cadena separada por comas de IDs
                StringBuilder contactosIds = new StringBuilder();
                for (Contacto c : grupo.getContactos()) {
                    if (contactosIds.length() > 0) {
                        contactosIds.append(",");
                    }
                    contactosIds.append(c.getId());
                }
                servPersistencia.anadirPropiedadEntidad(eContacto, CONTACTOS_GRUPO, contactosIds.toString());
            }
            
            // Usamos el servicio de persistencia para guardar la entidad
            servPersistencia.registrarEntidad(eContacto);
            contacto.setId(eContacto.getId()); // Establecemos el ID generado

        } catch (Exception e) {
            e.printStackTrace();
        }
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