package org.example.proyecto2.data;
import org.example.proyecto2.logic.Factura;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Factura (nombre, fecha, proveedor, cliente, subtotal, impuesto, total) VALUES (:nombre, :fecha, :proveedor, :cliente, :subtotal, :impuesto, :total)",nativeQuery = true)
    void guardarFactura(@Param("nombre") String nombre, @Param("fecha") String fecha, @Param("proveedor") String proveedor, @Param("cliente") String cliente, @Param("subtotal") Integer subtotal, @Param("impuesto") Integer impuesto, @Param("total") Integer total);


    @Modifying
    @Transactional
    @Query(value = "UPDATE Factura SET subtotal = :subtotal, impuesto = :impuesto, total = :total WHERE nombre = :nombre",nativeQuery = true)
    void editarFactura(@Param("nombre") String nombre, @Param("subtotal") Integer subtotal, @Param("impuesto") Integer impuesto, @Param("total") Integer total);

    Factura findByNumero(int numero);

    Factura findByNombre(String nombre);

    Factura findByNombreAndProveedor(String nombre, String proveedor);

    List<Factura> findAllByProveedor(String cedula);

}