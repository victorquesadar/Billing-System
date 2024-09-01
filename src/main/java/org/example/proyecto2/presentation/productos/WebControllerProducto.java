package org.example.proyecto2.presentation.productos;
import org.example.proyecto2.logic.Producto;
import org.example.proyecto2.logic.Service;
import org.example.proyecto2.logic.SesionProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class WebControllerProducto {
    @Autowired
    private Service service;

    @Autowired
    private SesionProveedor sesion;

    @GetMapping("/registroProducto")
    public String mostrarProductos(Model model) {
        List<Producto> productos = service.productoFindAllByProveedor(sesion.getCedula());
        model.addAttribute("productos",productos);
        return "/registroProducto";
    }
}
