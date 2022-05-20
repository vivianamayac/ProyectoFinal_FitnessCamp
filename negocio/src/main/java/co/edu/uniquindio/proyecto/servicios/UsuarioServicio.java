package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.Date;
import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;

    void actualizarUsuario(String email, String password, Usuario u) throws Exception;

    void eliminarUsuario(String email, String password) throws Exception;

    void registrarTarjetaUsuario(String idUsuario, String numero, String codigo, Date fecha)throws Exception;

    Usuario obtenerUsuario(String id) throws Exception;

    Usuario obtenerUsuarioNombre(String nombre) throws Exception;

    Usuario obtenerUsuarioEmailPassword(String email, String password) throws Exception;

    void registrarInfoNutricional(String idU,double peso,String medidas,double estatura,double porcentajeGrasa)throws Exception;

    void recoverPassword(String email,String password) throws Exception;
    Usuario obtenerUsuarioCorreo(String correo) throws Exception;
    List<Compra> obtenerComprasUsuario(String nombreUsuario);

    List<Servicio> obtenerServiciosUsuario(String nombreUsuario);

    List<Usuario> listarUsuarios();
}
