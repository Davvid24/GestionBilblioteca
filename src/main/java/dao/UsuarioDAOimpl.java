package dao;

import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class UsuarioDAOimpl implements UsuarioDAO {

    @Override
    public void anadirUsuario(Usuario usuario) throws Exception {
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

    @Override
    public void eliminarUsuario(int id) throws Exception {
        String sql = "delete from Usuario where id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Usuario eliminado: " + id);

        }
    }

    @Override
    public List<Usuario> listarUsuario() throws Exception {
        return List.of();
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {
        String sql = "update Usuario set nombre = ? where id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) usuario.setNombre(rs.getString(1));
            }
            System.out.println("Usuario actualizado: " + usuario);
        }
    }
}
