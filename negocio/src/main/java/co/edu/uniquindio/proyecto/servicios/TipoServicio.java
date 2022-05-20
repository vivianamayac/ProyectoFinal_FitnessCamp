package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface TipoServicio {

    TipoTrabajador registrarTipo(TipoTrabajador t) throws Exception;

    void  actualizarTipo(TipoTrabajador t, int idTipo) throws Exception;

    void eliminarTipo(int id) throws  Exception;

    TipoTrabajador obtenerTipo(int id) throws Exception;

    List<TipoTrabajador> listarTipos();


}
