package model;

import java.util.List;

public class Autor {
    private int  id;
    private String nombre;


    public Autor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Autor() {
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

    @Override
    public String toString() {
        return "Autor:" +
                "   -Id: " + id +
                "   -Nombre: " + nombre + "\n";
    }
}
