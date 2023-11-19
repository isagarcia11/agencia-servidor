package co.edu.uniquindio.agencia.modelo;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Cliente implements Serializable {

    private String identificacion;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private ArrayList<Destino> busquedasDestinos;

    @Override
    public String toString() {
        return "Cliente{" +
                "identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", busquedasDestinos=" + busquedasDestinos +
                '}';
    }
}
