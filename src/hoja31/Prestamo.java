package hoja31;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import static java.time.Duration.between;
import static java.time.Period.between;


public class Prestamo <Usuario>{
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
        this.fechaDevolucionPrevista=fechaPrestamo.plusDays(14);
        if(codigoLibro.matches("[0-9]{3}[0-9]{4}")==false){
            throw new PrestamoInvalidoException("inserte un codigo valido");
        }
    }
    public void registrarDevolucion(LocalDate fechaDevolucion){
        if(fechaDevolucion==null || fechaDevolucion.isBefore(fechaPrestamo)){
            throw new PrestamoInvalidoException("inserte una fecha correcta");
        }
        else{
            fechaDevolucionReal=fechaDevolucion;

        }
    }
    public int calcularDiasRetraso(){
        Duration duration=null;
        Duration retraso=duration.between(fechaDevolucionPrevista,fechaDevolucionReal);
        int ret = Integer.parseInt(String.valueOf(retraso));
        if(ret>0){
            return ret;
        }
        else{
            return 0;
        }
    }
    public boolean estaRetrasado(){
        if(calcularDiasRetraso()==0){
            return false;
        }
        else{
            return true;
        }
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
    public String getCodigoLibro() {
        return codigoLibro;
    }
    public LocalDate getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }
    public Usuario getSocio() {
        return socio;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
}
