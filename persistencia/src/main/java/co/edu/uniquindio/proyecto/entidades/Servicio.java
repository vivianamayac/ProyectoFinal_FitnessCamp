package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Servicio implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACIÓN =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 200)
    private String description;


    //================================= RELACIÓN CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACIÓN CON LA ENTIDAD TRABAJADOR =================================//
    @ManyToOne
    private Trabajador trabajador;

    //================================= RELACIÓN CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "servicio")
    @ToString.Exclude
    private List<Reserva> reservas;

    public Servicio(String nombre, String description, Administrador administrador,Trabajador trabajador) {
        this.nombre = nombre;
        this.description = description;
        this.administrador = administrador;
        this.trabajador = trabajador;
        this.reservas = new ArrayList<>();

    }

}
