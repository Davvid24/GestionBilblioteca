package org.example;

import dao.*;
import model.Libro;
import service.BibliotecaService;

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

                        break;
                    case 2:
                        //GESTIONAR AUTORES

                        break;
                    case 3:
                        //GESTIONAR AUTORES

                        break;
                    case 4:
                        //GESTIONAR AUTORES

                        break;
                    case 5:
                        //GESTIONAR AUTORES

                        break;

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


    // MENUS

    public void menuAutor(Scanner sc, BibliotecaService bibliotecaService) {
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
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
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

    public void menuLibro(Scanner sc, BibliotecaService bibliotecaService) {
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
                        String titulo = sc.next();
                        System.out.println();
                        System.out.print("ISBN: ");
                        String isbn = sc.next();
                        System.out.println();
                        Libro libro = new Libro(0, titulo, isbn);
                        bibliotecaService.anadirLibro(libro);
                        break;
                    case 2:
                        bibliotecaService.listarLibros();
                        break;
                    case 3:
                        bibliotecaService.listarLibros();

                        break;
                    case 4:
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

    public void menuPrestamos(Scanner sc, BibliotecaService bibliotecaService) {
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
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
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

    public void menuUsuarios(Scanner sc, BibliotecaService bibliotecaService) {
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
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
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
