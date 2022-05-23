package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CiudadServicioImpl implements CiudadServicio{

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public Ciudad registrarCiudad(Ciudad ciudad) throws FitnesscampException {

        if (ciudad.getNombre().length()>100){
            throw new FitnesscampException("No puede exceder los 100 caracteres");
        }

        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad actualizarCiudad(Ciudad ciudad) throws FitnesscampException {

        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(int id) throws FitnesscampException {

        Ciudad ciudadEncontrada=obtenerCiudad(id);

        if (ciudadEncontrada != null){
            ciudadRepo.delete(ciudadEncontrada);
        }else{
            throw new FitnesscampException("No existe la ciudad");
        }
    }

    @Override
    public Ciudad obtenerCiudad(int id) throws FitnesscampException {

        Optional<Ciudad> ciudad= ciudadRepo.findById(id);

        if (ciudad.isEmpty()){
            throw new FitnesscampException("La ciudad no existe");
        }

        return ciudad.get();
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }
}
