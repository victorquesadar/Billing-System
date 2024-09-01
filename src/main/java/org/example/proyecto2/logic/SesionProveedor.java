package org.example.proyecto2.logic;
import org.springframework.stereotype.Component;

@Component("sesionProveedor")
public class SesionProveedor {
    String email;
    String contrasena;
    String cedula;

    public SesionProveedor(String email, String contrasena, String cedula) {
        this.email = email;
        this.contrasena = contrasena;
        this.cedula = cedula;
    }

    public SesionProveedor() { this("","", ""); }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}