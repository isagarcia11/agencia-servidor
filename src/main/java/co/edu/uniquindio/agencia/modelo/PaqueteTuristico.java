package co.edu.uniquindio.agencia.modelo;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class PaqueteTuristico implements Serializable {

    private String nombre;
    private ArrayList<Destino> destinos;
    private String duracion;
    private String serviciosAdicionales;
    private float precio;
    private int cupoMaximo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @Override
    public String toString() {
        return "PaqueteTuristico{" +
                "nombre='" + nombre + '\'' +
                ", destinos=" + destinos +
                ", duracion='" + duracion + '\'' +
                ", serviciosAdicionales='" + serviciosAdicionales + '\'' +
                ", precio=" + precio +
                ", cupoMaximo=" + cupoMaximo +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
