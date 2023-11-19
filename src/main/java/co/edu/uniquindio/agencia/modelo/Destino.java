package co.edu.uniquindio.agencia.modelo;

import co.edu.uniquindio.agencia.enums.Clima;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Destino implements Serializable {

    private String nombre;
    private String ciudad;
    private String descripcion;
    private Clima clima;
    private ArrayList<String> imagenes;
    private ArrayList<Integer> calificaciones;
    private ArrayList<String> comentarios;

    @Override
    public String toString() {
        return "Destino{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", clima=" + clima +
                ", imagenes=" + imagenes +
                ", calificaciones=" + calificaciones +
                ", comentarios=" + comentarios +
                '}';
    }
}
