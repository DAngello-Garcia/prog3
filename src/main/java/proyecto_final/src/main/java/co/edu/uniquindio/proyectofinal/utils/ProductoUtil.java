package co.edu.uniquindio.proyectofinal.utils;

import co.edu.uniquindio.proyectofinal.model.EstadoProducto;
import co.edu.uniquindio.proyectofinal.model.Producto;

import java.util.function.Predicate;

public class ProductoUtil {

    public static Predicate<Producto> buscarPorNombre(String nombre) {
        return (producto) -> producto.getNombre().contains(nombre);
    }

    public static Predicate<Producto> buscarPorPrecio(double precio) {
        return (producto) -> producto.getPrecio() <= precio;
    }

    public static Predicate<Producto> buscarPorId(String id) {
        return (producto) -> producto.getId().equals(id);
    }

    public static Predicate<Producto> buscarPorEstado(EstadoProducto estado) {
        return (producto) -> producto.getEstado().equals(estado);
    }

    public static Predicate<Producto> buscarPorTodo(String nombre, double precio, EstadoProducto estado) {
        Predicate<Producto> predicado = producto -> true;
        if (nombre != null && !nombre.isEmpty())
            predicado = predicado.and(buscarPorNombre(nombre));
        if (precio > 0)
            predicado = predicado.and(buscarPorPrecio(precio));
        if(estado != null)
            predicado = predicado.and(buscarPorEstado(estado));
        return predicado;
    }

}
