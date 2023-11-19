package co.edu.uniquindio.agencia.modelo;

import co.edu.uniquindio.agencia.enums.Idioma;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuiaTuristico implements Serializable {

    private String nombre;
    private String identificacion;
    private ArrayList<Idioma> idiomas;
    private float experiencia;
    private ArrayList<Integer> calificaciones;
    private ArrayList<String> comentarios;

    @Override
    public String toString() {
        return "GuiaTuristico{" +
                "nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", idiomas=" + idiomas +
                ", experiencia=" + experiencia +
                ", calificaciones=" + calificaciones +
                ", comentarios=" + comentarios +
                '}';
    }
}
