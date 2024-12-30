package dao;

import java.util.List;

import dominio.Mensaje;

public interface MensajeDAO {
	void registrar(Mensaje mensaje) throws DAOException;
    boolean delete(Mensaje mensaje);
    Mensaje get(int id);
    List<Mensaje> getAll();
}
