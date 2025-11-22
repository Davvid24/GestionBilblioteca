package dao;

import model.Autor;
import model.Prestamo;

import java.sql.*;
import java.util.List;

public class PrestamoDAOimpl implements PrestamoDAO {

    @Override
    public void anadirPrestamo(Prestamo prestamo) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAOimpl();
        LibroDAO libroDAO = new LibroDAOimpl();
        if (usuarioDAO.getUsuarioById(prestamo.getIdusuario()) != null && libroDAO.getLibroById(prestamo.getIdlibro()) != null) {

            String sql = "insert into Prestamo (fechaInicio,fechaFin,usuarioId,LibroId) values (?,?,?,?)";
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
        } else {
            System.out.println("El prestamo no se pudo insertar. Introduce usuario y libro validos.");
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
    public Prestamo getPrestamoById(int id) throws Exception {
        String sql = "select id,fechaInicio,fechaFin,usuarioId,libroId from Prestamo where id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Prestamo(rs.getInt("id"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getInt("usuarioId"),
                        rs.getInt("libroId"));
            }
        }
        return null;
    }

    @Override
    public List<Prestamo> listarPrestamos() throws Exception {
        String sql = "SELECT * FROM Prestamo";
        List<Prestamo> prestamos = new java.util.ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId(rs.getInt("id"));
                prestamo.setFechaInicio(rs.getDate("fechaInicio"));
                prestamo.setFechaFin(rs.getDate("fechaFin"));
                prestamo.setIdusuario(rs.getInt("usuarioId"));
                prestamo.setIdlibro(rs.getInt("LibroID"));
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }

    @Override
    public void actualizarPrestamo(Prestamo prestamo) throws Exception {
        String sql = "UPDATE Prestamo SET fechaInicio = ?, fechaFin = ?, usuarioId = ?, LibroID = ? WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, prestamo.getFechaInicio());
            ps.setDate(2, prestamo.getFechaFin());
            ps.setInt(3, prestamo.getIdusuario());
            ps.setInt(4, prestamo.getIdlibro());
            ps.setInt(5, prestamo.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Prestamo actualizado correctamente");
            } else {
                System.out.println("No se encontró el prestamo con id " + prestamo.getId());
            }
        }
    }

    @Override
    public Boolean comprobarPrestamosUsuarios(int idUsuario) throws Exception {
        String sql = "select * from Prestamo where usuarioId = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean comprobarPrestamosLibros(int idLibro) throws Exception {
        String sql = "select * from Prestamo where libroId = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }
}
