package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadorRepo extends JpaRepository<Trabajador,String> {

    //================================= REPOSITORIO DE MODERADOR =================================//

    Optional<Trabajador> findByEmail(String email);

    Optional<Trabajador> findByNombre(String nombre);
    Trabajador findByEmailAndPassword(String email, String password);
}
