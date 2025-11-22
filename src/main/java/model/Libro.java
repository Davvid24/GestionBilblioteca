package model;

public class Libro {
    private int id;
    private String titulo;
    private String isbn;

    public Libro() {}

    public Libro(int id, String titulo, String isbn) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }

    public void setId(int id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    @Override
    public String toString() {
        return "Libro:" +
                "   -Id: " + id +
                "   -Titulo: " + titulo +
                "   -ISBN: " + isbn + "\n";
    }
}
