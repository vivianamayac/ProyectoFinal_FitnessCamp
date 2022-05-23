package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Imagen;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Value("#{param['producto']}")
    private String idProducto;

    @Getter
    @Setter
    private Producto producto;


    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private List<String>urlImagenes;


    @PostConstruct
    public void inicializar() {


        if (idProducto!=null && !"".equals(idProducto)){
            try {
                int id = Integer.parseInt(idProducto);

                this.producto = productoServicio.obtenerProducto(id);
                this.urlImagenes = new ArrayList<>();

                List<Imagen> imagenes = producto.getImagenes();

                if(imagenes.size()>0){

                    for(Imagen i:imagenes){

                        urlImagenes.add(i.getUrl());
                    }
                }else{

                    urlImagenes.add("default.png");
                }

                List<Producto>productos=new ArrayList<>();
                productos.add(producto);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
