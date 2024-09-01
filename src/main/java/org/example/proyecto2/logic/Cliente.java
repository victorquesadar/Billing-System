package org.example.proyecto2.logic;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cedula")
    private String cedula;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "proveedor", insertable=false, updatable=false)
    private String proveedor;

    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "cedula", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cedula")
    private Proveedor proveedorByProveedor;
    @OneToMany(mappedBy = "clienteByCliente")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero")
    private Collection<Factura> facturasByCedula;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cedula, cliente.cedula) && Objects.equals(nombre, cliente.nombre) && Objects.equals(email, cliente.email) && Objects.equals(proveedor, cliente.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, nombre, email, proveedor);
    }

    public Proveedor getProveedorByProveedor() {
        return proveedorByProveedor;
    }

    public void setProveedorByProveedor(Proveedor proveedorByProveedor) {
        this.proveedorByProveedor = proveedorByProveedor;
    }
    @Override
    public String toString() {
        return
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", proveedor='" + proveedor + '\'';
    }

    public Collection<Factura> getFacturasByCedula() {
        return facturasByCedula;
    }

    public void setFacturasByCedula(Collection<Factura> facturasByCedula) {
        this.facturasByCedula = facturasByCedula;
    }
}