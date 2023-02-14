package co.edu.uniquindio.ingesis.p3.taller0.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Transaccion {
    private float valor;
    private LocalDateTime fecha;
    private EstadoTransaccion estado;

    public Transaccion(float valor, LocalDateTime fecha, EstadoTransaccion estado) {
        this.valor = valor;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Transaccion() {
    }
}
