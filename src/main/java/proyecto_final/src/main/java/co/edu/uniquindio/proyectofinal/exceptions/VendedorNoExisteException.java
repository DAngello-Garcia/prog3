package co.edu.uniquindio.proyectofinal.exceptions;

public class VendedorNoExisteException extends VendedorException {

    public VendedorNoExisteException(){
        super("El Vendedor no existe");
    }
}
