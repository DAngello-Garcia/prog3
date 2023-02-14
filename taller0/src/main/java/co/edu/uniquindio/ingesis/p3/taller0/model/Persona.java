package co.edu.uniquindio.ingesis.p3.taller0.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Persona {
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String email;

    public Persona(String nombre, String apellido, String cedula, String direccion, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.email = email;
    }

    public Persona() {
    }
}
