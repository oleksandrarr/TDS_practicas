package dominio;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mensaje {
	//Atributos
    private String texto;
	private int emoticono;
    private String telefonoEmisor;
    private String telefonoReceptor;
    private LocalDateTime fechaHoraEnvio;
    //private int fuente=12;
    //private Color color; ¿La fuente y el color son los mismos para todos los mensajes?
    
    //Constructor mensaje de texto
    public Mensaje(String texto, String telefonoEmisor, String telefonoReceptor,LocalDateTime fechaHoraEnvio) {
		
		this.texto = texto;
		this.telefonoEmisor = telefonoEmisor;
		this.telefonoReceptor = telefonoReceptor;
		this.fechaHoraEnvio = fechaHoraEnvio;
	}
    
    //Constructor mensaje emotcono
    public Mensaje(int emoticono, String telefonoEmisor, String telefonoReceptor, LocalDateTime fechaHoraEnvio) {
		
		this.emoticono = emoticono;
		this.telefonoEmisor = telefonoEmisor;
		this.telefonoReceptor = telefonoReceptor;
		this.fechaHoraEnvio = fechaHoraEnvio;
	}


    public String getTexto() {
        return texto;
    }

    public int getEmoticonos() {
        return emoticono;
    }

    public String getTelefonoEmisor() {
        return telefonoEmisor;
    }

    public String getTelefonoReceptor() {
        return telefonoReceptor;
    }

    public LocalDateTime getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    
   
}
