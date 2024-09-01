package org.example.proyecto2.presentation.facturas;
import org.example.proyecto2.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebControllerFacturas {
    @Autowired
    private Service service;

    @Autowired
    private SesionProveedor sesion;

    @GetMapping("/facturar")
    public String mostrarDatos(Model model) {
        return "/facturar";
    }

    /* --- LISTAFACTURAS.HTML --- */

    @GetMapping("/listaFacturas")
    public String mostrarFacturas() {
        return "/listaFacturas";
    }

    /* -------------------------- */
}