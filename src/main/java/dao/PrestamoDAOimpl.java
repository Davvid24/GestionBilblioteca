package dao;

import model.Autor;
import model.Prestamo;

import java.sql.*;
import java.util.List;

public class PrestamoDAOimpl implements PrestamoDAO {
    @Override
    public void anadirPrestamo(Prestamo prestamo) throws Exception {
        String sql = "insert into Prestamo (fechaInicio,fechaFin,usuarioId,LibroID) values (?,?,?,?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, prestamo.getFechaInicio());
            ps.setDate(2, prestamo.getFechaFin());
            ps.setInt(3, prestamo.getIdusuario());
            ps.setInt(4, prestamo.getIdlibro());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) prestamo.setId(rs.getInt(1));
            }
            System.out.println("Prestamo insertado correctamente");
        }
    }

    @Override
    public void eliminarPrestamo(int id) throws Exception {
        String sql = "delete from Prestamo where id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Prestamo eliminado correctamente");
        }
    }

    @Override
    public List<Prestamo> listarPrestamo() throws Exception {
        return List.of();
    }

    @Override
    public void actualizarPrestamo(Prestamo prestamo) throws Exception {
    }
}
