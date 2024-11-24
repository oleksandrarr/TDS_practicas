package dao;

import java.util.List;

import dominio.Mensaje;

public interface MensajeDAO {
	void create(Mensaje mensaje);
    boolean delete(Mensaje mensaje);
    Mensaje get(int id);
    List<Mensaje> getAll();
}
