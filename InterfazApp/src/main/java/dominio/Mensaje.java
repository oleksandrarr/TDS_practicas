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
    private Usuario emisor;
    private String telefonoReceptor;
    private Contacto receptor;
    private LocalDateTime fechaHoraEnvio;
    private int tipoMensaje;

    
    //Constructor mensaje de texto
    public Mensaje(String texto, Usuario usuario, Contacto contacto,LocalDateTime fechaHoraEnvio, int tipoMensaje) {
		
		this.texto = texto;
		this.emisor = usuario;
		this.receptor = contacto;
		this.fechaHoraEnvio = fechaHoraEnvio;
		this.tipoMensaje = tipoMensaje;
	}
    
    //Constructor mensaje emotcono
    public Mensaje(int emoticono,Usuario usuario, Contacto contacto, LocalDateTime fechaHoraEnvio, int tipoMensaje) {
		
		this.emoticono = emoticono;
		this.emisor = usuario;
		this.receptor = contacto;
		this.fechaHoraEnvio = fechaHoraEnvio;
		this.tipoMensaje = tipoMensaje;
	}
    
    public Mensaje(String texto, Usuario usuario, String telefonoReceptor,LocalDateTime fechaHoraEnvio, int tipoMensaje) {
		
		this.texto = texto;
		this.emisor = usuario;
		this.telefonoReceptor = telefonoReceptor;
		this.fechaHoraEnvio = fechaHoraEnvio;
		this.tipoMensaje = tipoMensaje;
	}

    public Mensaje(int emoticono,Usuario usuario, String telefonoReceptor, LocalDateTime fechaHoraEnvio, int tipoMensaje) {
		
		this.emoticono = emoticono;
		this.emisor = usuario;
		this.telefonoReceptor = telefonoReceptor;
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

	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public String getTelefonoReceptor() {
		return telefonoReceptor;
	}

	public void setTelefonoReceptor(String telefonoReceptor) {
		this.telefonoReceptor = telefonoReceptor;
	}

	public Contacto getReceptor() {
		return receptor;
	}

	public void setReceptor(Contacto receptor) {
		this.receptor = receptor;
	}

	public LocalDateTime getFechaHoraEnvio() {
		return fechaHoraEnvio;
	}

	public void setFechaHoraEnvio(LocalDateTime fechaHoraEnvio) {
		this.fechaHoraEnvio = fechaHoraEnvio;
	}


    
   
}
