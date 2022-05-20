package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Administrador extends Persona implements Serializable {

    //================================= RELACIÓN CON LA ENTIDAD TRABAJADOR =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Trabajador> trabajadores;

    //================================= RELACIÓN CON LA ENTIDAD SERVICIO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Servicio> servicios;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Producto> productos;

    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<TipoTrabajador> tipos;

    //================================= CONSTRUCTOR  =================================//


    public Administrador(String id, String nombre, String telefono, String password, String email) {
        super(id, nombre, telefono, password, email);
        trabajadores = new ArrayList<>();
        servicios= new ArrayList<>();
        productos= new ArrayList<>();
        tipos = new ArrayList<>();
    }
}
