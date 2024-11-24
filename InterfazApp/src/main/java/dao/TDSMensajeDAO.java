package dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import dominio.Mensaje;
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
    
    private ServicioPersistencia servPersistencia;

    public TDSMensajeDAO() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    private Mensaje entidadToMensaje(Entidad eMensaje) {
        
        String texto = servPersistencia.recuperarPropiedadEntidad(eMensaje, TEXTO);
        String emoticono = servPersistencia.recuperarPropiedadEntidad(eMensaje, EMOTICONO);
        String telefonoEmisor = servPersistencia.recuperarPropiedadEntidad(eMensaje, TELEFONO_EMISOR);
        String telefonoReceptor = servPersistencia.recuperarPropiedadEntidad(eMensaje, TELEFONO_RECEPTOR);
        String fechaHoraEnvio = servPersistencia.recuperarPropiedadEntidad(eMensaje, FECHA_HORA_ENVIO);

        
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraEnvio);

        //La clase Mensaje tiene dos constructores, mensaje de texto y con emoticono
        if (texto != null && !texto.isEmpty()) {
            return new Mensaje(texto, telefonoEmisor, telefonoReceptor, fechaHora);
        } else if (emoticono != null && !emoticono.isEmpty()) {  
            int emoticonon = Integer.parseInt(emoticono);  
            return new Mensaje(emoticonon, telefonoEmisor, telefonoReceptor, fechaHora);
        }

        return null;  // Si no es ninguno de los dos, devolver null
    }

    private Entidad mensajeToEntidad(Mensaje mensaje) {
        Entidad eMensaje = new Entidad();
        eMensaje.setNombre(MENSAJE);

        // Establecer las propiedades de la entidad
        List<Propiedad> propiedades = new ArrayList<>();
        propiedades.add(new Propiedad(TEXTO, mensaje.getTexto()));
        propiedades.add(new Propiedad(EMOTICONO, Integer.toString(mensaje.getEmoticonos())));
        propiedades.add(new Propiedad(TELEFONO_EMISOR, mensaje.getTelefonoEmisor()));
        propiedades.add(new Propiedad(TELEFONO_RECEPTOR, mensaje.getTelefonoReceptor()));
        propiedades.add(new Propiedad(FECHA_HORA_ENVIO, mensaje.getFechaHoraEnvio().toString()));

        eMensaje.setPropiedades(propiedades);
        return eMensaje;
    }

    @Override
    public void create(Mensaje mensaje) {

        Entidad eMensaje = this.mensajeToEntidad(mensaje);
        eMensaje = servPersistencia.registrarEntidad(eMensaje);
        mensaje.setId(eMensaje.getId()); 
    }

    @Override
    public boolean delete(Mensaje mensaje) {
        
        Entidad eMensaje = servPersistencia.recuperarEntidad(mensaje.getId());
        return servPersistencia.borrarEntidad(eMensaje);
    }

    @Override
    public Mensaje get(int id) {

        Entidad eMensaje = servPersistencia.recuperarEntidad(id);
        return entidadToMensaje(eMensaje);
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
