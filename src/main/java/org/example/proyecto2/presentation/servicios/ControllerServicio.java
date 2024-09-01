package org.example.proyecto2.presentation.servicios;
import org.example.proyecto2.logic.Service;
import org.example.proyecto2.logic.Servicio;
import org.example.proyecto2.logic.SesionProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ControllerServicio {
    @Autowired
    private Service service;

    @Autowired
    private SesionProveedor sesion;

    @PostMapping("/registrarServicio")
    public void registrarServicio(@RequestBody Servicio ser, Model model) {
        ser.setProveedor(sesion.getCedula());
        service.guardarServicio(ser);
    }
    @GetMapping()
    public List<Servicio> listaServicios(){
        List<Servicio> servicios = service.servicioFindAllByProveedor(sesion.getCedula());
        for(int i=0; i<servicios.size();i++){
            System.out.println("servicio "+i+": "+servicios.get(i).getCodigo());
        }
        return servicios;
    }
}