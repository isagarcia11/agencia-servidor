package co.edu.uniquindio.agencia.utils;
import java.util.EventObject;
import java.util.Locale;

public class CambioIdiomaEvent extends EventObject {
    private final Locale nuevoIdioma;

    public CambioIdiomaEvent(Object source, Locale nuevoIdioma) {
        super(source);
        this.nuevoIdioma = nuevoIdioma;
    }

    public Locale getNuevoIdioma() {
        return nuevoIdioma;
    }

}
