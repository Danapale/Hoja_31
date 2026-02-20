package hoja31;

import java.time.LocalDate;
import java.util.Arrays;

public class GestorBiblioteca{
    private static int MAX_USUARIOS = 50;
    private static int MAX_PRESTAMOS = 200;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;
    private int numeroUsuarios;
    private int numeroPrestamos;
    private int comp;
    //private int cont;
    public int pilaUrs=0;
    public int pilaPres=0;

    public GestorBiblioteca(){
        usuarios=new Usuario[MAX_USUARIOS];
        prestamos = new Prestamo[MAX_PRESTAMOS];
        numeroUsuarios=0;
        numeroPrestamos=0;

    }
    public void registrarUsuario(Usuario usuario){
        comp=0;
        pilaUrs=numeroUsuarios;
        while(pilaUrs>0){
            if(usuarios[pilaUrs] == usuario){
                comp=1;
            }
            else{
                pilaUrs--;
            }
        }
        if(comp==0) {
            if(numeroUsuarios > MAX_USUARIOS){
                System.out.println("no se admiten más usuarios");
            }
            else {
                usuarios[pilaUrs] = usuario;
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
        pilaPres=numeroPrestamos;
        while(pilaPres>0){
            if(codigoLibro == prestamos[pilaPres].getCodigoLibro()){
                comp=1;
            }
            else{
                pilaPres--;
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
        pilaPres=numeroPrestamos;
        while(pilaPres>0 || comp==1){
            if(codigoLibro == prestamos[pilaPres].getCodigoLibro()){
                comp=1;
            }
            else{
                pilaPres--;
            }
        }
        if(comp==1){
            LocalDate fdp = prestamos[pilaPres].getFechaDevolucionPrevista();
            Usuario urs = (Usuario) prestamos[pilaPres].getSocio();
            if(fdp.isBefore(fechaDevolucion)==true){
                urs.sancionar((int) fechaDevolucion.getDayOfYear()- fdp.getDayOfYear());
            }
            if(fechaDevolucion.isBefore(prestamos[pilaPres].getFechaPrestamo())){
                throw new PrestamoInvalidoException("inserte una fecha correcta");
            }
            return true;
        }
        else{
            return false;
        }
    }
    public Usuario buscarUsuario(String numeroSocio){
        int cont;
        cont = 0;
        int comp=0;
        while(numeroUsuarios>cont){
            if(usuarios[cont].getNumeroSocio().equals(numeroSocio)){
                comp=1;
                break;
            }
            else{
                cont++;
            }
        }
        if(comp==1){
            return usuarios[cont];
        }
        else{
            System.out.println("ese usuario no existe");
            return null;
        }

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

    public int getPilaPres() {
        return pilaPres;
    }

    public int getPilaUrs() {
        return pilaUrs;
    }
}
