package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.Carrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private Persona persona;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private Persona personaAux;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private  boolean autenticado;

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private String email,emailR,password;

    @Getter @Setter
    private String rol;

    @Getter @Setter
    private String nombreCompleto;

    @Getter
    @Setter
    private ArrayList<Carrito> productosCarrito;

    @Getter
    @Setter
    private List<Compra> listaMisCompras;

    @Getter
    @Setter
    private double subtotal;

    @Autowired
    private CompraServicio compraServicio;

    @Getter
    @Setter
    private ArrayList<String> mediosPago;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private String numTarjeta;

    @Getter @Setter
    private String codTarjeta;
    @Getter @Setter
    private Date fechaTarjeta;

    @PostConstruct
    public void inicializar() {
        autenticado = false;
        this.productosCarrito = new ArrayList<>();
        this.mediosPago = new ArrayList<>();
        mediosPago.add("Credit card");
        this.subtotal = 0.0;
        this.usuario = new Usuario();
        listaMisCompras = new ArrayList<>();
        this.ciudades = ciudadServicio.listarCiudades();
    }


    public String iniciarSesion(){

        if (email!=null && password!=null) {
            try {
                persona = personaServicio.login(email,password);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! ingreso correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                if (persona instanceof Usuario){
                    rol="usuario";
                }else if (persona instanceof Administrador){
                    rol="admin";
                }

                autenticado=true;
               return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
        return null;
    }

    public String cerrarSesion(){

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public List<Compra> listarComprasUsuario() {
        if (persona != null) {
            listaMisCompras = compraServicio.listarComprasUsuario(Integer.parseInt(persona.getId()));
            return listaMisCompras;
        }
        return null;
    }

    public Double calcularSubTotal(int indice) {

        listaMisCompras = compraServicio.listarComprasUsuario(Integer.parseInt(persona.getId()));
        double total = 0.0;

        for (int i = 0; i < listaMisCompras.get(indice).getListaDetallesCompra().size(); i++) {
            total = listaMisCompras.get(indice).getListaDetallesCompra().get(i).getUnidades() * listaMisCompras.get(indice).getListaDetallesCompra().get(i).getProducto().getPrecio();
        }
        return total;
    }

    public void agregarAlCarrito(Integer codigo, Float precio, String nombre, String imagen) {
        Carrito c = new Carrito(codigo, nombre, imagen, 1, precio);
        if (!productosCarrito.contains(c)) {
            productosCarrito.add(c);
            this.subtotal= subtotal + (c.getPrecio() * c.getUnidades());
            this.subtotal = Math.round(subtotal*1000.0)/1000.0;
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "El producto se ha agregado a carrito");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", fm);
        }
    }

    public void eliminarDelCarrito(int indice) {
        this.subtotal -= productosCarrito.get(indice).getPrecio();
        this.subtotal = Math.round(subtotal*1000.0)/1000.0;
        productosCarrito.remove(indice);

        if (productosCarrito.isEmpty()){
            this.subtotal = 0.0;
        }
    }

    public void actualizarSubtotal() {
        this.subtotal = 0.0;
        for (Carrito p : productosCarrito) {
            this.subtotal += p.getPrecio() * p.getUnidades();
            this.subtotal = Math.round(subtotal*1000.0)/1000.0;
        }
    }

    public void comprar() {
        if (persona!= null && !productosCarrito.isEmpty()) {
            try {
                if (mediosPago.contains("Credit card")) {
                    compraServicio.agregarCompra(productosCarrito, (Usuario) persona, "Credit card");
                    productosCarrito.clear();
                    this.subtotal = 0.0;
                    this.subtotal = Math.round(subtotal*1000.0)/1000.0;

                } else {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Debe seleccionar un medio de pago para efectuar la compra");
                    FacesContext.getCurrentInstance().addMessage("msj-compra", fm);
                }
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "La compra no se ha podido efectuar correctamente: " + e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-compra", fm);
            }
        }
    }

    public void registrarTarjeta(){

        try {
            Usuario usuarioEncontrado = usuarioServicio.obtenerUsuarioNombre(usuario.getNombre());

            usuarioServicio.registrarTarjetaUsuario(usuarioEncontrado.getId(),numTarjeta,codTarjeta,fechaTarjeta);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public String comprarM(){

        registrarTarjeta();
        comprar();

        return "/usuario/buy?faces-redirect=true";
    }


}
