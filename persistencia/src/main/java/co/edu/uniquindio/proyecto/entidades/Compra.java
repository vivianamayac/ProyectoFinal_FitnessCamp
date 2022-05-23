package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(length = 100)
    private Date fechaVenta;

    @Column(nullable = false)
    private String medioPago;

    //================================= RELACIÓN CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Usuario usuario;

    //================================= RELACIÓN CON LA ENTIDAD DETALLE COMPRA =================================//
    @OneToMany (mappedBy = "compra", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DetalleCompra> listaDetallesCompra;

    public Compra(Date fechaVenta, Usuario usuario,String medioPago) {
        this.fechaVenta = fechaVenta;
        this.usuario = usuario;
        this.medioPago = medioPago;
        this.listaDetallesCompra = new ArrayList<>();
    }

}
