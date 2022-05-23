package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;
import co.edu.uniquindio.proyecto.repositorios.ReservaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServicioImpl implements ReservaServicio{

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
            throw new FitnesscampException("No se encontraron coincidencias");
        }
    }

    @Override
    public void eliminarReserva(int id) throws FitnesscampException {

        Reserva reservaEncontrado = obtenerReserva(id);

        if (reservaEncontrado!= null){
            reservaRepo.delete(reservaEncontrado);
        }else {
            throw new FitnesscampException("No se encontraron coincidencias");
        }
    }

    @Override
    public Reserva obtenerReserva(int id) throws FitnesscampException {

        Optional<Reserva> reservaEncontrado = reservaRepo.findById(id);

        if (reservaEncontrado.isEmpty()){

            throw new FitnesscampException("No se encontraron coincidencias");
        }
        return reservaEncontrado.get();
    }


    @Override
    public List<Reserva> listarReservas() {
        return reservaRepo.findAll();
    }
}
