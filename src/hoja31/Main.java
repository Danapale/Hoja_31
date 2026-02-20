package hoja31;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        /*Usuario user = new Usuario("a", "a@a.com","SOC12345", LocalDate.of(2005, 2,15));
        user.estaSancionado();
        Prestamo pr = new Prestamo("31524568","user","EL quijote", LocalDate.now());
        pr.getCodigoLibro();*/
        Scanner sc = new Scanner(System.in);
        String nombre_usuario;
        String email_usuario = "";
        String codigo_usuario = "";
        int año_registro;
        int mes_registro;
        int dia_registro;
        int eleccion = 0;
        GestorBiblioteca biblioteca = new GestorBiblioteca();
        String codigo_libro = "";
        String titulo_libro;
        LocalDate fecha_prestamo;
        LocalDate fecha_dev_prev;
        LocalDate fecha_dev_real;
        int cont = 0;
        String user;


        while (eleccion != 8) {
            System.out.println("\n1. Registrar nuevo usuario  \n2. Realizar préstamo de libro  \n3. Devolver libro  \n4. Consultar estado de usuario  \n5. Mostrar préstamos activos  \n6. Mostrar usuarios sancionados  \n7. Actualizar sanciones \n8. Salir ");
            try {
                eleccion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("introduzca un valor correcto");
            }
            if (eleccion == 1) {
                System.out.println("inserte el nombre de usuario");
                nombre_usuario = sc.nextLine();
                System.out.println("inserte el email");
                try{
                    email_usuario = sc.nextLine();
                } catch (UsuarioInvalidoException uie) {
                    System.out.println("Inserte un email valido");
                }
                System.out.println("inserte el codigo de usuario");
                try {
                    codigo_usuario = sc.nextLine();
                } catch (UsuarioInvalidoException uie1) {
                    System.out.println("Inserte un usuario valido");
                    codigo_usuario = sc.nextLine();
                }
                ;
                /*System.out.println("inserte el año de registro");
                año_registro=sc.nextInt();
                System.out.println("inserte el mes del registro");
                mes_registro=sc.nextInt();
                System.out.println("inserte el dia el registro");
                dia_registro=sc.nextInt();*/
                Usuario usuario = new Usuario(nombre_usuario, email_usuario, codigo_usuario, LocalDate.now());
                biblioteca.registrarUsuario(usuario);
                System.out.println(usuario.toString());
            }
            if (eleccion == 2) {
                System.out.println("inserte el codigo de libro");
                try{
                    codigo_libro = sc.nextLine();
                }
                catch (PrestamoInvalidoException pie){
                    System.out.println("El codigo del libro tiene que ser valido");
                }
                System.out.println("Inserte el titulo del libro");
                titulo_libro = sc.nextLine();
                biblioteca.realizarPrestamo(codigo_usuario, biblioteca.getUsuarios()[biblioteca.pilaUrs], titulo_libro, LocalDate.now());

            }
            if (eleccion == 3) {
                System.out.println("Inserte el codigo de libro a devolver");
                codigo_libro= sc.nextLine();
                try{
                    biblioteca.devolverLibro(codigo_libro, LocalDate.now());
                }
                catch (PrestamoInvalidoException pie){
                    System.out.println("la fecha no es correcta");
                }
            }
            if (eleccion == 4) {
                System.out.println("elige a que usuario quieres consultar poniendo el codigo de socio");
                user = sc.nextLine();
                /*cont = usuarios.length;
                while (cont >= 0) {
                    if (usuarios[cont].getNumeroSocio() == user) {
                        usuarios[cont].estaSancionado();
                        break;
                    }
                    cont--;
                }*/

                biblioteca.buscarUsuario(user);
            }
            if (eleccion == 5) {
                biblioteca.getPrestamos();
            }
            if (eleccion == 6) {
                int[] cont2 = new int[biblioteca.getUsuarios().length];
                int con = 0;
                cont = biblioteca.getUsuarios().length;

                while (cont > 0) {
                    if (biblioteca.getUsuarios()[cont].estaSancionado() == true) {
                        cont2[con] = cont;
                        con++;
                    }
                    cont--;
                }
                while (con != 0) {
                    biblioteca.getUsuarios()[cont2[con]].getNumeroSocio();
                    con--;
                }
            }
            if (eleccion == 7) {
                cont = biblioteca.getUsuarios().length;
                while (cont > 0) {
                    if (biblioteca.getUsuarios()[cont].estaSancionado() == true) {
                        if (biblioteca.getUsuarios()[cont].getFechaFinSancion().isBefore(LocalDate.now())) {
                            biblioteca.getUsuarios()[cont].levantarSancion();
                        }
                    }
                    cont++;
                }
            }

        }
    }
}
