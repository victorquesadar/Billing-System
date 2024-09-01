package org.example.proyecto2.data;
import org.example.proyecto2.logic.Servicio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Servicio (codigo, nombre, proveedor) VALUES (:codigo, :nombre, :proveedor)",nativeQuery = true)
    void guardarServicio(@Param("codigo") String codigo, @Param("nombre") String nombre, @Param("proveedor") String proveedor);

    List<Servicio> findAllByProveedor(String proveedor);

    Servicio findByCodigo(String codigo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Servicio SET precio = :precio, factura = :factura WHERE codigo = :codigo",nativeQuery = true)
    void editarServicio(@Param("codigo") String codigo, @Param("precio") Integer precio, @Param("factura") Integer factura);

    @Query(value = "SELECT SUM(precio) FROM Servicio WHERE factura = :factura",nativeQuery = true)
    Integer sumaPrecio(@Param("factura") Integer factura);

    List<Servicio> findAllByFactura(Integer numero);

}