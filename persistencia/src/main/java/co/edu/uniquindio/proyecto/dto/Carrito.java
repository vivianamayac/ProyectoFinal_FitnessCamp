package co.edu.uniquindio.proyecto.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Carrito {

    @EqualsAndHashCode.Include
    private Integer id;
    private Integer unidades;
    private String nombre, imagen;
    private Float precio;

    public Carrito(Integer id, String nombre, String imagen, Integer unidades, Float precio) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.unidades = unidades;
        this.precio = precio;
    }
}
