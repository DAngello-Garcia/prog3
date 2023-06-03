package co.edu.uniquindio.proyectofinal.exceptions;

public class PersonaNoExisteException extends Exception{

    public PersonaNoExisteException(String nombre){
        super(String.format("El %s no existe",nombre));
    }
}
