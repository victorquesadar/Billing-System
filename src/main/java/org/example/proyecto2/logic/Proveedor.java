package org.example.proyecto2.logic;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Proveedor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cedula")
    private String cedula;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "aceptado")
    private byte aceptado;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "contrasena")
    private String contrasena;

    @OneToMany(mappedBy = "proveedorByProveedor")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cedula") //@JsonManagedReference
    private Collection<Cliente> clientesByCedula;

    @OneToMany(mappedBy = "proveedorByProveedor")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero")//@JsonManagedReference
    private Collection<Factura> facturasByCedula;

    @OneToMany(mappedBy = "proveedorByProveedor")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigo")//@JsonManagedReference
    private Collection<Producto> productosByCedula;

    @OneToMany(mappedBy = "proveedorByProveedor")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigo")//@JsonManagedReference
    private Collection<Servicio> serviciosByCedula;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getAceptado() {
        return aceptado;
    }

    public void setAceptado(byte aceptado) {
        this.aceptado = aceptado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return aceptado == proveedor.aceptado && Objects.equals(cedula, proveedor.cedula) && Objects.equals(nombre, proveedor.nombre) && Objects.equals(email, proveedor.email) && Objects.equals(contrasena, proveedor.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, nombre, aceptado, email, contrasena);
    }

    public Collection<Cliente> getClientesByCedula() {
        return clientesByCedula;
    }

    public void setClientesByCedula(Collection<Cliente> clientesByCedula) {
        this.clientesByCedula = clientesByCedula;
    }

    public Collection<Factura> getFacturasByCedula() {
        return facturasByCedula;
    }

    public void setFacturasByCedula(Collection<Factura> facturasByCedula) {
        this.facturasByCedula = facturasByCedula;
    }

    public Collection<Producto> getProductosByCedula() {
        return productosByCedula;
    }

    public void setProductosByCedula(Collection<Producto> productosByCedula) {
        this.productosByCedula = productosByCedula;
    }

    @Override
    public String toString() {
        return
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", aceptado=" + aceptado +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' ;
    }

    public Collection<Servicio> getServiciosByCedula() {
        return serviciosByCedula;
    }

    public void setServiciosByCedula(Collection<Servicio> serviciosByCedula) {
        this.serviciosByCedula = serviciosByCedula;
    }
}
