package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador,String> {

    //================================= REPOSITORIO DE ADMINISTRADOR =================================//
    Optional<Administrador> findByEmail(String email);
    Administrador findByEmailAndPassword(String email,String password);

    @Query("select t from Trabajador t where t.administrador.nombre =:nombreAdmin")
    List<Trabajador> obtenerTrabajadoresAdmin(String nombreAdmin);

    @Query("select p from Producto p where p.administrador.nombre =:nombreAdmin")
    List<Producto> obtenerProductosAdmin(String nombreAdmin);

    @Query("select s from Servicio s where s.administrador.nombre =:nombreAdmin")
    List<Servicio> obtenerServiciosAdmin(String nombreAdmin);
}
