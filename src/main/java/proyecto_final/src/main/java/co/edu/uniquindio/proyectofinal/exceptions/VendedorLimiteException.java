package co.edu.uniquindio.proyectofinal.exceptions;

public class VendedorLimiteException extends VendedorException {

    public VendedorLimiteException(){
        super("El Maximo de vendedores son 10");
    }
}
