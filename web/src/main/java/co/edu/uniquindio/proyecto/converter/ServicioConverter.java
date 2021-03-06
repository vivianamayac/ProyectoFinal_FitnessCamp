package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ServicioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class ServicioConverter implements Converter<Servicio>, Serializable {

    @Autowired
    private ServicioServicio servicioServicio;


    @Override
    public Servicio getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            if (s!=null && !"".equals(s)){

                int id = Integer.parseInt(s);
                return servicioServicio.obtenerServicio(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Servicio servicio) {

        if (servicio!=null){
            return ""+servicio.getId();
        }
        return "";
    }
}
