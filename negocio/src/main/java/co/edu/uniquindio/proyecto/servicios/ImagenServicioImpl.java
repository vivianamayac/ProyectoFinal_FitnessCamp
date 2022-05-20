package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ImagenRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenServicioImpl implements ImagenServicio{

    private final ImagenRepo imagenRepo;
    private final ProductoRepo productoRepo;

    public ImagenServicioImpl(ImagenRepo imagenRepo, ProductoRepo productoRepo) {
        this.imagenRepo = imagenRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    public Imagen registrarImagen(Imagen i) throws Exception {

        if (i.getUrl().length() > 100){
            throw  new Exception("No puede exceder los 100 caracteres");
        }

        return imagenRepo.save(i);
    }

    @Override
    public Imagen actualizarImagen(Imagen i) throws Exception {

        if (i.getUrl().length() > 100){
            throw  new Exception("No puede exceder los 100 caracteres");
        }

        return imagenRepo.save(i);
    }

    @Override
    public void eliminarImagen(int id) throws Exception {

        Imagen imagenEncontrada = obtenerImagen(id);

        if(imagenEncontrada != null){
            imagenRepo.delete(imagenEncontrada);
        }else {
            throw new Exception("No existe la url");
        }
    }

    @Override
    public List<Imagen> obtenerImagenesProducto(int idProducto) throws Exception {

        List<Imagen> imagenes = productoRepo.obtenerImagenes(idProducto) ;

        if(imagenes ==null){
            throw new Exception("El producto no cuenta con imágenes");
        }

        return imagenes;
    }

    @Override
    public Imagen obtenerImagen(int id) throws Exception {

        Optional<Imagen> imagen = imagenRepo.findById(id);

        if(imagen.isEmpty()){
            throw  new Exception("No se encontró la url");
        }
        return imagen.get();
    }

    @Override
    public List<Imagen> listarImagenes() {
        return imagenRepo.findAll();
    }
}
