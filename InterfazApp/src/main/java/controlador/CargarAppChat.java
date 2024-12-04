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
		
	    controlador.registrarUsuario("pepe", "garcia", "pg@", "pepe", "623441", "123", "01/01/1999");
	    controlador.registrarUsuario("julian", "garcia", "pg@", "julian", "655655", "123", "01/01/1999");
	    controlador.registrarUsuario("pascua", "garcia", "pg@", "pascual", "6234243", "123", "01/01/1999");
	    
	    if(controlador.registrarUsuario("juan", "sanchez", "js@", "juan", "623224", "134", "01/01/1999")) {
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
	    controlador.añadirContactoIndividual("Pascual", "6234243");
	    controlador.añadirContactoIndividual("Pepe", "655655");
	    
	    
	   
	   
	   
	 
	    
	  
	}
}
