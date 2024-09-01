package org.example.proyecto2.presentation.productos;
import org.example.proyecto2.logic.Producto;
import org.example.proyecto2.logic.Service;
import org.example.proyecto2.logic.SesionProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ControllerProductos {
    @Autowired
    private Service service;

    @Autowired
    private SesionProveedor sesion;

    @PostMapping("/registrarProducto")
    public void registrarProducto(@RequestBody Producto pro) {
        pro.setProveedor(sesion.getCedula());
        service.guardarProducto(pro);
    }

    @GetMapping()
    public List<Producto> listaProductos(){
        List<Producto> productos = service.productoFindAllByProveedor(sesion.getCedula());
        for(int i=0; i<productos.size();i++){
            System.out.println("producto "+i+": "+productos.get(i).getCodigo());
        }
        return productos;
    }
}