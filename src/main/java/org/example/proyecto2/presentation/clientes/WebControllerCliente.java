package org.example.proyecto2.presentation.clientes;
import org.example.proyecto2.logic.Cliente;
import org.example.proyecto2.logic.Service;
import org.example.proyecto2.logic.SesionProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class WebControllerCliente {
    @Autowired
    private Service service;

    @Autowired
    private SesionProveedor sesion;

    @GetMapping("/registroCliente")
    public String mostrarClientes() {
        List<Cliente> clientes = service.clienteFindAllByProveedor(sesion.getCedula());
        return "registroCliente";
    }
}