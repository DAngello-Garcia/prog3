package co.edu.uniquindio.proyectofinal.exceptions;

public class ValorRequeridoException extends Exception{

    public ValorRequeridoException(String nombre){
        super(String.format("El/La %s es requerido", nombre));
    }
}
