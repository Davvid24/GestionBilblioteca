package dao;

import model.Autor;
import model.Libro;
import model.Libro_Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOimpl implements AutorDAO {


    @Override
    public void anadirAutor(Autor autor) throws Exception {
        if (existeAutor(autor)) {
            throw new Exception("Autor ya existente");
        } else {
            String sql = "insert into autor (nombre) values (?)";
            try (Connection con = ConnectionManager.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, autor.getNombre());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) autor.setId(rs.getInt(1));
                }

                System.out.println("DAO: Autor insertado -> " + autor);

            }
        }
    }

    @Override
    public void eliminarAutor(int id) throws Exception {
        Libro_AutorDAO autor = new Libro_AutorDAOimpl();
        if (autor.comprobarAutorLibros(id)) {
            System.out.println("El autor tiene libros asociados activos");
        } else {
            String sql = "delete from autor where id = ?";
            //Habría que hacer una llamada a Libros, para comprobar que el id que se va a eliminar no este en ningun libro
            try (Connection con = ConnectionManager.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("DAO: Autor deletado -> " + id);

            }
        }
    }

    @Override
    public List<Autor> listarAutores() throws Exception {
        List<Autor> autores = new ArrayList<>();
        String sql = "select * from autor";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                autores.add(new Autor(rs.getInt(1), rs.getString(2)));
            }
        }
        return autores;
    }

    @Override
    public Autor getAutorById(int id) throws Exception {
        String sql = "select id, nombre from autor where id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Autor(
                            rs.getInt("id"),
                            rs.getString("nombre"));
                }
            }
        }
        return null;
    }

    @Override
    public void actualizarAutor(Autor autor) throws Exception {
        String sql = "update autor set nombre = ? where id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, autor.getNombre());
            ps.setInt(2, autor.getId());
            ps.executeUpdate();
            System.out.println("DAO: Autor modificado -> " + autor);
        }
    }

    @Override
    public Boolean existeAutor(Autor autor) throws Exception {
        String sql = "SELECT * FROM autor WHERE nombre = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, autor.getNombre());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }
}
