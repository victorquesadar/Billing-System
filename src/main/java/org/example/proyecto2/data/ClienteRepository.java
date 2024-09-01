package org.example.proyecto2.data;
import org.example.proyecto2.logic.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Cliente (cedula, nombre, email, proveedor) VALUES (:cedula, :nombre, :email, :proveedor)",nativeQuery = true)
    void guardarCliente(@Param("cedula") String cedula, @Param("nombre") String nombre, @Param("email") String email, @Param("proveedor") String proveedor);

    List<Cliente> findAllByProveedor(String proveedor);

    Cliente findByCedula(String cedula);

}