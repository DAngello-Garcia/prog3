package co.edu.uniquindio.ingesis.p3.taller0.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaAhorro extends Cuenta {
    public CuentaAhorro(String numeroCuenta, float saldo) {
        super(numeroCuenta, saldo);
    }

    public CuentaAhorro() {
    }
}
