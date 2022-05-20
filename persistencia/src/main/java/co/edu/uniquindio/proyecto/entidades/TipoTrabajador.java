package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TipoTrabajador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String nombre;

    private String description;

    //================================= RELACIÓN CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    public TipoTrabajador(String nombre, String description, Administrador administrador) {
        this.nombre = nombre;
        this.description = description;
        this.administrador = administrador;
    }
}
