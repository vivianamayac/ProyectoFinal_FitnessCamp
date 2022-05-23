package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;
import co.edu.uniquindio.proyecto.repositorios.ReservaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServicioImpl implements ReservaServicio{

    public static final String SIN_COINCIDENCIA = "No se encontraron coincidencias en el sistema" ;
    private final ReservaRepo reservaRepo;

    public ReservaServicioImpl(ReservaRepo reservaRepo) {
        this.reservaRepo = reservaRepo;
    }

    @Override
    public Reserva registrarReserva(Reserva r) throws FitnesscampException {

        return reservaRepo.save(r);
    }

    @Override
    public void actualizarReserva(Reserva r, int id) throws FitnesscampException {

        Reserva reservaEncontrado = obtenerReserva(id);

        if (reservaEncontrado!=null){
            reservaRepo.save(reservaEncontrado);
        }else {
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
    }

    @Override
    public void eliminarReserva(int id) throws FitnesscampException {

        Reserva reservaEncontrado = obtenerReserva(id);

        if (reservaEncontrado!= null){
            reservaRepo.delete(reservaEncontrado);
        }else {
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
    }

    @Override
    public Reserva obtenerReserva(int id) throws FitnesscampException {

        Optional<Reserva> reservaEncontrado = reservaRepo.findById(id);

        if (reservaEncontrado.isEmpty()){

            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
        return reservaEncontrado.get();
    }


    @Override
    public List<Reserva> listarReservas() {
        return reservaRepo.findAll();
    }
}
