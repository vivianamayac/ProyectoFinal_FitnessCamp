package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrabajadorServicioImpl implements TrabajadorServicio {
    public static final String SIN_COINCIDENCIA = "No se encontraron coincidencias en el sistema" ;
    private final TrabajadorRepo trabajadorRepo;
    private final AdministradorRepo administradorRepo;
    private final ServicioRepo servicioRepo;


    public TrabajadorServicioImpl(TrabajadorRepo trabajadorRepo, AdministradorRepo administradorRepo, ServicioRepo servicioRepo) {
        this.trabajadorRepo = trabajadorRepo;
        this.administradorRepo = administradorRepo;
        this.servicioRepo = servicioRepo;
    }

    public boolean estaDisponible(String email){
        Optional<Trabajador> trabajadorEmail = trabajadorRepo.findByEmail(email);

        return  trabajadorEmail.isPresent();

    }

    @Override
    public Trabajador registrarTrabajador(Trabajador t) throws FitnesscampException {

        if (t.getEmail().length()>100){
            throw new FitnesscampException("No puede exceder los 100 caracteres");
        }

        if (t.getNombre().length()>100){
            throw new FitnesscampException("No puede exceder los 100 caracteres");
        }

        if (t.getPassword().length()>100){
            throw new FitnesscampException("No puede exceder los 100 caracteres");
        }

        if(estaDisponible(t.getEmail())){
            throw new FitnesscampException("El empleado ya existe");
        }

        return trabajadorRepo.save(t);
    }

    @Override
    public void actualizarTrabajador(Trabajador t,String email,String password) throws FitnesscampException {

        Trabajador aux = obtenerEmailPassword(email,password);

        if (aux != null){

            aux.setNombre(t.getNombre());
            aux.setTelefono(t.getTelefono());
            aux.setEmail(t.getEmail());

            trabajadorRepo.save(aux);
        }else {
            throw new FitnesscampException("No se encontraron resultados");
        }
    }

    @Override
    public void eliminarTrabajador(String email,String password) throws FitnesscampException {

        Trabajador trabajadorEncontrado = obtenerEmailPassword(email,password);

        if (trabajadorEncontrado != null){

            Administrador admin = trabajadorEncontrado.getAdministrador();
            admin.getTrabajadores().remove(trabajadorEncontrado);
            trabajadorEncontrado.setAdministrador(null);

            List<Servicio> serviciosVinculados = trabajadorEncontrado.getServicios();

            if(serviciosVinculados!=null && serviciosVinculados.size()!=0){

                for(Servicio s:trabajadorEncontrado.getServicios()){
                    s.setTrabajador(null);
                    servicioRepo.save(s);
                }

                trabajadorEncontrado.getServicios().clear();
            }

            administradorRepo.save(admin);
            trabajadorRepo.delete(trabajadorEncontrado);
        } else {
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
    }

    @Override
    public Trabajador obtenerTrabajador(String id) throws FitnesscampException {

        Optional<Trabajador> trabajador = trabajadorRepo.findById(id);

        if(trabajador.isEmpty()){
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }

        return trabajador.get();
    }

    @Override
    public Trabajador obtenerTrabajadorNombre(String nombre) throws FitnesscampException {

        Optional<Trabajador> trabajador = trabajadorRepo.findByNombre(nombre);

        if(trabajador.isEmpty()){
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }

        return trabajador.get();
    }

    @Override
    public Trabajador obtenerEmailPassword(String email, String password) throws FitnesscampException {

        Trabajador trabajador = trabajadorRepo.findByEmailAndPassword(email,password);

        if(trabajador == null){

            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
        return trabajador;
    }


    @Override
    public List<Trabajador> listarTrabajadores() {

        return trabajadorRepo.findAll();
    }
}
