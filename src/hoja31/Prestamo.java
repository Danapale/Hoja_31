package hoja31;
import java.time.LocalDate;

import static java.time.LocalDate.*;

public class Prestamo {
    private String codigoLibro;
    private String tituloLibro;
    private Usuario socio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;

    public Prestamo(String codigoLibro, Usuario socio, String tituloLibro, LocalDate fechaPrestamo){
        this.codigoLibro=codigoLibro;
        this.socio=socio;
        this.tituloLibro=tituloLibro;
        this.fechaPrestamo=fechaPrestamo;
    }
    public void registrarDevolucion(LocalDate fechaDevolucion){
        if(fechaDevolucion==null){}
        //else if(fechaDevolucion){}

    }
    public int calcularDiasRetraso(){
        return 2;
    }
    public boolean estaRetrasado(){
        return false;
    }
    @Override
    public String toString() {
        if(fechaDevolucionReal==null){
        return "codigo libro: "+codigoLibro+"\ntitulo libro: "+tituloLibro+"\nsocio: "+socio+"\nfecha prestamo: "+fechaPrestamo+"\nfecha de devolucion prevista: "+fechaDevolucionPrevista;
        }
        else{
            return "codigo libro: "+codigoLibro+"\ntitulo libro: "+tituloLibro+"\nsocio: "+socio+"\nfecha prestamo: "+fechaPrestamo+"\nfecha devolucion: "+fechaDevolucionReal;
        }
    }
}
