package co.edu.uniquindio.proyectofinal.exceptions;

public class PersonaExisteException extends Exception{

    public PersonaExisteException(String nombre){
        super(String.format("El %s ya se encuentra registrado",nombre));
    }
}
