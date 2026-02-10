package hoja31;

import java.time.LocalDate;

public class GestorBiblioteca {
    private static int MAX_USUARIOS = 50;
    private static int MAX_PRESTAMOS = 200;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;
    private int numeroUsuarios=0;
    private int numeroPrestamos=0;
    private int comp;

    public GestorBiblioteca(){

    }
    public void registrarUsuario(Usuario usuario){
        comp=0;
        int cont=numeroUsuarios;
        while(cont>0){
            if(usuarios[numeroUsuarios] == usuario){
                comp=1;
            }
            else{
                cont--;
            }
        }
        if(comp==0) {
            usuarios[numeroUsuarios] = usuario;
            numeroUsuarios++;
        }
        else{
            System.out.println("este usuario ya esta añadido");
        }
    }
    public void realizarPrestamo(String codigoLibro, Usuario socio, String tituloLibro, LocalDate fechaPrestamo){
        Prestamo pr = new Prestamo(codigoLibro, socio, tituloLibro,fechaPrestamo);
    }

}
