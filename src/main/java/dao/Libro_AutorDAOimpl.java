package dao;

import model.Autor;
import model.Libro;
import model.Libro_Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Libro_AutorDAOimpl implements Libro_AutorDAO {
    @Override
    public void anadirLibroAutor(Autor autor, Libro libro) throws Exception {
        String sql = "insert into libro_autor (idLibro,idAutor) values (?,?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,libro.getId());
            ps.setInt(2, autor.getId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) autor.setId(rs.getInt(1));
            }

            System.out.println("DAO: Autor insertado -> " + autor);

        }
    }

    @Override
    public void eliminarLibroAutor(int id) throws Exception {
            String sql = "DELETE FROM libro_autor WHERE idLibro = ?";
            try (Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1,id);
            }
    }

    @Override
    public List<Libro_Autor> listarAutoresLibros() throws Exception {
        List<Libro_Autor> librosAutores = new ArrayList<>();
        String sql = "SELECT * FROM libro_autor";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                librosAutores.add(new Libro_Autor(rs.getInt(1), rs.getInt(2)));
            }
        }

        return librosAutores;
    }


    @Override
    public void actualizarAutoresLibros(Autor autor, Libro libro) throws Exception {
        String sql = "update libro_Autor set idAutor = ?, idLibro = ? where idAutor = ? && idLibro = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
                 ps.setInt(1,libro.getId());
                 ps.setInt(2, autor.getId());
                 ps.setInt(3, libro.getId());
                 ps.setInt(4, autor.getId());
                 ps.executeUpdate();
        }
    }

    @Override
    public Boolean comprobarAutorLibros(int idAutor) throws Exception {
        String sql = "SELECT * FROM libro_autor WHERE idAutor = ?";
        try (Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idAutor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }
}
