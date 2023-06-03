package co.edu.uniquindio.proyectofinal.utils;

import co.edu.uniquindio.proyectofinal.model.Rol;
import co.edu.uniquindio.proyectofinal.model.Vendedor;

import java.util.function.Predicate;

public class VendedorUtil {

    public static Predicate<Vendedor> buscarPorNombre(String nombre){
        return (vendedor) -> vendedor.getNombre().contains(nombre);
    }
    public static Predicate<Vendedor> buscarPorApellido(String apellido){
        return (vendedor) -> vendedor.getApellido().contains(apellido);
    }
    public static Predicate<Vendedor> buscarPorCedula(String cedula){
        return (vendedor) -> vendedor.getCedula().equals(cedula);
    }
    public static Predicate<Vendedor> buscarPorUsuario(String usuario){
        return (vendedor) -> vendedor.getUsuario().getUser().equals(usuario);
    }
    public static Predicate<Vendedor> buscarPorRol(Rol rol){
        return (vendedor) -> vendedor.getUsuario().getRol() == rol;
    }

    public static Predicate<Vendedor> buscarPorTodo(String nombre, String apellido, String cedula, String usuario, Rol rol){
        Predicate<Vendedor> predicado = vendedor -> true;
        if(nombre != null && !nombre.isEmpty()){
            predicado = predicado.and(buscarPorNombre(nombre));
        }
        if(apellido != null && !apellido.isEmpty()){
            predicado = predicado.and(buscarPorApellido(apellido));
        }
        if(cedula != null && !cedula.isEmpty()){
            predicado = predicado.and(buscarPorCedula(cedula));
        }
        if(usuario != null && !usuario.isEmpty()){
            predicado = predicado.and(buscarPorUsuario(usuario));
        }
        if(rol != null){
            predicado = predicado.and(buscarPorRol(rol));
        }
        return predicado;
    }
}
