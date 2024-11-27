package controlador;

import java.time.LocalDate;
import java.util.List;

import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Mensaje;
import dominio.RepositorioUsuarios;
import dominio.Mensaje;
import dominio.Usuario;

public class CargarAppChat {
	public static void main(String[] args) {
		 
		Controlador controlador = Controlador.INSTANCE;
	    controlador.registrarUsuario("pepe", "garcia", "pg@", "pepe", "62344", "123", "01/01/1999");
	    controlador.registrarUsuario("julian", "garcia", "pg@", "julian", "62344", "123", "01/01/1999");
	    
	    if(controlador.registrarUsuario("juan", "sanchez", "js@", "juan", "62322", "134", "01/01/1999")) {
	    	System.out.println("Ha dejado registrarlo");
	    }else {
	    	System.out.println("No ha dejado registrarlo");
	    }
	    // Login del usuario
	    if (!controlador.loginUsuario("pepe", "123")) {
	        System.out.println("ERROR: No se pudo iniciar sesión como 'pepe'.");
	        return;
	    }
	    System.out.printf("id Usuario %s \n", controlador.getUsuarioActual().getId());
	    // Añadir y recuperar contacto
	    ContactoIndividual contacto = controlador.añadirContactoIndividual("puan", "juan");
	    ContactoIndividual c3 = controlador.añadirContactoIndividual("Puliuan", "julian");
	   
	
	    
	    Contacto contacto2 = controlador.getContactoIndividual(contacto.getId());
	    if (contacto == null) {
	        System.out.println("ERROR: No se encontró el contacto 'Juan'.");
	        
	    }
	    /*
	    if (!controlador.loginUsuario("juan", "134")) {
	        System.out.println("ERROR: No se pudo iniciar sesión como 'pepe'.");
	        return;
	    }
*/
	    // Enviar mensajes y obtener mensajesg
	    controlador.enviarMensaje(contacto, "Hola, ¿cómo estás?", Mensaje.ENVIADO);
	    controlador.enviarMensaje(contacto, "Hola, ¿cómo estás?", Mensaje.ENVIADO);
	    List<Mensaje> mensajes = controlador.obtenerMensajes(contacto);
	    System.out.println("Mensajes enviados: " + mensajes.size());
	    System.out.println("Contacto: " + controlador.INSTANCE.getUsuarioActual().getContactos().size());
	    
	    System.out.println("Contacto: " + controlador.INSTANCE.getUsuarioActual().getContactos().size());
	    Contacto contacto3 = controlador.getContactoIndividual(c3.getId());
	    ContactoIndividual c4 = controlador.añadirContactoIndividual("Contprua", "julian");
	    controlador.añadirContactoIndividual("Contp9rua3b88", "julian");
	    System.out.printf("Tamaño de la lista de contactos: %d \n",controlador.INSTANCE.getUsuarioActual().getContactos().size());
	   
	    if(controlador.INSTANCE.getContactoIndividual(c4.getId())==null) {
	    	System.out.println("NO LO RECUPERA");
	    }else {
	    	System.out.printf("Recupera el contacto  \n"+c4.getNombre()+c4.getId());
	    }
	    
	   for(Contacto c: controlador.obtenerContactos()) {
		   System.out.printf("Contacto lista %s \n",c.getNombre());
	   }
	}
}
