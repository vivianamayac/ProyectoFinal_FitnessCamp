package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.List;

public interface ServicioServicio {

    Servicio registrarServicio(Servicio s) throws FitnesscampException;

    void actualizarServicio(Servicio s,int id) throws FitnesscampException;

    void eliminarServicio(int id) throws FitnesscampException;

    Servicio obtenerServicio(int id) throws FitnesscampException;

    Servicio obtenerServicioNombre(String nombre) throws FitnesscampException;

    List<Servicio> listarServicios();

}
