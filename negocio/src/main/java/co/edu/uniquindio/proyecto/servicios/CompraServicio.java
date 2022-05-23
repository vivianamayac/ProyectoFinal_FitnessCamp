package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.dto.Carrito;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.ArrayList;
import java.util.List;

public interface CompraServicio {

    Compra crearCompra(Compra c) throws FitnesscampException;

    Compra agregarDetalleCompra(Compra compra, DetalleCompra detalle);

    Compra agregarCompra(ArrayList<Carrito> productoCarrito, Usuario usuario, String medioPago) throws FitnesscampException;

    List<Compra> listarComprasUsuario (int idUsuario);

    Compra obtenerCompraUsuario(int idUsuario,int idCompra) throws FitnesscampException;

}
