package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

//================================= RELACION DE HERENCIA =================================//

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Persona implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private String id;

    @Column(length = 100,nullable = false)
    private String nombre;

    @Column(length = 100,nullable = false,unique = true)
    private String telefono;

    @Column(name = "password",length = 100,nullable = false)
    private String password;

    @Column(name = "email",length = 100,nullable = false,unique = true)
    private String email;

    //================================= CONSTRUCTOR  =================================//
    public Persona(String id, String nombre, String telefono, String password, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.password = password;
        this.email = email;
    }


}
