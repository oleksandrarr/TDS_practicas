package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Mensaje;
import dominio.RepositorioUsuarios;
import dominio.Mensaje;
import dominio.Usuario;

public class CargarAppChat {
	public static void main(String[] args) throws Exception {
		 
		Controlador controlador = Controlador.INSTANCE;
		
		controlador.registrarUsuario("Maria","Perez","maria.p@um.es", "mary", "624673910","maria","22.03",new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		controlador.registrarUsuario("Paula","Gonzales", "paula.g@um.es", "paulaGo","647479157", "papapa","30.12",new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		controlador.registrarUsuario("Daniel", "Lopez", "daniL@um.es", "dani", "6337894238", "contraseña", "07.09",new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		controlador.registrarUsuario("Carlos", "Alonso", "charly@um.es", "charly", "622785638", "chachi", "19.07",new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		controlador.registrarUsuario("Carmen", "Alvarez", "carmen.a@um.es", "carmen", "645385911", "CaRmEn", "01.02",new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		controlador.registrarUsuario("Alejandro", "Jimenez", "alejandro.j@um.es", "alex", "634568319", "1111", "09.03",new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		controlador.registrarUsuario("Jose", "Garcia", "jose.g@um.es", "jose.g", "655224817", "joseg", "17.08",new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		System.out.println("Todos los usuarios registrados.");
		
		
		controlador.loginUsuario("mary", "maria");
		System.out.println("Login exitoso.");
		
		Contacto contacto1 = controlador.añadirContactoIndividual("Paula", "647479157");
		Contacto contacto2 = controlador.añadirContactoIndividual("Dani", "6337894238");
		Contacto contacto3 = controlador.añadirContactoIndividual("Jose", "655224817");
		Contacto contacto4 = controlador.añadirContactoIndividual("Alejandro", "634568319");
		System.out.println("Todos los contactos añadidos.");
		
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Ho3la!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Ho3la!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto2, "Hola. Qué tal?", 0);
		Controlador.INSTANCE.enviarMensaje(contacto2, "Hola. Qué tal?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Ho3la!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto3, "Hola. Qué tal?", 0);
		Controlador.INSTANCE.enviarMensaje(contacto3, "Hola. Qué tal?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola. Qué tal?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola. Qué tal?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola. Qué tal?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola. Qué tal?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola. Qué tal?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola. Qué tal?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola.322342 Qué tal?", 1);
	   
		List<Mensaje> mensajes = controlador.obtenerMensajes(contacto1);
		for (Mensaje mensaje : mensajes) {
		    System.out.println("Mensaje: " + mensaje.getTexto() 
		        + ", Fecha: " + mensaje.getFechaHoraEnvio() 
		        + ", Emisor: " + mensaje.getEmisor());
		}
	 
	    
	  
	}
}
