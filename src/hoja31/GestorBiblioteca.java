package hoja31;

import java.time.LocalDate;

public class GestorBiblioteca /*<Prestamo>*/ {
    private static int MAX_USUARIOS = 50;
    private static int MAX_PRESTAMOS = 200;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;
    private int numeroUsuarios=0;
    private int numeroPrestamos=0;
    private int comp;
    private int cont;

    public GestorBiblioteca(){

    }
    public void registrarUsuario(Usuario usuario){
        comp=0;
        cont=numeroUsuarios;
        while(cont>0){
            if(usuarios[cont] == usuario){
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
           throw new UsuarioRepetidoException("Este usuario ya esta registrado");
        }
    }
    public void realizarPrestamo(String codigoLibro, Usuario socio, String tituloLibro, LocalDate fechaPrestamo){
        Prestamo pr = new Prestamo(codigoLibro, socio, tituloLibro,fechaPrestamo);
        if(codigoLibro.matches("[0-9]{3}[0-9]{4}")==false){
            throw new PrestamoInvalidoException("inserte un codigo valido");
        }
        if(socio.estaSancionado() == true){
            throw new UsuarioSancionadoException("Este usuario esta actualmente sancionado");
        }

        comp=0;
        cont=numeroPrestamos;
        while(cont>0){
            if(codigoLibro == pr.getCodigoLibro()){
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
            throw new UsuarioRepetidoException("Este usuario ya esta registrado");
        }

    }
    public Usuario buscarUsuario(Usuario usuario){
        return usuario;
    }


}
