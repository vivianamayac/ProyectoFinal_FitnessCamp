package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.Date;
import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws FitnesscampException;

    void actualizarUsuario(String email, String password, Usuario u) throws FitnesscampException;

    void eliminarUsuario(String email, String password) throws FitnesscampException;

    void registrarTarjetaUsuario(String idUsuario, String numero, String codigo, Date fecha)throws FitnesscampException;

    Usuario obtenerUsuario(String id) throws FitnesscampException;

    Usuario obtenerUsuarioNombre(String nombre) throws FitnesscampException;

    Usuario obtenerUsuarioEmailPassword(String email, String password) throws FitnesscampException;

    void registrarInfoNutricional(String idU,double peso,String medidas,double estatura,double porcentajeGrasa)throws FitnesscampException;

    void recoverPassword(String email,String password) throws FitnesscampException;
    Usuario obtenerUsuarioCorreo(String correo) throws FitnesscampException;
    List<Compra> obtenerComprasUsuario(String nombreUsuario);

    List<Servicio> obtenerServiciosUsuario(String nombreUsuario);

    List<Usuario> listarUsuarios();
}
