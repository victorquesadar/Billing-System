package org.example.proyecto2.logic;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Producto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo")
    private String codigo;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "precio")
    private int precio;
    @Basic
    @Column(name = "cantidad")
    private Integer cantidad;
    @Basic
    @Column(name = "totallinea")
    private Integer totallinea;
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
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero")// @JsonBackReference
    @JoinColumn(name = "factura", referencedColumnName = "numero")
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTotallinea() {
        return totallinea;
    }

    public void setTotallinea(Integer totallinea) {
        this.totallinea = totallinea;
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
        Producto producto = (Producto) o;
        return precio == producto.precio && Objects.equals(codigo, producto.codigo) && Objects.equals(nombre, producto.nombre) && Objects.equals(cantidad, producto.cantidad) && Objects.equals(totallinea, producto.totallinea) && Objects.equals(proveedor, producto.proveedor) && Objects.equals(factura, producto.factura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, precio, cantidad, totallinea, proveedor, factura);
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
            ", nombre='" + nombre + '\'' +
            ", precio='" + precio + '\'';
    }
}