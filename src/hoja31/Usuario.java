package hoja31;
import java.io.StringReader;
import java.time.LocalDate;

public class Usuario {
    private String nombre;
    private String email;
    private String numeroSocio;
    private LocalDate fechaRegistro;
    private boolean sancionado;
    private LocalDate  fechaFinSancion;

    public Usuario(String nombre, String email, String numeroSocio, LocalDate fechaRegistro){

        this.nombre= nombre;
        this.email=email;
        this.numeroSocio=numeroSocio;
        this.fechaRegistro=fechaRegistro;
        fechaFinSancion=null;
        while(email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*[.][a-z]*")==false){
            //throw new UsuarioInvalidoException();
            System.out.println("inserte un email valido");
            this.email=email;
        }
        while(numeroSocio.matches("SOC[0-9]{5}")==false){
            System.out.println("inserte un email valido");
            this.email=email;
        }
    }
    public void sancionar(){
        if(fechaFinSancion==null){
            fechaFinSancion=LocalDate.now().plusDays(30);
        }
        else{
            fechaFinSancion=fechaFinSancion.plusDays(30);
        }
    }
    public void levantarSancion(){
        fechaFinSancion=null;
    }
    public boolean estaSancionado() {
        return sancionado;
    }
    @Override
    public String toString() {
        if(sancionado==true){
            return "nombre: "+nombre+"\nemail: "+email+"\nnumero de socio: "+numeroSocio+"\nfecha fin de sancion: "+fechaFinSancion;
        }
        else{
            return "nombre: "+nombre+"\nemail: "+email+"\nnumero de socio: "+numeroSocio;
        }
    }
}
