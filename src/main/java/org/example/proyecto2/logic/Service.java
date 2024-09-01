package org.example.proyecto2.logic;
import org.example.proyecto2.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service("service")
public class Service {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    /* --- PROVEEDOR ---- */

    public Proveedor proveedorFindByEmailAndContrasena(String email, String contrasena) {
        return proveedorRepository.findByEmailAndContrasena(email, contrasena);
    }

    public void guardarProveedorNoAceptado(Proveedor prov) {
        proveedorRepository.guardarProveedorNoAceptado(prov.getCedula(), prov.getNombre(), prov.getAceptado(), prov.getEmail(), prov.getContrasena());
    }

    public void editarProveedor (Proveedor prov) {
        proveedorRepository.editarProveedor(prov.getCedula(), prov.getNombre(), prov.getEmail(), prov.getContrasena());
    }

    public List<Proveedor> proveedorFindAllByAceptado(byte aceptado) {
        return proveedorRepository.findAllByAceptado(aceptado);
    }

    public Proveedor findByCedula(String cedula) {
        return proveedorRepository.findByCedula(cedula);
    }

    public void proveedorFindByCedula(String cedula) {
        proveedorRepository.findByCedula(cedula).setAceptado((byte) 1);
        proveedorRepository.guardarProveedorAceptado(proveedorRepository.findByCedula(cedula).getCedula(), proveedorRepository.findByCedula(cedula).getAceptado());
    }

    public void eliminarProveedor(String cedula) {
        proveedorRepository.delete(proveedorRepository.findByCedula(cedula));
    }

    /* --------------- */

    /* --- CLIENTE --- */

    public void guardarCliente(Cliente cli) {
        clienteRepository.guardarCliente(cli.getCedula(), cli.getNombre(), cli.getEmail(), cli.getProveedor());
    }

    public List<Cliente> clienteFindAllByProveedor(String proveedor) {
        return clienteRepository.findAllByProveedor(proveedor);
    }

    public List<Cliente> clienteFindAllP(String proveedor) {
        Cliente cli=new Cliente();

        return clienteRepository.findAllByProveedor(proveedor);
    }

    public Cliente clienteFindByCedula(String cedula) {
        return clienteRepository.findByCedula(cedula);
    }

    /* --------------- */

    /* --- PRODUCTO --- */

    public void guardarProducto(Producto pro) {
        productoRepository.guardarProducto(pro.getCodigo(),pro.getNombre(),pro.getPrecio(),pro.getProveedor());
    }

    public List<Producto> productoFindAllByProveedor(String proveedor) {
        return productoRepository.findAllByProveedor(proveedor);
    }

    public Producto productoFindByCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }

    public void editarProducto(Producto pro) {
        productoRepository.editarProducto(pro.getCodigo(), pro.getCantidad(), pro.getTotallinea(), pro.getFactura());
    }

    public Integer sumaTotalLinea(Integer factura) {
        return productoRepository.sumaTotalLinea(factura);
    }

    public List<Producto> productoFindAllByFactura(Integer numero) {
        return productoRepository.findAllByFactura(numero);
    }

    /* -------------- */

    /* --- SERVICIO --- */

    public void guardarServicio(Servicio ser) {
        servicioRepository.guardarServicio(ser.getCodigo(),ser.getNombre(),ser.getProveedor());
    }

    public List<Servicio> servicioFindAllByProveedor(String proveedor) {
        return servicioRepository.findAllByProveedor(proveedor);
    }

    public Servicio servicioFindByCodigo(String codigo) {
        return servicioRepository.findByCodigo(codigo);
    }

    public void editarServicio(Servicio ser) {
        servicioRepository.editarServicio(ser.getCodigo(), ser.getPrecio(), ser.getFactura());
    }

    public Integer sumaPrecio(Integer factura) {
        return servicioRepository.sumaPrecio(factura);
    }

    public List<Servicio> servicioFindAllByFactura(Integer numero) {
        return servicioRepository.findAllByFactura(numero);
    }

    /* ---------------- */

    /* --- FACTURA --- */

    public void guardarFactura(Factura fac) {
        facturaRepository.guardarFactura(fac.getNombre(), fac.getFecha(), fac.getProveedor(), fac.getCliente(), fac.getSubtotal(), fac.getImpuesto(), fac.getTotal());
    }

    public Factura facturaFindByNumero(int numero) { return facturaRepository.findByNumero(numero); }

    public Factura facturaFindByNombre(String nombre) {
        return facturaRepository.findByNombre(nombre);
    }

    public Factura facturaFindByNombreAndProveedor(String nombre, String proveedor) {
        return facturaRepository.findByNombreAndProveedor(nombre, proveedor);
    }

    public void editarFactura(Factura fac) {
        facturaRepository.editarFactura(fac.getNombre(), fac.getSubtotal(), fac.getImpuesto(), fac.getTotal());
    }

    public Iterable<Factura> facturaFindAll() {
        return facturaRepository.findAll();
    }

    public List<Factura> facturaFindAllByProveedor(String cedula) {
        return facturaRepository.findAllByProveedor(cedula);
    }

    /* --------------- */
}