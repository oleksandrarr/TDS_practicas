package dominio;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mensaje {
	public static final int ENVIADO = 0;
	public static final int RECIBIDO = 1;
	
	int id;
	private String texto;
	private int emoticono;
    private int emisor;
    private String telefonoReceptor;
    private int receptor;
    private LocalDateTime fechaHoraEnvio;
    private int tipoMensaje;

    
    //Constructor mensaje de texto
    public Mensaje(String texto, int idUsuario, int contacto,LocalDateTime fechaHoraEnvio, int tipoMensaje) {
		this.texto = texto;
		this.emisor = idUsuario;
		this.receptor = contacto;
		this.fechaHoraEnvio = fechaHoraEnvio;
		this.tipoMensaje = tipoMensaje;
	}
    
    //Constructor mensaje emoticono
    public Mensaje(int emoticono,int usuario, int contacto, LocalDateTime fechaHoraEnvio, int tipoMensaje) {
		
		this.emoticono = emoticono;
		this.emisor = usuario;
		this.receptor = contacto;
		this.fechaHoraEnvio = fechaHoraEnvio;
		this.tipoMensaje = tipoMensaje;
	}
    
   //El tipo de mensaje es Enviado o recibido 
	public int getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(int tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getEmoticono() {
		return emoticono;
	}

	public void setEmoticono(int emoticono) {
		this.emoticono = emoticono;
	}

	public int getEmisor() {
		return emisor;
	}

	public void setEmisor(int emisor) {
		this.emisor = emisor;
	}

	public String getTelefonoReceptor() {
		return telefonoReceptor;
	}

	public void setTelefonoReceptor(String telefonoReceptor) {
		this.telefonoReceptor = telefonoReceptor;
	}

	public int getReceptor() {
		return receptor;
	}

	public void setReceptor(int receptor) {
		this.receptor = receptor;
	}

	public LocalDateTime getFechaHoraEnvio() {
		return fechaHoraEnvio;
	}

	public void setFechaHoraEnvio(LocalDateTime fechaHoraEnvio) {
		this.fechaHoraEnvio = fechaHoraEnvio;
	}
	
	//Encuentra el mensaje que contiene el texto o si el texto es vacio
	// interpretamos que lo que se quiere son todos los mensajes de ese contacto
	public boolean encontrarMensaje(String texto) {
		if(this.texto!=null) {
			if (texto == null || texto.isEmpty()) {
		        return true; // Si no hay texto considero que todos los mensajes coinciden con la busqueda
		    }
			if(this.texto.toLowerCase().contains(texto.toLowerCase())){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}


    
   
}
