package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.EmailService;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private Usuario usuarioAuxiliar;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String passwordAux;

    @Autowired
    private EmailService emailService;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter
    @Setter
    private List<Compra> serviciosActivos;

    @Getter @Setter
    private double imc;

    @Getter @Setter
    private double peso;

    @Getter @Setter
    private double porcentajeGrasa;

    @Getter @Setter
    private double estatura;

    @Getter @Setter
    private String medidas;

    @Getter @Setter
    private List<Compra> comprasRegistradas;
    @Setter @Getter
    private List<Servicio> serviciosReservados;

    @PostConstruct
    public void inicializar() {
        this.usuario  = new Usuario();
        this.usuarioAuxiliar = obtenerUsuario();
        this.comprasRegistradas = obtenerComprasUsuario();
        this.serviciosReservados = obtenerServiciosUsuario();
        this.imc = obtenerIMC();
    }

    public Usuario obtenerUsuario(){

        try {
            Usuario u = new Usuario();

            if (personaLogin!=null){
                u = usuarioServicio.obtenerUsuarioNombre(personaLogin.getNombre());

                return u;
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
        return null;
    }

    public List<Compra> obtenerComprasUsuario(){

        List<Compra> compras = new ArrayList<>();

        if (personaLogin!=null){
            compras = usuarioServicio.obtenerComprasUsuario(personaLogin.getNombre());
        }

        return compras;
    }

    public List<Servicio> obtenerServiciosUsuario(){

        List<Servicio> servicios = new ArrayList<>();

        if (personaLogin!=null){
            servicios = usuarioServicio.obtenerServiciosUsuario(personaLogin.getNombre());
        }

        return servicios;
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! te registramos correctamente");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public String actualizarUsuario(){

        try{

            if(personaLogin!=null){

                usuarioServicio.actualizarUsuario(personaLogin.getEmail(),personaLogin.getPassword(), usuario);
                this.usuarioAuxiliar = obtenerUsuario();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                return "/index?faces-redirect=true";
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
        return null;
    }

    public String eliminarUsuario(){

        try {
            if (personaLogin!=null) {

                usuarioServicio.eliminarUsuario(personaLogin.getEmail(),personaLogin.getPassword());

                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                return "/index?faces-redirect=true";
            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
        return null;
    }


    public double obtenerIMC(){

        double imc= 0;

        if (personaLogin !=null){
            try {
                Usuario u = usuarioServicio.obtenerUsuarioNombre(personaLogin.getNombre());
                imc= u.getImc();

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }
        }

        return imc;
    }

    public String registrarInfoNutricional(){

        if (personaLogin!=null){

            try {
                usuarioServicio.registrarInfoNutricional(personaLogin.getId(),peso,medidas,estatura, porcentajeGrasa);
                return "/usuario/perfilUsuario?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
        return null;
    }

    public String enviarEmailRecoverPassword(){

        try {

            Usuario u = usuarioServicio.obtenerUsuarioCorreo(email);

            if (u!=null){
                String receptor = u.getEmail();
                String asunto = "Recover password";
                String mensaje = "<span style=\"color:black;font-size: 20px;font-family:'Times New Roman', sans-serif\"><b>Recover password</b></span>";
                mensaje += "<br><br> Enter the following link:";
                mensaje += "<br><br><span style=\"color:blue\"><b>http://fitnesstcamp3.azurewebsites.net/recoverPassword.xhtml/b></span><br><br>";
                emailService.enviarEmail(asunto,mensaje,receptor);

                return "/index?faces-redirect=true";
            }else{
                throw new Exception("No se encontraron registros");
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return null;
    }

    public String recoverPassword(){

        try {
            Usuario u = usuarioServicio.obtenerUsuarioCorreo(email);

            usuarioServicio.recoverPassword(u.getEmail(),passwordAux);
            String mensaje = "<span style=\"color:black;font-size: 20px;font-family:'Times New Roman', sans-serif\"><b>Successful password change</b></span>";
            mensaje += "<br><br>Your password was updated!";
            mensaje += "<br><br>Enjoy our services in <span style=\"color:blue\">Aquí va el link :v</span>";
            emailService.enviarEmail("Confirmation",mensaje,u.getEmail());

            return "/signin?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
