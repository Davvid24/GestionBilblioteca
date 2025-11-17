package dao;

import model.Autor;
import model.Prestamo;

import java.util.List;

public interface PrestamoDAO {
    void anadirPrestamo(Prestamo prestamo) throws Exception;
    void eliminarPrestamo(int id) throws Exception;
    List<Autor> listarPrestamo() throws Exception;
    void actualizarPrestamo(Prestamo prestamo) throws Exception;

}
