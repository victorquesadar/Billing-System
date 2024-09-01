package org.example.proyecto2.data;
import org.example.proyecto2.logic.Proveedor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, String> {
    Proveedor findByEmailAndContrasena(String email, String contrasena);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Proveedor (cedula, nombre, aceptado, email, contrasena) VALUES (:cedula, :nombre, :aceptado, :email, :contrasena)",nativeQuery = true)
    void guardarProveedorNoAceptado(@Param("cedula") String cedula, @Param("nombre") String nombre, @Param("aceptado") byte aceptado, @Param("email") String email, @Param("contrasena") String contrasena);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Proveedor SET aceptado = :aceptado WHERE cedula = :cedula",nativeQuery = true)
    void guardarProveedorAceptado(@Param("cedula") String cedula, @Param("aceptado") byte aceptado);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Proveedor SET nombre = :nombre, email = :email, contrasena = :contrasena WHERE cedula =:cedula",nativeQuery = true)
    void editarProveedor(@Param("cedula") String cedula, @Param("nombre") String nombre, @Param("email") String email, @Param("contrasena") String contrasena);

    List<Proveedor> findAllByAceptado(byte aceptado);

    Proveedor findByCedula(String cedula);

}