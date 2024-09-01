package org.example.proyecto2.logic;
import org.springframework.stereotype.Component;
@Component("sesionFactura")
public class SesionFactura {
    int numero;
    String nombre;

    public SesionFactura(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public SesionFactura() { this(0, ""); }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}