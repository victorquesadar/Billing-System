package org.example.proyecto2.presentation.servicios;
import org.example.proyecto2.logic.Service;
import org.example.proyecto2.logic.Servicio;
import org.example.proyecto2.logic.SesionProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class WebControllerServicio {
    @Autowired
    private Service service;

    @Autowired
    private SesionProveedor sesion;

    @GetMapping("/registroServicio")
    public String mostrarServicios(Model model) {
        List<Servicio> servicios = service.servicioFindAllByProveedor(sesion.getCedula());
        model.addAttribute("servicios",servicios);
        return "/registroServicio";
    }
}
