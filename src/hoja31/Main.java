package hoja31;

import java.time.LocalDate;

public class Main {
    public static void main (String[] args){
        Usuario user = new Usuario("a", "a@a.com","SOC12345", LocalDate.of(2005, 2,15));
        user.estaSancionado();
        Prestamo pr = new Prestamo("31524568","user","EL quijote", LocalDate.now());
        pr.getCodigoLibro();
    }
}
