package org.example.proyecto2.presentation.proveedores;
import org.example.proyecto2.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Autowired
    private Service service;

    @Autowired
    private SesionProveedor sesion;

    /* --- INDEX.HTML --- */

    @GetMapping("/index")
    public String paginaIndex() {
        return "/index";
    }

    /* --- PERFIL.HTML --- */

    @GetMapping("/perfil")
    public String perfil(Model model){

        return "perfil";
    }

    /* ------------------- */

    /* --- REGISTROPROVEEDOR.HTML --- */

    @GetMapping("/registroProveedor")
    public String paginaRegistroProveedor() {
        return "/registroProveedor";
    }

    /* ----------------------------- */

    /* --- MENUADMIN.HTML --- */

    @GetMapping("/menuAdmin")
    public String paginaMenuAdmin() {
        return "/menuAdmin";
    }

    /* ---------------------- */

    /* --- MENUPROV.HTML --- */

    @GetMapping("/menuProv")
    public String paginaMenuProv() {
        return "/menuProv";
    }

    /* --------------------- */

    /* --- SOLICITUDESREGISTROPROV.HTML --- */

    @GetMapping("/solicitudesRegistroProv")
    public String mostrarProveedoresNoAceptados(Model model) {
        return "/solicitudesRegistroProv";
    }

    /* --- LISTAPROVEEDORES.HTML --- */

    @GetMapping("/listaProveedores")
    public String mostrarProveedoresAceptados(Model model) {
        return "/listaProveedores";
    }

    /* ---------------------------- */
}