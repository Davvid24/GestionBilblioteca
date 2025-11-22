package service;

import dao.*;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class BibliotecaService {
    AutorDAO autorDAO = new AutorDAOimpl();
    LibroDAO libroDAO = new LibroDAOimpl();
    Libro_AutorDAO libroAutorDAO = new Libro_AutorDAOimpl();
    PrestamoDAO prestamoDao = new PrestamoDAOimpl();
    UsuarioDAO usuarioDAO = new UsuarioDAOimpl();

    public BibliotecaService(AutorDAO autorDAO, LibroDAO libroDAO, Libro_AutorDAO libroAutorDAO, PrestamoDAO prestamoDao, UsuarioDAO usuarioDAO) {
        this.autorDAO = autorDAO;
        this.libroDAO = libroDAO;
        this.libroAutorDAO = libroAutorDAO;
        this.prestamoDao = prestamoDao;
        this.usuarioDAO = usuarioDAO;
    }

    public BibliotecaService() {
    }

    //Autor DAO

    public void anadirAutor(Autor autor) {
        try{
            autorDAO.anadirAutor(autor);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminaAutor(int idAutor) {
        try{
            autorDAO.eliminarAutor(idAutor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public List<Autor> listarAutor(){
        try {
            return autorDAO.listarAutores();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void actualizarAutor(int id,String nombre) {
        try {
            Autor autor = autorDAO.getAutorById(id);
            if(autor != null){
                autor.setNombre(nombre);
                autorDAO.actualizarAutor(autor);
            }else{
                System.out.println("No se encontro el autor con el id " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar Autor: " + e.getMessage());
        }
    }


    // libro DAO

    public void anadirLibro(Libro libro){
        try{
            libroDAO.addLibro(libro);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Libro> listarLibros(){
        try{
            return libroDAO.getAllLibros();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void eliminarLibro(int idLibro){
        try {
            libroDAO.deleteLibro(idLibro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarLibro(int id,String titulo, String isbn) {
        try {
            Libro libro = libroDAO.getLibroById(id);
            if (libro != null) {
                libro.setId(id);
                libro.setTitulo(titulo);
                libro.setIsbn(isbn);
                libroDAO.updateLibro(libro);
            } else {
                System.out.println("No se encontro el autor con el id " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Prestamos
    public void  anadirPrestamo(Prestamo prestamo){
        try {
            prestamoDao.anadirPrestamo(prestamo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Prestamo>  listarPrestamos( ){
        try {
            return prestamoDao.listarPrestamos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void  eliminarPrestamo( int id){
        try {
            prestamoDao.eliminarPrestamo(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizarPrestamo(Prestamo prestamo) {
        try {
            Prestamo prestamo2 = prestamoDao.getPrestamoById(prestamo.getId());
            if (prestamo2 != null) {
                prestamo2.setFechaInicio(prestamo.getFechaInicio());
                prestamo2.setFechaFin(prestamo.getFechaFin());
                prestamo2.setIdusuario(prestamo.getIdusuario());
                prestamo2.setIdlibro(prestamo.getIdlibro());
                prestamoDao.actualizarPrestamo(prestamo2);
            } else {
                System.out.println("No se encontró el prestamo con id " + prestamo.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar prestamo: " + e.getMessage());
        }
    }


    //Usuarios

    public void anadirUsuario(Usuario usuario){
        try {
            usuarioDAO.anadirUsuario(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Usuario> listarUsuario(){
        try {
            return usuarioDAO.listarUsuario();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void  eliminarUsuario(int id){
        try {
            usuarioDAO.eliminarUsuario(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void  actualizarUsuario(int id, String nombre) {
        try {
            Usuario usuario = usuarioDAO.getUsuarioById(id);
            if (usuario != null) {
                usuario.setNombre(nombre);
                usuarioDAO.actualizarUsuario(usuario);
            }else{
                System.out.println("No se encontro el usuario con el id " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Prestamo  getPrestamoID(int id){
        try {
            return prestamoDao.getPrestamoById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }











}
