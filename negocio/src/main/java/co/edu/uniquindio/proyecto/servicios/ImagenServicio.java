package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Imagen;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.List;

public interface ImagenServicio {

    Imagen registrarImagen(Imagen i) throws FitnesscampException;

    Imagen actualizarImagen(Imagen i) throws FitnesscampException;

    void eliminarImagen (int id) throws FitnesscampException;

    List<Imagen> obtenerImagenesProducto(int idProducto) throws FitnesscampException;

    Imagen obtenerImagen(int id) throws FitnesscampException;

    List<Imagen> listarImagenes() ;

}
