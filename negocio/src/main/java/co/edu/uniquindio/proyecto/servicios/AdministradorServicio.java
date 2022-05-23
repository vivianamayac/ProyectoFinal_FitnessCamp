package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Servicio;
import co.edu.uniquindio.proyecto.entidades.Trabajador;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;

import java.util.List;

public interface AdministradorServicio {

    Administrador registrarAdministrador(Administrador a) throws FitnesscampException;

    void actualizarAdministrador(Administrador a,String email,String password) throws FitnesscampException;

    void eliminarAdministrador(String email) throws FitnesscampException;

    Administrador obtenerAdministrador(String id) throws FitnesscampException;

    Administrador obtenerAdministradorEmail(String email) throws FitnesscampException;

    List<Trabajador> obtenerTrabajadoresAdmin(String nombreAdmin);

    List<Producto> obtenerProductosAdmin(String nombreAdmin);

    List<Servicio> obtenerServiciosAdmin(String nombreAdmin);

    List<Administrador> listarAdministradores();

    Administrador obtenerEmailPassword(String email,String password) throws FitnesscampException;
}
