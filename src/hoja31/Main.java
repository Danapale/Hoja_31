package hoja31;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws Exception{
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
        int eleccion=0;
        GestorBiblioteca biblioteca = new GestorBiblioteca();
        Usuario[]usuarios = new Usuario[50];
        String codigo_libro = "";
        String titulo_libro;
        LocalDate fecha_prestamo;
        LocalDate fecha_dev_prev;
        LocalDate fecha_dev_real;
        int cont=0;


        
        while(eleccion != 8){
            System.out.println("\n1. Registrar nuevo usuario  \n2. Realizar préstamo de libro  \n3. Devolver libro  \n4. Consultar estado de usuario  \n5. Mostrar préstamos activos  \n6. Mostrar usuarios sancionados  \n7. Actualizar sanciones \n8. Salir ");
            eleccion = sc.nextInt();
            if(eleccion ==1){
                System.out.println("inserte el nombre de usuario");
                nombre_usuario=sc.nextLine();
                try {
                    System.out.println("inserte el email");
                    email_usuario = sc.nextLine();
                }
                catch (UsuarioInvalidoException uie){
                    System.out.println("Inserte un email valido");
                    email_usuario=sc.nextLine();
                };
                System.out.println("inserte el codigo de usuario");
                try{
                codigo_usuario=sc.nextLine();
                }
                catch (UsuarioInvalidoException uie){
                    System.out.println("Inserte un usuario valido");
                    codigo_usuario=sc.nextLine();
                };
                /*System.out.println("inserte el año de registro");
                año_registro=sc.nextInt();
                System.out.println("inserte el mes del registro");
                mes_registro=sc.nextInt();
                System.out.println("inserte el dia el registro");
                dia_registro=sc.nextInt();*/
                Usuario usuario = new Usuario(nombre_usuario, email_usuario, codigo_usuario, LocalDate.now());
                biblioteca.registrarUsuario(usuario);
                //biblioteca1.registrarUsuario(usuario);
                usuario.toString();
            }
            if(eleccion == 2){
                System.out.println("inserte el codigo de libro");
                codigo_libro=sc.nextLine();
                System.out.println("Inserte el titulo del libro");
                titulo_libro=sc.nextLine();
                biblioteca.realizarPrestamo(codigo_usuario, usuarios[cont],titulo_libro, LocalDate.now());

            }
            if(eleccion == 3){
                biblioteca.devolverLibro(codigo_libro, LocalDate.now());
            }
            if(eleccion == 4){
                System.out.println("elige a que usuario quieres consultar");
                String user = sc.nextLine();
                cont= usuarios.length;
                while(cont>=0){
                    if(usuarios[cont].getNumeroSocio() == user ){
                        usuarios[cont].estaSancionado();
                        break;
                    }
                    cont--;
                }
            }
            if(eleccion == 5){
                biblioteca.getPrestamos();
            }
            if(eleccion==6){
                int[] cont2 = new int[usuarios.length];
                int con=0;
                cont = usuarios.length;
                while (cont > 0){
                    if(usuarios[cont].estaSancionado() == true){
                        cont2[con]=cont;
                        con++;
                    }
                    cont--;
                }
                while(con!=0){
                    usuarios[cont2[con]].getNumeroSocio();
                    con--;
                }
            }
            if(eleccion == 7){
                cont=usuarios.length;
                while(cont>0){
                    if(usuarios[cont].estaSancionado() == true){
                        if(usuarios[cont].getFechaFinSancion().isBefore(LocalDate.now())){
                            usuarios[cont].levantarSancion();
                        }
                    }
                    cont++;
                }
            }

        }
    }
}
