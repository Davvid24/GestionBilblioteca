package org.example;

import dao.*;
import model.Autor;
import model.Libro;
import model.Prestamo;
import model.Usuario;
import service.BibliotecaService;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AutorDAO autorDAO = new AutorDAOimpl();
        LibroDAO libroDAO = new LibroDAOimpl();
        UsuarioDAO usuarioDAO = new UsuarioDAOimpl();
        PrestamoDAO prestamoDAO = new PrestamoDAOimpl();
        Libro_AutorDAO libro_AutorDAO = new Libro_AutorDAOimpl();

        BibliotecaService bibliotecaService =
                new BibliotecaService(autorDAO, libroDAO, libro_AutorDAO, prestamoDAO, usuarioDAO);
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("""
                         =================================
                                GESTION BIBLIOTECA
                         =================================
                         1. Gestionar Autores.
                         2. Gestionar Libros.
                         3. Gestionar Prestamos.
                         4. Gestionar Usuarios.
                         5. Salir.
                         \s
                          Introduce el nº de una de estas opciones.
                        \s
                        \s""");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        //GESTIONAR AUTORES
                        menuAutor(sc, bibliotecaService);
                        break;
                    case 2:
                        //GESTIONAR AUTORES
                        menuLibro(sc, bibliotecaService);

                        break;
                    case 3:
                        //GESTIONAR AUTORES
                        menuPrestamos(sc, bibliotecaService);

                        break;
                    case 4:
                        //GESTIONAR AUTORES
                        menuUsuarios(sc, bibliotecaService);

                        break;
                    case 5:
                        System.out.println("Finalizando gestion.");
                        System.exit(0);
                        break;

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


    // MENUS

    public static void menuAutor(Scanner sc, BibliotecaService bibliotecaService) {
        boolean control = true;
        try {
            while (control) {
                System.out.println("""
                         =================================
                                  GESTION AUTORES
                         =================================
                         1. Crear Autor.
                         2. Leer Autores.
                         3. Actualizar Autor.
                         4. Eliminar Autor.
                         5. Salir.
                         \s
                          Introduce el nº de una de estas opciones.
                        \s
                        \s""");


                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        //añadir libro
                        System.out.println("Introduce los datos del Autor:");
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        Autor autor = new Autor(0, nombre);
                        bibliotecaService.anadirAutor(autor);

                        break;
                    case 2:
                        bibliotecaService.listarAutor();
                        break;
                    case 3:
                        bibliotecaService.listarLibros();
                        System.out.print("Introduce el id del Autor a modificar:");
                        int id = sc.nextInt();
                        System.out.println();
                        System.out.println("Introduce el nuevo Nombre del Autor");
                        nombre = sc.nextLine();
                        bibliotecaService.actualizarAutor(id, nombre);
                        break;
                    case 4:
                        bibliotecaService.listarAutor();
                        System.out.println("Introduce el id del Autor a elimianr:");
                        id = sc.nextInt();
                        bibliotecaService.eliminaAutor(id);
                        break;
                    case 5:
                        control = false;
                        break;
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void menuLibro(Scanner sc, BibliotecaService bibliotecaService) {
        boolean control = true;
        try {
            while (control) {
                System.out.println("""
                         =================================
                                  GESTION LIBROS
                         =================================
                         1. Crear Libro.
                         2. Leer Libros.
                         3. Actualizar Libro.
                         4. Eliminar Libro.
                         5. Salir.
                         \s
                          Introduce el nº de una de estas opciones.
                        \s
                        \s""");


                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        //añadir libro
                        System.out.println("Introduce los datos del libro:");
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.println();
                        System.out.print("ISBN: ");
                        String isbn = sc.nextLine();
                        System.out.println();
                        Libro libro = new Libro(0, titulo, isbn);
                        bibliotecaService.anadirLibro(libro);
                        break;
                    case 2:
                        bibliotecaService.listarLibros();
                        break;
                    case 3:
                        bibliotecaService.listarLibros();
                        System.out.print("Introduce el id del libro a cambiar:");
                        int id = sc.nextInt();
                        System.out.println();
                        System.out.println("Introduce el nuevo titulo del libro:");
                        titulo = sc.nextLine();
                        System.out.println();
                        System.out.println("Introduce el nuevo isbn del libro:");
                        isbn = sc.nextLine();
                        System.out.println();
                        bibliotecaService.actualizarLibro(id, titulo, isbn);
                        break;
                    case 4:
                        bibliotecaService.listarLibros();
                        System.out.println("Introduce el id del libro a elimianr:");
                        id = sc.nextInt();
                        bibliotecaService.eliminarLibro(id);
                        break;
                    case 5:
                        control = false;
                        break;
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void menuPrestamos(Scanner sc, BibliotecaService bibliotecaService) {
        boolean control = true;
        try {
            while (control) {
                System.out.println("""
                         =================================
                                 GESTION PRÉSTAMOS
                         =================================
                         1. Crear Préstamo.
                         2. Leer Préstamos.
                         3. Actualizar Préstamo.
                         4. Eliminar Préstamo.
                         5. Salir.
                         \s
                          Introduce el nº de una de estas opciones.
                        \s
                        \s""");


                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        //añadir prestamo
                        System.out.println("Introduce los datos del préstamo:");
                        System.out.print("Fecha inicio: ");
                        Date fechaInicio = Date.valueOf(sc.nextLine());
                        System.out.println();
                        System.out.print("Fecha Fin: ");
                        Date fechaFin = Date.valueOf(sc.nextLine());
                        System.out.println();
                        System.out.print("Id del Usuario: ");
                        int idUsuario = sc.nextInt();
                        System.out.println();
                        System.out.print("Id del Libro: ");
                        int idLibro = sc.nextInt();
                        System.out.println();

                        Prestamo prestamo = new Prestamo(0,  fechaInicio, fechaFin, idUsuario, idLibro);
                        bibliotecaService.anadirPrestamo(prestamo);
                        break;
                    case 2:
                        bibliotecaService.listarPrestamos();
                        break;
                    case 3:
                        bibliotecaService.listarPrestamos();
                        System.out.print("Introduce el id del préstamo a cambiar:");
                        int id = sc.nextInt();
                        System.out.println();
                        System.out.print("nueva Fecha inicio: ");
                        fechaInicio = Date.valueOf(sc.nextLine());
                        System.out.println();
                        System.out.print("nueva Fecha Fin: ");
                        fechaFin = Date.valueOf(sc.nextLine());
                        System.out.println();
                        System.out.print("Id del nuevo Usuario: ");
                        idUsuario = sc.nextInt();
                        System.out.println();
                        System.out.print("Id del nuevo Libro: ");
                        idLibro = sc.nextInt();
                        System.out.println();
                        prestamo = new Prestamo(id,  fechaInicio, fechaFin, idUsuario, idLibro);
                        bibliotecaService.actualizarPrestamo(prestamo);


                        break;
                    case 4:
                        bibliotecaService.listarPrestamos();
                        System.out.println("Introduce el id del préstamo a eliminar:");
                        id = sc.nextInt();
                        bibliotecaService.eliminarPrestamo(id);
                        break;
                    case 5:
                        control = false;
                        break;
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void menuUsuarios(Scanner sc, BibliotecaService bibliotecaService) {
        boolean control = true;
        try {
            while (control) {
                System.out.println("""
                         =================================
                                  GESTION USUARIOS
                         =================================
                         1. Crear Usuario.
                         2. Leer Usuarios.
                         3. Actualizar Usuario.
                         4. Eliminar Usuario.
                         5. Salir.
                         \s
                          Introduce el nº de una de estas opciones.
                        \s
                        \s""");


                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        //añadir
                        System.out.println("Introduce el nombre del Usuario:");
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.println();
                        Usuario usuario = new Usuario(0, nombre);
                        bibliotecaService.anadirUsuario(usuario);
                        break;
                    case 2:
                        bibliotecaService.listarUsuario ();
                        break;
                    case 3:
                        bibliotecaService.listarUsuario();
                        System.out.print("Introduce el id del Usuario a cambiar:");
                        int id = sc.nextInt();
                        System.out.println("Introduce el nuevo Nombre del Usuario:");
                        nombre = sc.nextLine();
                        System.out.println();

                        bibliotecaService.actualizarUsuario(id, nombre);
                        break;
                    case 4:
                        bibliotecaService.listarLibros();
                        System.out.println("Introduce el id del usuario a eliminar:");
                        id = sc.nextInt();
                        bibliotecaService.eliminarUsuario(id);
                        break;
                    case 5:
                        control = false;
                        break;
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
