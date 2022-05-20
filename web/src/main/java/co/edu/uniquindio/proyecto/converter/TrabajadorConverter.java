package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Trabajador;
import co.edu.uniquindio.proyecto.servicios.TrabajadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class TrabajadorConverter implements Converter<Trabajador>, Serializable {

    @Autowired
    private TrabajadorServicio trabajadorServicio;


    @Override
    public Trabajador getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            if (s!=null && !"".equals(s)){

                int id = Integer.parseInt(s);
                return trabajadorServicio.obtenerTrabajador(String.valueOf(id));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent,Trabajador trabajador) {

        if (trabajador!=null){
            return ""+trabajador.getId();
        }
        return "";
    }
}
