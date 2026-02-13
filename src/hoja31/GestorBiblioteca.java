package hoja31;

import java.time.LocalDate;
import java.util.Arrays;

public class GestorBiblioteca /*Usuario*/ {
    private static int MAX_USUARIOS = 50;
    private static int MAX_PRESTAMOS = 200;
    private Usuario[] usuarios = new Usuario[MAX_USUARIOS];
    private Prestamo[] prestamos = new Prestamo[MAX_PRESTAMOS];
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
            if(numeroUsuarios > MAX_USUARIOS){
                System.out.println("no se admiten más usuarios");
            }
            else {
                usuarios[cont] = usuario;
                numeroUsuarios++;
            }
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
            if(codigoLibro == prestamos[cont].getCodigoLibro()){
                comp=1;
            }
            else{
                cont--;
            }
        }
        if(comp==0) {
            if(numeroPrestamos>MAX_PRESTAMOS){
                System.out.println("no se admiten más prestamos");
            }
            else {
                prestamos[numeroPrestamos] = pr;
                numeroPrestamos++;
            }
        }
        else{
            throw new LibroNoDisponibleException("Este usuario ya esta registrado");
        }

    }
    public boolean devolverLibro (String codigoLibro, LocalDate fechaDevolucion){
        //ver si se sanciona o no
        comp=0;
        cont=numeroPrestamos;
        while(cont>0 || comp==1){
            if(codigoLibro == prestamos[cont].getCodigoLibro()){
                comp=1;
            }
            else{
                cont--;
            }
        }
        if(comp==1){
            LocalDate fdp = prestamos[cont].getFechaDevolucionPrevista();
            Usuario urs = (Usuario) prestamos[cont].getSocio();
            if(fdp.isBefore(fechaDevolucion)==true){
                urs.sancionar((int) fechaDevolucion.getDayOfYear()- fdp.getDayOfYear());
            }
            if(fechaDevolucion.isBefore(prestamos[cont].getFechaPrestamo())){
                throw new PrestamoInvalidoException("inserte una fecha correcta");
            }
            return true;
        }
        else{
            return false;
        }
    }
    public Usuario buscarUsuario(Usuario usuario){
        return usuario;
    }
    public Prestamo[] getPrestamos() {
        return prestamos;
    }
    public Usuario[] getUsuarios() {
        return usuarios;
    }
    @Override
    public String toString() {
        return Arrays.toString(getPrestamos()) + Arrays.toString(getUsuarios());
    }
}
