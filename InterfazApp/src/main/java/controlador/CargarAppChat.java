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
		
		controlador.registrarUsuario("Maria","Perez","maria.p@um.es", "mary", "624673910","maria","22.03");
		controlador.registrarUsuario("Paula","Gonzales", "paula.g@um.es", "paulaGo","647479157", "papapa","30.12");
		controlador.registrarUsuario("Antonio","Sanchez", "antony@um.es", "antony", "633567819", "123", "4.06");
		controlador.registrarUsuario("Daniel", "Lopez", "daniL@um.es", "dani", "6337894238", "contraseña", "07.09");
		controlador.registrarUsuario("Carlos", "Alonso", "charly@um.es", "charly", "622785638", "chachi", "19.07");
		controlador.registrarUsuario("Carmen", "Alvarez", "carmen.a@um.es", "carmen", "645385911", "CaRmEn", "01.02");
		controlador.registrarUsuario("Alejandro", "Jimenez", "alejandro.j@um.es", "alex", "634568319", "1111", "09.03");
		controlador.registrarUsuario("Jose", "Garcia", "jose.g@um.es", "jose.g", "655224817", "joseg", "17.08");
		System.out.println("Todos los usuarios registrados.");
		
		
		controlador.loginUsuario("mary", "maria");
		System.out.println("Login exitoso.");
		
		Contacto contacto1 = controlador.añadirContactoIndividual("Paula", "647479157");
		Contacto contacto2 = controlador.añadirContactoIndividual("Dani", "6337894238");
		Contacto contacto3 = controlador.añadirContactoIndividual("Jose", "655224817");
		System.out.println("Todos los contactos añadidos.");
		
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Ho3la!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Ho3la!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto2, "Hola. Qué tal?", 0);
		Controlador.INSTANCE.enviarMensaje(contacto2, "Hola. Qué tal?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Ho3la!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto3, "Hola. Qué tal?", 0);
		Controlador.INSTANCE.enviarMensaje(contacto3, "Hola. Qué tal?", 1);
	    
	    
	   
	   
	   
	 
	    
	  
	}
}
