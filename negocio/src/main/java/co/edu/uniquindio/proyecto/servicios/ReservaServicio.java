package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.entidades.Servicio;

import java.util.List;

public interface ReservaServicio {

    Reserva registrarReserva(Reserva r) throws Exception;

    void actualizarReserva(Reserva r,int id) throws Exception;

    void eliminarReserva(int id) throws Exception;

    Reserva obtenerReserva(int id) throws Exception;

    List<Reserva> listarReservas();

}
