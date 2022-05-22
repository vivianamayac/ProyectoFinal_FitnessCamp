package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AdministradorBean implements Serializable {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private TrabajadorServicio trabajadorServicio;

    @Autowired
    private ServicioServicio servicioServicio;

    @Getter @Setter
    private Trabajador trabajador;

    @Getter @Setter
    private String correo;

    @Getter @Setter
    private String passwordN;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Usuario trabajadorLogin;

    @Getter @Setter
    private Producto productoAux;
    @Getter
    @Setter
    private List<Producto> productosRegistrados;

    @Getter
    @Setter
    private List<Trabajador> trabajadores;
    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Servicio servicio;
    @Value("${upload.url}")
    private String urlImagenes;
    private List<Imagen> imagenes;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Getter @Setter
    private String nombreAux;

    @Getter @Setter
    private Administrador administrador;


    @Getter @Setter
    private List<Trabajador> trabajadoresRegistrados;

    @Getter @Setter
    private List<Servicio> serviciosRegistrados;

    @PostConstruct
    public void inicializar() {
        this.trabajador = new Trabajador();
        this.producto = new Producto();
        this.productoAux = new Producto();
        this.servicio = new Servicio();
        this.imagenes = new ArrayList<>();
        this.trabajadores = trabajadorServicio.listarTrabajadores();
        this.administrador = obtenerAdministrador();
        this.trabajadoresRegistrados = obtenerTrabajadoresRegistrados();
        this.productosRegistrados = obtenerProductosRegistrados();
        this.serviciosRegistrados = obtenerServiciosRegistrados();
    }

    public Administrador obtenerAdministrador(){

        Administrador administradorEncontrado = new Administrador();

        if(personaLogin!=null){

            try{

                administradorEncontrado = administradorServicio.obtenerAdministrador(personaLogin.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return administradorEncontrado;
    }

    public List<Trabajador> obtenerTrabajadoresRegistrados(){

        List<Trabajador> trabajadores = new ArrayList<>();

        if (personaLogin!=null) {
            trabajadores = administradorServicio.obtenerTrabajadoresAdmin(personaLogin.getNombre());
        }

        return trabajadores;
    }

    public List<Producto> obtenerProductosRegistrados(){

        List<Producto> productos = new ArrayList<>();

        if (personaLogin!=null){
            productos = administradorServicio.obtenerProductosAdmin(personaLogin.getNombre());
        }

        return productos;
    }

    public List<Servicio> obtenerServiciosRegistrados(){

        List<Servicio> servicios = new ArrayList<>();

        if (personaLogin!=null){
            servicios = administradorServicio.obtenerServiciosAdmin(personaLogin.getNombre());
        }

        return servicios;
    }

    public void registrarTrabajador() {
        try {
            trabajador.setAdministrador((Administrador) personaLogin);
            trabajadorServicio.registrarTrabajador(trabajador);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El trabajador se registro exitosamente");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void actualizarTrabajador(){

        try{
            if(personaLogin!=null){

                Trabajador trabajadorAux= trabajadorServicio.obtenerTrabajadorNombre(nombreAux);

                if (trabajadorAux!=null){

                    trabajador.setAdministrador((Administrador) personaLogin);
                    trabajadorServicio.actualizarTrabajador(trabajador,trabajadorAux.getEmail(),trabajadorAux.getPassword());
                    this.trabajadoresRegistrados = obtenerTrabajadoresRegistrados();
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Update success");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                }else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No se encontraron registros");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
    }

    public void eliminarTrabajador(String email,String password){

        try {
            if (personaLogin!=null ) {
                trabajadorServicio.eliminarTrabajador(email, password);
                this.trabajadoresRegistrados = obtenerTrabajadoresRegistrados();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Worker successfully removed");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public void registrarProducto() {

        try {
            if (personaLogin != null) {
                if (!imagenes.isEmpty()){

                    producto.setAdministrador((Administrador) personaLogin);

                    Producto productoCreado = productoServicio.registrarProducto(this.producto);

                    for (Imagen i : imagenes) {
                        i.setProducto(productoCreado);
                        imagenServicio.registrarImagen(i);
                    }

                    productoCreado.setImagenes(imagenes);

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El producto se registro correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void actualizarProducto() {

        if (personaLogin != null) {

            try {

                productoServicio.actualizarProducto(producto, productoAux.getNombre());
                this.productosRegistrados = obtenerProductosRegistrados();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El producto se actualizo correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No pudimos actualizar el producto");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void eliminarProducto(int idProducto) throws Exception {

        if (personaLogin!=null){
            productoServicio.eliminarProducto(idProducto);
            this.productosRegistrados = obtenerProductosRegistrados();
        }
    }

    public void registrarServicio() {
        try {
            servicio.setAdministrador((Administrador) personaLogin);
            servicioServicio.registrarServicio(servicio);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El servicio se registro exitosamente");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public void subirImagenes(FileUploadEvent event) {

        Imagen foto = new Imagen("default.png");

        imagenes.add(foto);
    }


    public String subirImagen(UploadedFile file) {

        try {
            InputStream input = file.getInputStream();
            String fileName = FilenameUtils.getName(file.getFileName());
            String baseName = FilenameUtils.getBaseName(fileName) + "_";
            String extension = "." + FilenameUtils.getExtension(fileName);
            File fileDest = File.createTempFile(baseName, extension, new File(urlImagenes));
            FileOutputStream output = new FileOutputStream(fileDest);
            IOUtils.copy(input, output);

            return fileDest.getName();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }



}

