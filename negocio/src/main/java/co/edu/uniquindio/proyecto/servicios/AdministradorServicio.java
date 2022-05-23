package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Servicio;
import co.edu.uniquindio.proyecto.entidades.Trabajador;

import java.util.List;

public interface AdministradorServicio {

    Administrador registrarAdministrador(Administrador a) throws Exception;

    void actualizarAdministrador(Administrador a,String email,String password) throws Exception;

    void eliminarAdministrador(String email) throws Exception;

    Administrador obtenerAdministrador(String id) throws Exception;

    Administrador obtenerAdministradorEmail(String email) throws Exception;

    List<Trabajador> obtenerTrabajadoresAdmin(String nombreAdmin);

    List<Producto> obtenerProductosAdmin(String nombreAdmin);

    List<Servicio> obtenerServiciosAdmin(String nombreAdmin);

    List<Administrador> listarAdministradores();

    Administrador obtenerEmailPassword(String email,String password) throws Exception;
}
