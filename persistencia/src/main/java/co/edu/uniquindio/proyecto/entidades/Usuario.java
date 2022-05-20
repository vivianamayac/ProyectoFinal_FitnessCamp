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
public class Usuario extends Persona implements Serializable {

    @Column(name = "numero_tarjeta",length = 100)
    private String numeroTarjeta;

    @Column(name = "fecha_tarjeta",length = 100)
    private Date fechaTarjeta;

    @Column(name = "codigo_tarjeta",length = 100)
    private String codigoTarjeta;

    private double peso;

    private String medidas;

    private double porcentajeGrasa;

    private double estatura;

    private double imc;

    //================================= RELACIÓN CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Reserva> reservas;

    //================================= RELACIÓN CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Compra> compras;

    //================================= CONSTRUCTOR  =================================//


    public Usuario(String id, String nombre, String telefono, String password, String email) {
        super(id, nombre, telefono, password, email);
        this.reservas = new ArrayList<>();
        this.compras = new ArrayList<>();
    }
}
