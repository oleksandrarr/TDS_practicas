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

public class TDSContactoDAO {

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
}