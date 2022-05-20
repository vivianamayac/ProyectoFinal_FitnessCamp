package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra,Integer> {

    //================================= REPOSITORIO DE COMPRA =================================//

    @Query("select c from Compra c where c.usuario.id = :id")
    List <Compra> listarComprasUsuario (int id);

    @Query("select c from Compra c where c.usuario.id =:idUsuario and c.id =:idCompra")
    Compra obtenerCompraUsuario(int idUsuario,int idCompra);

}
