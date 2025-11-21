package dao;

import model.Autor;

import java.util.List;

public interface AutorDAO {
    void anadirAutor(Autor autor) throws Exception;
    void eliminarAutor(int id) throws Exception;
    List<Autor> listarAutores() throws Exception;
    Autor getAutorById(int id) throws Exception;
    void actualizarAutor(Autor autor) throws Exception;

}
