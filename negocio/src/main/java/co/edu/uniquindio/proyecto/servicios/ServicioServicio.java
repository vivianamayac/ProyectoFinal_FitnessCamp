package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ServicioServicio {

    Servicio registrarServicio(Servicio s) throws Exception;

    void actualizarServicio(Servicio s,int id) throws Exception;

    void eliminarServicio(int id) throws Exception;

    Servicio obtenerServicio(int id) throws Exception;

    Servicio obtenerServicioNombre(String nombre) throws Exception;

    List<Servicio> listarServicios();

}
