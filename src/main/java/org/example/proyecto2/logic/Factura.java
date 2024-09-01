package org.example.proyecto2.logic;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Factura {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numero")
    private int numero;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "fecha")
    private String fecha;
    @Basic
    @Column(name = "proveedor", insertable=false, updatable=false)
    private String proveedor;
    @Basic
    @Column(name = "cliente", insertable=false, updatable=false)
    private String cliente;
    @Basic
    @Column(name = "subtotal")
    private int subtotal;
    @Basic
    @Column(name = "impuesto")
    private int impuesto;
    @Basic
    @Column(name = "total")
    private int total;
    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "cedula", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cedula")
    private Proveedor proveedorByProveedor;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cedula")
    @JoinColumn(name = "cliente", referencedColumnName = "cedula", nullable = false)
    private Cliente clienteByCliente;

    @OneToMany(mappedBy = "facturaByFactura")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigo")
    private Collection<Producto> productosByNumero;

    @OneToMany(mappedBy = "facturaByFactura")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigo")
    private Collection<Servicio> serviciosByNumero;

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return subtotal == factura.subtotal && impuesto == factura.impuesto && total == factura.total && Objects.equals(numero, factura.numero) && Objects.equals(fecha, factura.fecha) && Objects.equals(proveedor, factura.proveedor) && Objects.equals(cliente, factura.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, nombre, fecha, proveedor, cliente, subtotal, impuesto, total);
    }

    public Proveedor getProveedorByProveedor() {
        return proveedorByProveedor;
    }

    public void setProveedorByProveedor(Proveedor proveedorByProveedor) {
        this.proveedorByProveedor = proveedorByProveedor;
    }

    public Cliente getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(Cliente clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public Collection<Producto> getProductosByNumero() {
        return productosByNumero;
    }

    public void setProductosByNumero(Collection<Producto> productosByNumero) {
        this.productosByNumero = productosByNumero;
    }

    public Collection<Servicio> getServiciosByNumero() {
        return serviciosByNumero;
    }

    public void setServiciosByNumero(Collection<Servicio> serviciosByNumero) {
        this.serviciosByNumero = serviciosByNumero;
    }
    @Override
    public String toString() {
        return
            "nombre='" + nombre + '\'';
    }
}