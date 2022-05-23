package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.Carrito;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;
    private final UsuarioRepo usuarioRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final ProductoRepo productoRepo;

    public CompraServicioImpl(CompraRepo compraRepo, UsuarioRepo usuarioRepo, DetalleCompraRepo detalleCompraRepo, ProductoRepo productoRepo) {
        this.compraRepo = compraRepo;
        this.usuarioRepo = usuarioRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.productoRepo = productoRepo;

    }

    @Override
    public Compra crearCompra(Compra c)  {

       return compraRepo.save(c);

    }

    @Override
    public Compra agregarDetalleCompra(Compra compra, DetalleCompra detalle) {
        compra.getListaDetallesCompra().add(detalle);
        return compra;
    }

    @Override
    public Compra agregarCompra(ArrayList<Carrito> productoCarrito, Usuario usuario, String medioPago) throws FitnesscampException {
        try {
            Compra compra = new Compra();
            compra.setFechaVenta(new Date());
            compra.setUsuario(usuario);
            compra.setMedioPago(medioPago);

            compraRepo.save(compra);

            DetalleCompra detalle;
            List<DetalleCompra> lista = new ArrayList<>();
            for (Carrito p : productoCarrito) {
                detalle = new DetalleCompra();
                if(detalleCompraRepo.verificarUnidadesProducto(p.getId()) > p.getUnidades()){
                    detalle.setCompra(compra);
                    detalle.setPrecioProducto(p.getPrecio());
                    detalle.setUnidades(p.getUnidades());
                    detalle.setProducto(productoRepo.findById(p.getId()).orElse(null));
                    quitarUnidades(p.getId(), p.getUnidades());
                    detalleCompraRepo.save(detalle);
                    lista.add(detalle);
                    compra.setListaDetallesCompra(lista);
                }
            }
            return compra;
        } catch (Exception e) {
            throw new FitnesscampException(e.getMessage());
        }

    }

    public void quitarUnidades (Integer codigoProducto, Integer unidadesComprada){
       Optional<Producto> producto = productoRepo.findById(codigoProducto);
        if (producto.isPresent()) {
            Producto productoActual =producto.get();
            productoActual.setUnidades(productoActual.getUnidades() - unidadesComprada );
        }

    }

    public Compra obtenerCompra(int idCompra) throws FitnesscampException{

        Optional<Compra> compraEncontrada = compraRepo.findById(idCompra);

        if (compraEncontrada.isEmpty()){
            throw new FitnesscampException("La compra no existe");
        }

        return compraEncontrada.get();
    }

    @Override
    public List<Compra> listarComprasUsuario(int idUsuario) {
        return compraRepo.listarComprasUsuario(idUsuario);
    }

    @Override
    public Compra obtenerCompraUsuario(int idUsuario, int idCompra) throws FitnesscampException {

        Optional<Usuario> u = usuarioRepo.findById(idUsuario);
        Optional<Compra> c = compraRepo.findById(idCompra);

        if (u.isEmpty()){
            throw new FitnesscampException("El usuario no existe");
        }

        if (c.isEmpty()){
            throw new FitnesscampException("La compra no existe");
        }

        Compra compraU = compraRepo.obtenerCompraUsuario(Integer.parseInt(u.get().getId()),c.get().getId());

        if (compraU == null) {
            throw new FitnesscampException("El usuario no cuenta con esta compra");
        }

        return compraU;
    }

}
