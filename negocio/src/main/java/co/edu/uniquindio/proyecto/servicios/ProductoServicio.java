package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.List;

public interface ProductoServicio {

    Producto registrarProducto(Producto p) throws FitnesscampException;

    void actualizarProducto(Producto p,String nombre) throws FitnesscampException;

    void eliminarProducto(int id) throws FitnesscampException;

    Producto obtenerProducto(int id) throws FitnesscampException;

    List<Producto> buscarProductos(String cadena);

    Producto obtenerProductoNombre(String nombre) throws FitnesscampException;

    List<Producto> listarProductos();

}
