package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;
import co.edu.uniquindio.proyecto.repositorios.ImagenRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl  implements ProductoServicio {

    public static final String SIN_COINCIDENCIA = "No se encontraron coincidencias en el sistema" ;
    private final ProductoRepo productoRepo;
    private final ImagenRepo imagenRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ImagenRepo imagenRepo) {
        this.productoRepo = productoRepo;
        this.imagenRepo = imagenRepo;
    }

    @Override
    public Producto registrarProducto(Producto p) throws FitnesscampException {

        if (p.getNombre().length() >100){
            throw new FitnesscampException("El nombre no puede exceder los 100 caracteres");
        }
        return productoRepo.save(p);
    }

    @Override
    public void actualizarProducto(Producto p, String nombre) throws FitnesscampException {

        Producto producto = obtenerProductoNombre(nombre);

        if (producto!=null){

            producto.setNombre(p.getNombre());
            producto.setImagenes(p.getImagenes());
            producto.setUnidades(p.getUnidades());
            producto.setPrecio(p.getPrecio());
            producto.setDescription(p.getDescription());
            productoRepo.save(producto);
        }else {
            throw new FitnesscampException(SIN_COINCIDENCIA );
        }

    }

    @Override
    public void eliminarProducto(int id) throws FitnesscampException {

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
            throw new FitnesscampException("No se encontraron registros");
        }
    }

    @Override
    public Producto obtenerProducto(int id) throws FitnesscampException {

        Optional<Producto> productoEncontrado = productoRepo.findById(id);

        if (productoEncontrado.isEmpty()){
            throw new FitnesscampException(SIN_COINCIDENCIA );
        }

        return productoEncontrado.get();
    }

    @Override
    public Producto obtenerProductoNombre(String nombre) throws FitnesscampException {

        Producto productoEncontrado = productoRepo.obtenerProductoNombre(nombre);

        if (productoEncontrado==null){
            throw new FitnesscampException(SIN_COINCIDENCIA );
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
