package co.edu.uniquindio.proyectofinal.exceptions;

public class VendedorExisteException extends VendedorException {

    public VendedorExisteException(){
        super("El vendedor ya se encuentra registrado");
    }
}
