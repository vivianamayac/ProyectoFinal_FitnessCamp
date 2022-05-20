package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class inicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Getter
    @Setter
    private List<Producto> productos;


    @PostConstruct
    public void inicializar() {
          this.productos = productoServicio.listarProductos();
    }

    public String irADetalle(Integer id){
      return "/detalleProducto?faces-redirect=true&amp;producto="+id;
    }

}
