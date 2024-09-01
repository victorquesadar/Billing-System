package org.example.proyecto2.presentation.facturas;
import org.example.proyecto2.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class ControllerFacturas {
    @Autowired
    private Service service;

    @Autowired
    private SesionProveedor sesion;

    @Autowired
    private SesionFactura sesionFactura;

    @Autowired
    private GeneradorPDF generadorPDF;

    @Autowired
    private GenerarXML generarXML;

    @GetMapping("/listaClientes")
    public List<Cliente> listaClientes(){
        List<Cliente> clientes = service.clienteFindAllByProveedor(sesion.getCedula());
        for(int i=0; i<clientes.size();i++){
            System.out.println("cliente "+i+": "+clientes.get(i));

        }
        return clientes;
    }

    @GetMapping("/listaProductos")
    public List<Producto> listaProductos(){
        List<Producto> productos = service.productoFindAllByProveedor(sesion.getCedula());
        for(int i=0; i<productos.size();i++){
            System.out.println("producto "+i+": "+productos.get(i).getCodigo());
        }
        return productos;
    }

    @GetMapping("/listaServicios")
    public List<Servicio> listaServicios(){
        List<Servicio> servicios = service.servicioFindAllByProveedor(sesion.getCedula());
        for(int i=0; i<servicios.size();i++){
            System.out.println("servicio "+i+": "+servicios.get(i).getCodigo());
        }
        return servicios;
    }

    @PostMapping("/guardarDetallesFactura")
    public void guardarDetallesFactura(@RequestParam("nombre") String nombre, @RequestParam("fecha") String fecha, @RequestParam("cliente") String cliente){
        System.out.println("nombre: "+nombre+" fecha: "+fecha+" cliente: "+cliente);
        Factura fac = new Factura();
        fac.setNombre(nombre);
        fac.setFecha(fecha);
        fac.setProveedor(sesion.getCedula());
        fac.setCliente(cliente);
        fac.setSubtotal(0);
        fac.setImpuesto(0);
        fac.setTotal(0);
        sesionFactura.setNumero(fac.getNumero());
        sesionFactura.setNombre(fac.getNombre());
        service.guardarFactura(fac);
    }

    @PostMapping("/guardarProductoFactura")
    public void guardarProductoFactura(@RequestParam(name = "producto", required = false) String producto, @RequestParam(name = "cantidad", required = false) Integer cantidad) {
        System.out.println("producto: "+producto+" cantidad: "+cantidad);
        int numFactura = service.facturaFindByNombreAndProveedor(sesionFactura.getNombre(), sesion.getCedula()).getNumero();
        if (producto != null && cantidad !=null) {
            Producto pro = service.productoFindByCodigo(producto);
            pro.setCantidad(cantidad);
            pro.setTotallinea(pro.getPrecio() * cantidad);
            pro.setFactura(numFactura);
            service.editarProducto(pro);
        }
    }

    @PostMapping("/guardarServicioFactura")
    public void guardarServicioFactura(@RequestParam(name = "servicio", required = false) String servicio, @RequestParam(name = "precio", required = false) Integer precio) {
        System.out.println("servicio: "+servicio+" precio: "+precio);
        int numFactura = service.facturaFindByNombreAndProveedor(sesionFactura.getNombre(), sesion.getCedula()).getNumero();
        if (servicio != null && precio != null) {
            Servicio ser = service.servicioFindByCodigo(servicio);
            ser.setPrecio(precio);
            ser.setFactura(numFactura);
            service.editarServicio(ser);
        }
    }

    @PostMapping("/generarFactura")
    public void generarFactura(Model model) {
        int numFactura = service.facturaFindByNombreAndProveedor(sesionFactura.getNombre(), sesion.getCedula()).getNumero();
        Integer sumaTotalLinea = service.sumaTotalLinea(numFactura);
        Integer sumaPrecio = service.sumaPrecio(numFactura);
        Factura fac = service.facturaFindByNumero(numFactura);

        if (sumaTotalLinea != null && sumaPrecio != null) {
            fac.setSubtotal(sumaTotalLinea + sumaPrecio);
            fac.setImpuesto(((sumaTotalLinea + sumaPrecio) * 13) / 100);
            fac.setTotal((sumaTotalLinea + sumaPrecio) + (((sumaTotalLinea + sumaPrecio) * 13) / 100));
            service.editarFactura(fac);
            generadorPDF.generarFacturaPDF(fac, service.findByCedula(fac.getProveedor()), service.clienteFindByCedula(fac.getCliente()), service.productoFindAllByFactura(fac.getNumero()), service.servicioFindAllByFactura(fac.getNumero()));
            generarXML.guardarXmlFactura(fac, service.findByCedula(fac.getProveedor()), service.clienteFindByCedula(fac.getCliente()), service.productoFindAllByFactura(fac.getNumero()), service.servicioFindAllByFactura(fac.getNumero()));
        }

        else if (sumaTotalLinea != null && sumaPrecio == null) {
            fac.setSubtotal(sumaTotalLinea);
            fac.setImpuesto((sumaTotalLinea * 13) / 100);
            fac.setTotal((sumaTotalLinea) + ((sumaTotalLinea * 13) / 100));
            service.editarFactura(fac);
            generadorPDF.generarFacturaPDF(fac, service.findByCedula(fac.getProveedor()), service.clienteFindByCedula(fac.getCliente()), service.productoFindAllByFactura(fac.getNumero()), service.servicioFindAllByFactura(fac.getNumero()));
            generarXML.guardarXmlFactura(fac, service.findByCedula(fac.getProveedor()), service.clienteFindByCedula(fac.getCliente()), service.productoFindAllByFactura(fac.getNumero()), service.servicioFindAllByFactura(fac.getNumero()));
        }

        if (sumaTotalLinea == null && sumaPrecio != null) {
            fac.setSubtotal(sumaPrecio);
            fac.setImpuesto((sumaPrecio * 13) / 100);
            fac.setTotal((sumaPrecio) + ((sumaPrecio * 13) / 100));
            service.editarFactura(fac);
            generadorPDF.generarFacturaPDF(fac, service.findByCedula(fac.getProveedor()), service.clienteFindByCedula(fac.getCliente()), service.productoFindAllByFactura(fac.getNumero()), service.servicioFindAllByFactura(fac.getNumero()));
            generarXML.guardarXmlFactura(fac, service.findByCedula(fac.getProveedor()), service.clienteFindByCedula(fac.getCliente()), service.productoFindAllByFactura(fac.getNumero()), service.servicioFindAllByFactura(fac.getNumero()));
        }

        List<Cliente> clientes = service.clienteFindAllByProveedor(sesion.getCedula());
        model.addAttribute("clientes",clientes);
        List<Producto> productos = service.productoFindAllByProveedor(sesion.getCedula());
        model.addAttribute("productos",productos);
        List<Servicio> servicios = service.servicioFindAllByProveedor(sesion.getCedula());
        model.addAttribute("servicios",servicios);
    }

    @GetMapping("/listaFac")
    public List<Factura> listaFacturas() {
        List<Factura> lista = service.facturaFindAllByProveedor(sesion.getCedula());
        for(int i=0; i<lista.size();i++){
            System.out.println("FACTURA: "+lista.get(i).getNombre());
        }
        return lista;
    }
}