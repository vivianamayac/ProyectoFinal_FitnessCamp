package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    private final AdministradorRepo administradorRepo;


    public AdministradorServicioImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    public boolean estaDisponible(String email){
        Optional<Administrador> admEmail = administradorRepo.findByEmail(email);

        return admEmail.isPresent();
    }

    @Override
    public Administrador registrarAdministrador(Administrador a) throws Exception {

        if (a.getEmail().length()>100){
            throw new Exception("No puede exceder los 100 caracteres");
        }

        if (a.getNombre().length()>100){
            throw new Exception("No puede exceder los 100 caracteres");
        }

        if (a.getPassword().length()>100){
            throw new Exception("No puede exceder los 100 caracteres");
        }

        if(estaDisponible(a.getEmail())){
            throw new Exception("Ya existe una cuenta vinculada a este correo");
        }

        return administradorRepo.save(a);
    }


    @Override
    public void actualizarAdministrador(Administrador a,String email, String password) throws Exception {

        Administrador administradorObtenido = obtenerEmailPassword(email,password);

        if(administradorObtenido!= null){

            administradorRepo.save(administradorObtenido);
        }
    }

    @Override
    public void eliminarAdministrador(String email) throws Exception {
        Administrador administradorEncontrado = obtenerAdministradorEmail(email);

        if (administradorEncontrado  != null){
           administradorRepo.delete(administradorEncontrado);
        } else {
            throw new Exception("Lo sentimos, no hemos encontrado el administrador");
        }
    }

    @Override
    public Administrador obtenerAdministrador(String id) throws Exception {
        Optional<Administrador> administrador = administradorRepo.findById(id);

        if(administrador.isEmpty()){
            throw new Exception("No existe un admin con la cédula ingresada");
        }

        return administrador.get();
    }

    @Override
    public Administrador obtenerAdministradorEmail(String email) throws Exception {

        Optional<Administrador> administrador = administradorRepo.findByEmail(email);

        if(administrador.isEmpty()){
            throw new Exception("No existe un administrador con los datos ingresados");
        }

        return administrador.get();
    }

    @Override
    public List<Trabajador> obtenerTrabajadoresAdmin(String nombreAdmin){

        return administradorRepo.obtenerTrabajadoresAdmin(nombreAdmin);
    }

    @Override
    public List<Producto> obtenerProductosAdmin(String nombreAdmin) {
        return administradorRepo.obtenerProductosAdmin(nombreAdmin);
    }

    @Override
    public List<Servicio> obtenerServiciosAdmin(String nombreAdmin) {
        return administradorRepo.obtenerServiciosAdmin(nombreAdmin);
    }

    @Override
    public List<Administrador> listarAdministradores() {
        return administradorRepo.findAll();
    }

    @Override
    public Administrador obtenerEmailPassword(String email, String password) throws Exception {

        Administrador administrador =administradorRepo.findByEmailAndPassword(email, password);

        if(administrador == null){

            throw new Exception("No existe una cuenta con estos datos");
        }

        return administrador;
    }

}
