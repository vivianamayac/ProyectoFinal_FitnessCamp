package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ImagenRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl  implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final ImagenRepo imagenRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ImagenRepo imagenRepo) {
        this.productoRepo = productoRepo;
        this.imagenRepo = imagenRepo;
    }

    @Override
    public Producto registrarProducto(Producto p) throws Exception {

        if (p.getNombre().length() >100){
            throw new Exception("El nombre no puede exceder los 100 caracteres");
        }
        return productoRepo.save(p);
    }

    @Override
    public void actualizarProducto(Producto p, String nombre) throws Exception {

        Producto producto = obtenerProductoNombre(nombre);

        if (producto!=null){

            producto.setNombre(p.getNombre());
            producto.setImagenes(p.getImagenes());
            producto.setUnidades(p.getUnidades());
            producto.setPrecio(p.getPrecio());
            producto.setDescription(p.getDescription());
            productoRepo.save(producto);
        }else {
            throw new Exception("No se encontraron coincidencias");
        }

    }

    @Override
    public void eliminarProducto(int id) throws Exception {

        Producto productoEncontrado = obtenerProducto(id);

        if (productoEncontrado!=null){

            if(productoEncontrado.getImagenes()!=null && productoEncontrado.getImagenes().size()>0){

                imagenRepo.deleteAll(productoEncontrado.getImagenes());

                productoEncontrado.getImagenes().clear();

            }

            productoEncontrado.setAdministrador(null);

            productoRepo.save(productoEncontrado);
            productoRepo.delete(productoEncontrado);
        }else{
            throw new Exception("No se encontraron registros");
        }
    }

    @Override
    public Producto obtenerProducto(int id) throws Exception {

        Optional<Producto> productoEncontrado = productoRepo.findById(id);

        if (productoEncontrado.isEmpty()){
            throw new Exception("No se encontraron coincidencias");
        }

        return productoEncontrado.get();
    }

    @Override
    public Producto obtenerProductoNombre(String nombre) throws Exception {

        Producto productoEncontrado = productoRepo.obtenerProductoNombre(nombre);

        if (productoEncontrado==null){
            throw new Exception("No se encontraron coincidencias");
        }

        return productoEncontrado;
    }

    @Override
    public List<Producto> buscarProductos(String cadena) {
        return productoRepo.busquedaProductosNombre(cadena);
    }


    @Override
    public List<Producto> listarProductos() {
        return productoRepo.findAll();
    }
}
