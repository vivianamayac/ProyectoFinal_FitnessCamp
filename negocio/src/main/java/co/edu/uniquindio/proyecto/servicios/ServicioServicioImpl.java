package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServicioImpl implements ServicioServicio{
    public static final String SIN_COINCIDENCIA = "No se encontraron coincidencias en el sistema" ;
    private final ServicioRepo servicioRepo;

    public ServicioServicioImpl(ServicioRepo servicioRepo) {
        this.servicioRepo = servicioRepo;
    }

    @Override
    public Servicio registrarServicio(Servicio s) throws FitnesscampException {

        if (s.getNombre().length() >100){
            throw new FitnesscampException("El nombre no puede exceder los 100 caracteres");
        }

        return servicioRepo.save(s);
    }

    @Override
    public void actualizarServicio(Servicio s, int id) throws FitnesscampException {

        Servicio servicioEncontrado = obtenerServicio(id);

        if (servicioEncontrado!=null){
            servicioRepo.save(servicioEncontrado);
        }else {
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
    }

    @Override
    public void eliminarServicio(int id) throws FitnesscampException {

        Servicio servicioEncontrado = obtenerServicio(id);

        if (servicioEncontrado != null){
            servicioRepo.delete(servicioEncontrado);
        }else {
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
    }

    @Override
    public Servicio obtenerServicio(int id) throws FitnesscampException {

        Optional<Servicio> servicioEncontrado = servicioRepo.findById(id);

        if (servicioEncontrado.isEmpty()){

            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
        return servicioEncontrado.get();
    }

    @Override
    public Servicio obtenerServicioNombre(String nombre) throws FitnesscampException {

        Servicio servicioEncontrado = servicioRepo.obtenerServicioNombre(nombre);

        if (servicioEncontrado==null){

            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
        return servicioEncontrado;
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepo.findAll();
    }
}
