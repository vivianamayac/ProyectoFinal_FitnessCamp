package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reserva implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;


    @Column(length = 100)
    private String fechaReserva;


    //================================= RELACION CON LA ENTIDAD SERVICIO=================================//
    @ManyToOne
    @ToString.Exclude
    private Servicio servicio;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    @ToString.Exclude
    private Usuario usuario;

}
