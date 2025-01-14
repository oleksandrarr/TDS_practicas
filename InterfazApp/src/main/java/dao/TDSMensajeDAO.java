package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import dominio.Contacto;
import dominio.Mensaje;
import dominio.Usuario;
import beans.Entidad;
import beans.Propiedad;

/**
 * Clase que implementa el Adaptador DAO concreto de Mensaje para el tipo H2.
 */
public final class TDSMensajeDAO implements MensajeDAO {

    private static final String MENSAJE = "Mensaje";
    private static final String TEXTO = "texto";
    private static final String EMOTICONO = "emoticono";
    private static final String TELEFONO_EMISOR = "telefonoEmisor";
    private static final String TELEFONO_RECEPTOR = "telefonoReceptor";
    private static final String FECHA_HORA_ENVIO = "fechaHoraEnvio";
    private static final String USUARIO = "usuario";
    private static final String CONTACTO = "contacto";
    private static final String TIPO = "tipo";
    private ServicioPersistencia servPersistencia;

    public TDSMensajeDAO() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    private Mensaje entidadToMensaje(Entidad eMensaje) throws DAOException {
    
        String texto = servPersistencia.recuperarPropiedadEntidad(eMensaje, TEXTO);
        String emoticono = servPersistencia.recuperarPropiedadEntidad(eMensaje, EMOTICONO);
        String telefonoReceptor = servPersistencia.recuperarPropiedadEntidad(eMensaje, TELEFONO_RECEPTOR);
        String fechaHoraEnvio = servPersistencia.recuperarPropiedadEntidad(eMensaje, FECHA_HORA_ENVIO);

        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraEnvio);
     
        int tipo = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, TIPO));
        
        //Recuperamos el id del usuario que emitio el mensaje
        int idUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, USUARIO));
       
      
        //Recuperamos el contacto al que se le envió el mensaje
        int contacto = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, CONTACTO));

      
        //La clase Mensaje tiene dos constructores, mensaje de texto y con emoticono
        if (texto != null && !texto.isEmpty()) {
            return new Mensaje(texto, idUsuario, contacto, fechaHora,tipo);
        } else if (emoticono != null && !emoticono.isEmpty()) {
            int emoticonon = Integer.parseInt(emoticono);  
            return new Mensaje(emoticonon, idUsuario, contacto, fechaHora,tipo);
        }
   
        return null;  // Si no es ninguno de los dos, devolver null
    }

    private Entidad mensajeToEntidad(Mensaje mensaje) {
        Entidad eMensaje = new Entidad();
        eMensaje.setNombre(MENSAJE);
        eMensaje.setPropiedades(new ArrayList<>(Arrays.asList(
        	    new Propiedad(TEXTO, mensaje.getTexto()),
        	    new Propiedad(EMOTICONO, Integer.toString(mensaje.getEmoticono())),
        	    new Propiedad(USUARIO, Integer.toString(mensaje.getEmisor())),
        	    new Propiedad(CONTACTO, Integer.toString(mensaje.getReceptor())),
        	    new Propiedad(FECHA_HORA_ENVIO, mensaje.getFechaHoraEnvio().toString()),
        	    new Propiedad(TIPO, Integer.toString(mensaje.getTipoMensaje()))
        	)));
   
        return eMensaje;
    }

    @Override
    public void registrar(Mensaje mensaje) throws DAOException {
        Entidad eMensaje = mensajeToEntidad(mensaje);

        try {
            eMensaje = servPersistencia.registrarEntidad(eMensaje);
            mensaje.setId(eMensaje.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Error al registrar el mensaje en la base de datos."+e);
        }
    }
    @Override
    public boolean delete(Mensaje mensaje) {
        
        Entidad eMensaje = servPersistencia.recuperarEntidad(mensaje.getId());
        return servPersistencia.borrarEntidad(eMensaje);
    }

    @Override
    public Mensaje get(int id) {

        Entidad eMensaje = servPersistencia.recuperarEntidad(id);
        try {
        	
			return entidadToMensaje(eMensaje);
		} catch (DAOException e) {
			e.printStackTrace();
		}

        return null;
    }

    @Override
    public List<Mensaje> getAll() {
      
        List<Entidad> entidades = servPersistencia.recuperarEntidades(MENSAJE);
        List<Mensaje> mensajes = new ArrayList<>();

 
        for (Entidad eMensaje : entidades) {
            mensajes.add(get(eMensaje.getId()));
        }

        return mensajes;
    }
}