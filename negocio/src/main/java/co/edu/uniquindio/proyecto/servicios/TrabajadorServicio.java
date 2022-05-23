package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.List;

public interface TrabajadorServicio {

    Trabajador registrarTrabajador(Trabajador t) throws FitnesscampException;

    void actualizarTrabajador(Trabajador t,String email,String password) throws FitnesscampException;

    void eliminarTrabajador(String email,String password) throws FitnesscampException;

    Trabajador obtenerTrabajador(String id) throws FitnesscampException;

    Trabajador obtenerTrabajadorNombre(String nombre) throws FitnesscampException;

    List<Trabajador> listarTrabajadores();

    Trabajador obtenerEmailPassword(String email, String password) throws FitnesscampException;
}
