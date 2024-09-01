package org.example.proyecto2.data;
import org.example.proyecto2.logic.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Producto (codigo, nombre, precio, proveedor) VALUES (:codigo, :nombre, :precio, :proveedor)",nativeQuery = true)
    void guardarProducto(@Param("codigo") String codigo, @Param("nombre") String nombre, @Param("precio") Integer precio, @Param("proveedor") String proveedor);

    List<Producto> findAllByProveedor(String proveedor);

    Producto findByCodigo(String codigo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Producto SET cantidad = :cantidad, totallinea = :totallinea, factura = :factura WHERE codigo = :codigo",nativeQuery = true)
    void editarProducto(@Param("codigo") String codigo, @Param("cantidad") Integer cantidad, @Param("totallinea") Integer totallinea, @Param("factura") Integer factura);


    @Query(value = "SELECT SUM(totallinea) FROM Producto WHERE factura = :factura",nativeQuery = true)
    Integer sumaTotalLinea(@Param("factura") Integer factura);

    List<Producto> findAllByFactura(Integer numero);

}