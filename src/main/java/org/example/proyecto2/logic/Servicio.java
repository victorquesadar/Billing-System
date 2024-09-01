package org.example.proyecto2.logic;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Servicio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo")
    private String codigo;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "precio")
    private Integer precio;
    @Basic
    @Column(name = "proveedor", insertable=false, updatable=false)
    private String proveedor;
    @Basic
    @Column(name = "factura", insertable=false, updatable=false)
    private Integer factura;
    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "cedula", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cedula")//@JsonBackReference
    private Proveedor proveedorByProveedor;
    @ManyToOne
    @JoinColumn(name = "factura", referencedColumnName = "numero")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero")//@JsonBackReference
    private Factura facturaByFactura;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servicio servicio = (Servicio) o;
        return Objects.equals(codigo, servicio.codigo) && Objects.equals(nombre, servicio.nombre) && Objects.equals(precio, servicio.precio) && Objects.equals(proveedor, servicio.proveedor) && Objects.equals(factura, servicio.factura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, precio, proveedor, factura);
    }

    public Proveedor getProveedorByProveedor() {
        return proveedorByProveedor;
    }

    public void setProveedorByProveedor(Proveedor proveedorByProveedor) {
        this.proveedorByProveedor = proveedorByProveedor;
    }

    public Factura getFacturaByFactura() {
        return facturaByFactura;
    }

    public void setFacturaByFactura(Factura facturaByFactura) {
        this.facturaByFactura = facturaByFactura;
    }

    @Override
    public String toString() {
        return
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'';
    }
}