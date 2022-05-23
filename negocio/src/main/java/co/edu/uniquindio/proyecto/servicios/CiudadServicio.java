package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.List;

public interface CiudadServicio {

    Ciudad registrarCiudad(Ciudad ciudad) throws FitnesscampException;

    Ciudad actualizarCiudad(Ciudad ciudad) throws FitnesscampException;

    void eliminarCiudad(int id) throws FitnesscampException;

    Ciudad obtenerCiudad(int id) throws FitnesscampException;

    List<Ciudad> listarCiudades();
}
