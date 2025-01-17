package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.Contacto;
import dominio.ContactoIndividual;
import dominio.Grupo;
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
		controlador.registrarUsuario("Pepe", "Morenilla", "pepe.m@um.es", "pepe", "641784967", "123", "9.12", new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		controlador.registrarUsuario("Luis", "Hernandez", "luis.h@um.es", "luis", "644873618", "123456789", "10.11", new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		controlador.registrarUsuario("Juan", "Rodríguez", "juan@um.es", "juan", "646936749", "juan", "15.2", new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));
		
		controlador.loginUsuario("mary", "maria");
		
		Contacto contacto1 = controlador.añadirContactoIndividual("Paula", "647479157");
		Contacto contacto2 = controlador.añadirContactoIndividual("Dani", "6337894238");
		Contacto contacto3 = controlador.añadirContactoIndividual("Jose", "655224817");
		Contacto contacto4 = controlador.añadirContactoIndividual("Alejandro", "634568319");
		Contacto contacto5 = controlador.añadirContactoIndividual(null, "645385911");
		Contacto contacto6 = controlador.añadirContactoIndividual("pepe", "641784967");
		Contacto contacto7 = controlador.añadirContactoIndividual("luis", "644873618");
		Contacto contacto8 = controlador.añadirContactoIndividual("juan", "646936749");
		
		List<ContactoIndividual> lista = new ArrayList<>();
		lista.add((ContactoIndividual) contacto2);
		lista.add((ContactoIndividual) contacto6);
		lista.add((ContactoIndividual) contacto7);
		//Grupo grupo = controlador.añadirGrupo(lista, "Amigos", new URL("https://cdn-icons-png.flaticon.com/512/3135/3135768.png"));

		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Hola! ¿Cómo estás?", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Bien. ¿Qué tal el examen?", 0);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Muy bien! He sacado un 10", 1);
		Controlador.INSTANCE.enviarMensaje(contacto1, "Me alegro mucho", 0);
		Controlador.INSTANCE.enviarMensaje(contacto2, "Hola. Qué tal?", 0);
		Controlador.INSTANCE.enviarMensaje(contacto2, "Hola. Regular", 1);
		Controlador.INSTANCE.enviarMensaje(contacto2, "Y eso?", 0);
		Controlador.INSTANCE.enviarMensaje(contacto2, "Me he puesto malo", 1);
		Controlador.INSTANCE.enviarMensaje(contacto2, ":(", 0);
		Controlador.INSTANCE.enviarMensaje(contacto3, "Buenass", 0);
		Controlador.INSTANCE.enviarMensaje(contacto3, "Hola! Cuánto tiempo!", 1);
		Controlador.INSTANCE.enviarMensaje(contacto3, "A que sí!", 0);
		Controlador.INSTANCE.enviarMensaje(contacto5, "Buenos días", 1);
		Controlador.INSTANCE.enviarMensaje(contacto8, "Holiss", 0);
	   
		//controlador.enviarMensaje(grupo, "Hola a todos!", 0);
		
		List<Mensaje> mensajes = controlador.obtenerMensajes(contacto1.getId());
	  
	}
}
