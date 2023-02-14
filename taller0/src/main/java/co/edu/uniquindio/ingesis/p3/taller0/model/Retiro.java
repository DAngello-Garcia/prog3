package co.edu.uniquindio.ingesis.p3.taller0.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Retiro extends Transaccion {
    public Retiro(float valor, LocalDateTime fecha, EstadoTransaccion estado) {
        super(valor, fecha, estado);
    }

    public Retiro() {
    }
}
