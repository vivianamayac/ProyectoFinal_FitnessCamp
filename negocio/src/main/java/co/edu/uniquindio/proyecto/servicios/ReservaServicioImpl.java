package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Reserva;
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
    public Reserva registrarReserva(Reserva r) throws Exception {

        return reservaRepo.save(r);
    }

    @Override
    public void actualizarReserva(Reserva r, int id) throws Exception {

        Reserva reservaEncontrado = obtenerReserva(id);

        if (reservaEncontrado!=null){
            reservaRepo.save(reservaEncontrado);
        }else {
            throw new Exception("No se encontraron coincidencias");
        }
    }

    @Override
    public void eliminarReserva(int id) throws Exception {

        Reserva reservaEncontrado = obtenerReserva(id);

        if (reservaEncontrado!= null){
            reservaRepo.delete(reservaEncontrado);
        }else {
            throw new Exception("No se encontraron coincidencias");
        }
    }

    @Override
    public Reserva obtenerReserva(int id) throws Exception {

        Optional<Reserva> reservaEncontrado = reservaRepo.findById(id);

        if (reservaEncontrado.isEmpty()){

            throw new Exception("No se encontraron coincidencias");
        }
        return reservaEncontrado.get();
    }


    @Override
    public List<Reserva> listarReservas() {
        return reservaRepo.findAll();
    }
}
