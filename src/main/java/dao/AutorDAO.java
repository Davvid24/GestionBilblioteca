package dao;

import model.Autor;

import java.util.List;

public interface AutorDAO {
    void anadirAutor(Autor autor) throws Exception;
    void eliminarAutor(int id) throws Exception;
    List<Autor> listarAutores() throws Exception;
    void actualizarAutor(Autor autor) throws Exception;

}
