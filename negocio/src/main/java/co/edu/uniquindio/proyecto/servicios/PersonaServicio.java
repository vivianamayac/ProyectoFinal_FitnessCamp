package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

public interface PersonaServicio {

    Persona login(String email,String password) throws FitnesscampException;

    Persona obtenerPersonaEmail(String email) throws FitnesscampException;

    void cambiarPassword(String email,String passwordN) throws FitnesscampException;
}
