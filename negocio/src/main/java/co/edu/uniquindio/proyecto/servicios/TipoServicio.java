package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.List;

public interface TipoServicio {

    TipoTrabajador registrarTipo(TipoTrabajador t) throws FitnesscampException;

    void  actualizarTipo(TipoTrabajador t, int idTipo) throws FitnesscampException;

    void eliminarTipo(int id) throws FitnesscampException;

    TipoTrabajador obtenerTipo(int id) throws FitnesscampException;

    List<TipoTrabajador> listarTipos();


}
