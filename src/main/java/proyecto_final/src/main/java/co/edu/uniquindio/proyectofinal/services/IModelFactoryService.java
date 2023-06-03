package co.edu.uniquindio.proyectofinal.services;

import co.edu.uniquindio.proyectofinal.exceptions.VendedorNoExisteException;
import co.edu.uniquindio.proyectofinal.model.EstadoProducto;
import co.edu.uniquindio.proyectofinal.model.Producto;
import co.edu.uniquindio.proyectofinal.model.Usuario;
import co.edu.uniquindio.proyectofinal.model.Vendedor;

import java.util.ArrayList;

public interface IModelFactoryService {

    public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, Usuario usuario,
                                  int index);
    public Boolean eliminarVendedor(String cedula, int index);
    public ArrayList<Vendedor> obtenerVendedores();
    boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion, Usuario usuario);
    boolean loginAdmin(String user, String pass);
    public ArrayList<Producto> obtenerProductos();
    public Producto crearProducto(String nombre, double precio, EstadoProducto estado, Vendedor vendedor);
    boolean loginVendedor(String user, String pass, int i);
    Vendedor retornarVendedor(String user) throws VendedorNoExisteException;
    public Boolean eliminarProducto(String id, int index);
    public Producto actualizarProducto(String nombre, double precio, EstadoProducto estado, String id, Vendedor vendedor);
}
