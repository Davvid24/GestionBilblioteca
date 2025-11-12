package dao;

import model.Autor;

import java.sql.*;
import java.util.List;

public class AutorDAOimpl implements AutorDAO {


    @Override
    public void anadirAutor(Autor autor) throws Exception {
        String sql = "insert into Autor (nombre) values (?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, autor.getNombre() );
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) autor.setId(rs.getInt(1));
            }

            System.out.println("DAO: Autor insertado -> " + autor);

        }
    }

    @Override
    public void eliminarAutor(int id) throws Exception {

        String sql = "delete from Autor where id = ?";
        //Habría que hacer una llamada a Libros, para comprobar que el id que se va a eliminar no este en ningun libro
        try (Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("DAO: Autor deletado -> " + id);

        }
    }

    @Override
    public List<Autor> listarAutores() throws Exception {
        return List.of();
    }

    @Override
    public void actualizarAutor(Autor autor) throws Exception {

    }
}
