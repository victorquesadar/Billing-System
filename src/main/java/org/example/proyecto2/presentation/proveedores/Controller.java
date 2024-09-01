package org.example.proyecto2.presentation.proveedores;
import org.example.proyecto2.logic.Proveedor;
import org.example.proyecto2.logic.Service;
import org.example.proyecto2.logic.SesionProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/proveedores")
public class Controller {
    @Autowired
    private Service service;

    @Autowired
    private SesionProveedor sesion;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        System.out.println("correo" + email);
        Proveedor proveedor = service.proveedorFindByEmailAndContrasena(email, password);
        System.out.println(proveedor);
        if (proveedor != null) {
            if (proveedor.getAceptado() == 2) {
                sesion.setEmail(email);
                sesion.setContrasena(password);
                sesion.setCedula(proveedor.getCedula());
                return ResponseEntity.ok("/menuAdmin");
            } else if (proveedor.getAceptado() == 1) {
                sesion.setEmail(email);
                sesion.setContrasena(password);
                sesion.setCedula(proveedor.getCedula());
                return ResponseEntity.ok("/menuProv");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("/index");
    }

    @GetMapping("/perfilProv")
    public Proveedor obtenerProveedorPerfil(){
        Proveedor pro = service.findByCedula(sesion.getCedula());
        System.out.println("cedula "+pro.getCedula()+"nombre "+pro.getNombre()+"email "+pro.getEmail()+"contra "+pro.getContrasena());
        return pro;
    }

    @PostMapping("/editPerfil")
    public void editarPerfil(@RequestBody Proveedor pro, Model model) {
        sesion.setEmail(pro.getEmail());
        sesion.setContrasena(pro.getContrasena());
        System.out.println("Edicion--cedula:"+pro.getCedula()+" nombre:"+pro.getNombre()+" email:"+pro.getEmail()+" contra:"+pro.getContrasena());

        service.editarProveedor(pro);
        service.findByCedula(sesion.getCedula());
        model.addAttribute("perfil", service.findByCedula(sesion.getCedula()));
    }

    /* --- REGISTROPROVEEDOR.HTML --- */

    @PostMapping("/registrarProveedor")
    public void registrarProveedor(@RequestBody Proveedor prov) {
        prov.setAceptado((byte) 0);
        service.guardarProveedorNoAceptado(prov);
    }

    @GetMapping("/listaAceptados")
    public List<Proveedor> listaAceptados(){
        List<Proveedor> proveedoresAceptados = service.proveedorFindAllByAceptado((byte) 1);
        return proveedoresAceptados;
    }

    @GetMapping("/listaSolicitudes")
    public List<Proveedor> listaSolicitudes(){
        List<Proveedor> proveedoresNoAceptados = service.proveedorFindAllByAceptado((byte) 0);
        return proveedoresNoAceptados;
    }

    @DeleteMapping("/{cedula}")
    public void eliminarProveedor(@PathVariable String cedula) {
        System.out.println("ced: "+cedula);
        service.eliminarProveedor(cedula);
    }

    @PostMapping("/{cedula}")
    public void aceptarProv(@PathVariable String cedula){
        service.proveedorFindByCedula(cedula);
    }
}