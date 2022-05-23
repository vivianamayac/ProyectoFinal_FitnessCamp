package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.entidades.Servicio;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ReservaServicio;
import co.edu.uniquindio.proyecto.servicios.ServicioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class ServicioBean implements Serializable {

    @Autowired
    private ServicioServicio servicio;

    @Getter
    @Setter
    private List<Servicio> servicios;

    @Getter
    @Setter
    private Reserva reserva;

    @Autowired
    private ReservaServicio reservaServicio;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @PostConstruct
    public void inicializar() {
        this.servicios = servicio.listarServicios();
        this.reserva = new Reserva();
    }

    public String irADetalle(Integer id){
      return  "/detalleProducto?faces-redirect=true&amp;producto="+id;
    }

    public void registrarReserva() {
        try {
            reserva.setUsuario((Usuario) personaLogin);
            reservaServicio.registrarReserva(reserva);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! la reserva quedo registrada");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "La reserva se ha registrado satisfactoriamente");

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void metods(){

        registrarReserva();
        showMessage();
    }

}
