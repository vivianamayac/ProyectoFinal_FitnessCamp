package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.TipoTrabajador;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipoServicioImpl implements TipoServicio{

    private final TipoRepo tipoRepo;

    public TipoServicioImpl(TipoRepo tipoRepo) {
        this.tipoRepo = tipoRepo;
    }

    @Override
    public TipoTrabajador registrarTipo(TipoTrabajador t) throws Exception {

      if (t.getNombre().length()>100){
          throw new Exception("El nombre no puede exceder los 100 caracteres");
      }

        return tipoRepo.save(t);
    }

    @Override
    public void actualizarTipo(TipoTrabajador t, int idTipo) throws Exception {

        TipoTrabajador tipo= obtenerTipo(idTipo);

        if (tipo != null){

            tipoRepo.save(tipo);
        }
    }

    @Override
    public void eliminarTipo(int id) throws Exception {

        TipoTrabajador tipoEncontrado = obtenerTipo(id);

        if(tipoEncontrado != null){
            tipoRepo.delete(tipoEncontrado);
        } else {
            throw  new Exception("No se encontraron coincidencias");
        }

    }

    @Override
    public TipoTrabajador obtenerTipo(int id) throws Exception {

        Optional<TipoTrabajador> tipo = tipoRepo.findById(id);

        if(tipo.isEmpty()){
            throw  new Exception("No se encontraron coincidencias");
        }

        return tipo.get();
    }

    @Override
    public List<TipoTrabajador> listarTipos() {
        return tipoRepo.findAll();
    }


}
