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
    
    //Constructor mensaje emotcono
    public Mensaje(int emoticono,int usuario, int contacto, LocalDateTime fechaHoraEnvio, int tipoMensaje) {
		
		this.emoticono = emoticono;
		this.emisor = usuario;
		this.receptor = contacto;
		this.fechaHoraEnvio = fechaHoraEnvio;
		this.tipoMensaje = tipoMensaje;
	}
    
  
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
	
	//hay que cambiar los mensajes , el emisor y recteptor deben se numeros de telefono
	public boolean encontrarMensaje(String texto) {
		System.out.println("ENTRA menaje final"+this.getTexto());
		if (texto == null || texto.isEmpty()) {
	        return true; // Si no hay texto considero que todos los mensajes coinciden con la busqueda
	    }
		if(this.texto.toLowerCase().contains(texto.toLowerCase())){
			System.out.println("ENTRA menaje fina2222222l"+this.getTexto());
			return true;
		}else {
			return false;
		}
	}


    
   
}
