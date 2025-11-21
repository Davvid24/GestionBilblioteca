package dao;

import model.Prestamo;

import java.util.List;

public interface PrestamoDAO {
    void anadirPrestamo(Prestamo prestamo) throws Exception;
    void eliminarPrestamo(int id) throws Exception;
    Prestamo getPrestamoById(int id) throws Exception;
    List<Prestamo> listarPrestamos() throws Exception;
    void actualizarPrestamo(Prestamo prestamo) throws Exception;
    Boolean comprobarPrestamosUsuarios(int idUsuario) throws Exception;
    Boolean comprobarPrestamosLibros(int idLibro) throws Exception;

}
