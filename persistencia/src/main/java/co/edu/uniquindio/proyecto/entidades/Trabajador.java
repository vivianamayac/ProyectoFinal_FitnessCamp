package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trabajador extends Persona implements Serializable {

    //================================= RELACIÓN CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACIÓN CON LA ENTIDAD SERVICIO =================================//
    @OneToMany(mappedBy = "trabajador")
    @ToString.Exclude
    private List<Servicio> servicios;


    //================================= CONSTRUCTOR  =================================//

    public Trabajador(String id, String nombre, String telefono, String password, String email, Administrador administrador) {
        super(id, nombre, telefono, password, email);
        this.administrador = administrador;
        this.servicios = new ArrayList<>();
    }
}
