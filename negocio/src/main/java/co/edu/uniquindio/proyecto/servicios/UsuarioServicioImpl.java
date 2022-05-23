package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    public static final String SIN_COINCIDENCIA = "No se encontraron coincidencias en el sistema" ;
    private final UsuarioRepo usuarioRepo;


    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public boolean estaDisponible(String email){
        Usuario usuarioEmail = usuarioRepo.findByEmail(email);

        return usuarioEmail != null;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws FitnesscampException {

        if (u.getEmail().length()>100){
            throw new FitnesscampException("No puede exceder los 100 caracteres");
        }

        if (u.getNombre().length()>100){
            throw new FitnesscampException("No puede exceder los 100 caracteres");
        }

        if (u.getPassword().length()>100){
            throw new FitnesscampException("No puede exceder los 100 caracteres");
        }

        if(estaDisponible(u.getEmail())){
            throw new FitnesscampException("El usuario ya existe");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public void registrarTarjetaUsuario(String idUsuario,String numero, String codigo, Date fecha) throws FitnesscampException {

        Usuario usuarioEncontrado= obtenerUsuario(idUsuario);

        if (usuarioEncontrado!=null){

            usuarioEncontrado.setNumeroTarjeta(numero);
            usuarioEncontrado.setCodigoTarjeta(codigo);
            usuarioEncontrado.setFechaTarjeta(fecha);
            usuarioRepo.save(usuarioEncontrado);
        }else{
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
    }

    @Override
    public void actualizarUsuario(String email, String password,Usuario u) throws FitnesscampException {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email, password);

        if (usuarioEncontrado != null) {

            usuarioEncontrado.setNombre(u.getNombre());
            usuarioEncontrado.setTelefono(u.getTelefono());
            usuarioEncontrado.setEmail(u.getEmail());

            usuarioRepo.save(usuarioEncontrado);
        }
    }

    @Override
    public void eliminarUsuario(String email,String password) throws FitnesscampException {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email,password) ;

        if (usuarioEncontrado != null){
            usuarioRepo.delete(usuarioEncontrado);
        }else{
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }
    }

    @Override
    public Usuario obtenerUsuario(String id) throws FitnesscampException {

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()){
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }

        return usuario.get();
    }

    @Override
    public Usuario obtenerUsuarioNombre(String nombre) throws FitnesscampException {

        Usuario usuario = usuarioRepo.findByNombre(nombre);

        if(usuario == null){
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }

        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioCorreo(String correo) throws FitnesscampException {

        Usuario usuario = usuarioRepo.findByEmail(correo);

        if(usuario == null){
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }

        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioEmailPassword(String email,String password) throws FitnesscampException {

        Usuario usuario = usuarioRepo.findByEmailAndPassword(email,password);

        if(usuario==null){
            throw new FitnesscampException(SIN_COINCIDENCIA);
        }

        return usuario;
    }

    @Override
    public void registrarInfoNutricional(String idU, double peso, String medidas, double estatura, double porcentajeGrasa) throws FitnesscampException {

        Usuario u = obtenerUsuario(idU);
        double imc = 0;

        if (u!=null){
            u.setPeso(peso);
            u.setMedidas(medidas);
            u.setEstatura(estatura);
            u.setPorcentajeGrasa(porcentajeGrasa);
            imc = u.getPeso()/(Math.pow(u.getEstatura(),2));
            u.setImc(imc);
            usuarioRepo.save(u);
        }

    }

    @Override
    public List<Compra> obtenerComprasUsuario(String nombreUsuario) {
        return usuarioRepo.obtenerComprasUsuario(nombreUsuario);
    }

    @Override
    public List<Servicio> obtenerServiciosUsuario(String nombreUsuario) {
        return usuarioRepo.obtenerServiciosUsuario(nombreUsuario);
    }

    @Override
    public void recoverPassword(String email, String password) throws FitnesscampException {

        Usuario u = usuarioRepo.findByEmail(email);

        if (u!=null){
            u.setPassword(password);
            usuarioRepo.save(u);
        }else{
            throw new FitnesscampException("No se encontraron registros");
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

}
