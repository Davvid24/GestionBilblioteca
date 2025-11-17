package dao;

import model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void anadirUsuario(Usuario usuario) throws Exception;
    void eliminarUsuario(int id) throws Exception;
    List<Usuario> listarUsuario() throws Exception;
    void actualizarUsuario(Usuario usuario) throws Exception;

}
