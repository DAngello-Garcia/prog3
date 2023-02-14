package co.edu.uniquindio.ingesis.p3.taller0.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Empleado extends Persona {
    public Empleado(String nombre, String apellido, String cedula, String direccion, String email) {
        super(nombre, apellido, cedula, direccion, email);
    }

    public Empleado() {
    }
}
