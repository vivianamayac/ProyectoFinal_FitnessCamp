package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServicioImpl implements ServicioServicio{

    private final ServicioRepo servicioRepo;

    public ServicioServicioImpl(ServicioRepo servicioRepo) {
        this.servicioRepo = servicioRepo;
    }

    @Override
    public Servicio registrarServicio(Servicio s) throws Exception {

        if (s.getNombre().length() >100){
            throw new Exception("El nombre no puede exceder los 100 caracteres");
        }

        return servicioRepo.save(s);
    }

    @Override
    public void actualizarServicio(Servicio s, int id) throws Exception {

        Servicio servicioEncontrado = obtenerServicio(id);

        if (servicioEncontrado!=null){
            servicioRepo.save(servicioEncontrado);
        }else {
            throw new Exception("No se encontraron coincidencias");
        }
    }

    @Override
    public void eliminarServicio(int id) throws Exception {

        Servicio servicioEncontrado = obtenerServicio(id);

        if (servicioEncontrado != null){
            servicioRepo.delete(servicioEncontrado);
        }else {
            throw new Exception("No se encontraron coincidencias");
        }
    }

    @Override
    public Servicio obtenerServicio(int id) throws Exception {

        Optional<Servicio> servicioEncontrado = servicioRepo.findById(id);

        if (servicioEncontrado.isEmpty()){

            throw new Exception("No se encontraron coincidencias");
        }
        return servicioEncontrado.get();
    }

    @Override
    public Servicio obtenerServicioNombre(String nombre) throws Exception {

        Servicio servicioEncontrado = servicioRepo.obtenerServicioNombre(nombre);

        if (servicioEncontrado==null){

            throw new Exception("No se encontraron coincidencias");
        }
        return servicioEncontrado;
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepo.findAll();
    }
}
