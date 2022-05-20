package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Servicio;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,Integer> {

    Usuario findByEmailAndPassword(String email,String password);

    Usuario findByEmail(String email);

    Usuario findByNombre(String nombre);

    Optional<Usuario> findById(String id);

    @Query("select c from Compra c where c.usuario.nombre =:nombreUsuario")
    List<Compra> obtenerComprasUsuario(String nombreUsuario);

    @Query("select s from Servicio s join s.reservas r where r.usuario.nombre =:nombreUsuario")
    List<Servicio> obtenerServiciosUsuario(String nombreUsuario);
}
