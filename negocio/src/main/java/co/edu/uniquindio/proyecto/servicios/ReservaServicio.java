package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.List;

public interface ReservaServicio {

    Reserva registrarReserva(Reserva r) throws FitnesscampException;

    void actualizarReserva(Reserva r,int id) throws FitnesscampException;

    void eliminarReserva(int id) throws FitnesscampException;

    Reserva obtenerReserva(int id) throws FitnesscampException;

    List<Reserva> listarReservas();

}
