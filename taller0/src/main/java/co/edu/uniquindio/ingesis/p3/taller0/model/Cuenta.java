package co.edu.uniquindio.ingesis.p3.taller0.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cuenta {
    private String numeroCuenta;
    private float saldo;

    public Cuenta(String numeroCuenta, float saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public Cuenta() {
    }
}
