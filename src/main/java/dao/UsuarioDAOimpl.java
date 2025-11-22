package dao;

import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOimpl implements UsuarioDAO {

    @Override
    public void anadirUsuario(Usuario usuario) throws Exception {
        if (existeUsuario(usuario)) {
            throw new Exception("Usuario existente");
        } else {
            String sql = "insert into Usuario (nombre) values (?)";
            try (Connection con = ConnectionManager.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, usuario.getNombre());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) usuario.setNombre(rs.getString(1));
                }

                System.out.println("Usuario insertado: " + usuario);
            }
        }
    }

    @Override
    public void eliminarUsuario(int id) throws Exception {
        PrestamoDAO prestamoDAO = new PrestamoDAOimpl();
        if(prestamoDAO.comprobarPrestamosUsuarios(id)){
            System.out.println("El usuario tiene prestamos activos");
        }else {
            String sql = "delete from Usuario where id = ?";
            try (Connection con = ConnectionManager.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Usuario eliminado: " + id);

            }
        }
    }

    @Override
    public List<Usuario> listarUsuario() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "select * from Usuario";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuarioById(int id) throws Exception {
        String sql = "select nombre from Usuario where id = ?";
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return new Usuario(rs.getInt("id"),
                            rs.getString("nombre"));
                }
            }
        }
        return null;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {
        String sql = "update Usuario set nombre = ? where id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) usuario.setNombre(rs.getString(1));
            }
            System.out.println("Usuario actualizado: " + usuario);
        }
    }


    @Override
    public Boolean existeUsuario(Usuario usuario) throws Exception {
        String sql = "select nombre from Usuario where nombre = ?";
        try (Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, usuario.getNombre());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }
}
