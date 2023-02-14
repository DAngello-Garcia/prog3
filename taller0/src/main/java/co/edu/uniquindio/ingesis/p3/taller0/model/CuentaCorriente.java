package co.edu.uniquindio.ingesis.p3.taller0.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaCorriente extends Cuenta {
    public CuentaCorriente(String numeroCuenta, float saldo) {
        super(numeroCuenta, saldo);
    }

    public CuentaCorriente() {
    }
}
