package dao;

import java.util.List;

import dominio.Contacto;

public interface ContactoDAO {
	void create(Contacto contacto);
    boolean delete(Contacto contacto);
    Contacto get(int id);
    List<Contacto> getAll();
}
