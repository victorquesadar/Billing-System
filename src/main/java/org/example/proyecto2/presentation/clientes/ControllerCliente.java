package org.example.proyecto2.presentation.clientes;
import org.example.proyecto2.logic.Cliente;
import org.example.proyecto2.logic.Service;
import org.example.proyecto2.logic.SesionProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ControllerCliente {
    private final Service service;

    @Autowired
    private SesionProveedor sesion;

    @Autowired
    public ControllerCliente(Service service) {
        this.service = service;
    }

    @GetMapping("/listaClientes")
    public List<Cliente> listaClientes(){
        List<Cliente> clientes = service.clienteFindAllByProveedor(sesion.getCedula());
        for(int i=0; i<clientes.size();i++){
            System.out.println("cliente "+i+": "+clientes.get(i));

        }
        return clientes;
    }

    @PostMapping("/registarCliente")
    public void registrarCli(@RequestBody Cliente cliente){
        cliente.setProveedor(sesion.getCedula());
        service.guardarCliente(cliente);
    }
}