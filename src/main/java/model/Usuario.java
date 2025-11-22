package model;

import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private List<Libro> libros;

    public Usuario(int id, String nombre, List<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.libros = libros;
    }

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Usuario:" +
                "   -Id: " + id +
                "   -Nombre: " + nombre +
                "   -Libros: " + libros+ "\n";
    }
}
