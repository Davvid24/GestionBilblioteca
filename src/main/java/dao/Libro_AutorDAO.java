package dao;

import model.Autor;
import model.Libro;

import java.util.List;

public interface Libro_AutorDAO {
    void anadirLibroAutor(Autor autor, Libro libro) throws Exception;
    void eliminarLibroAutor(int id) throws Exception;
    List<Autor> listarAutoresLibros() throws Exception;
    void actualizarAutoresLibros(Autor autor, Libro libro) throws Exception;
}
