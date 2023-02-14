package co.edu.uniquindio.ingesis.p3.taller0.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente extends Persona {
    public Cliente(String nombre, String apellido, String cedula, String direccion, String email) {
        super(nombre, apellido, cedula, direccion, email);
    }

    public Cliente() {
    }
}
