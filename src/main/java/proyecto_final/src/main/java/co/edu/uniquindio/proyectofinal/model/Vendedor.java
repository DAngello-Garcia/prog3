package co.edu.uniquindio.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.services.IVendedorService;

import java.io.Serializable;
import java.util.ArrayList;

public class Vendedor extends Persona implements IVendedorService, Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Producto> listaProductos;
    private ArrayList<Vendedor> listaContactosVendedor;
    private ArrayList<Vendedor> listaConexionesVendedor;

    {
        listaProductos = new ArrayList<>();
        listaContactosVendedor = new ArrayList<>();
        listaConexionesVendedor = new ArrayList<>();
    }

    public Vendedor() {

    }

    public Vendedor(String direccion) {
        this.setDireccion(direccion);
        this.setCedula("");
        this.setNombre("");
        this.setApellido("");
    }

    public Vendedor(String nombre, String apellido, String cedula, String direccion, Usuario usuario) {
        super(nombre, apellido, cedula, direccion, usuario);
    }

    @Override
    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    @Override
    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public ArrayList<Vendedor> getListaContactosVendedor() {
        return listaContactosVendedor;
    }

    @Override
    public void setListaContactosVendedor(ArrayList<Vendedor> listaContactosVendedor) {
        this.listaContactosVendedor = listaContactosVendedor;
    }

    public ArrayList<Vendedor> getListaConexionesVendedor() {
        return listaConexionesVendedor;
    }

    public void setListaConexionesVendedor(ArrayList<Vendedor> listaConexionesVendedor) {
        this.listaConexionesVendedor = listaConexionesVendedor;
    }

    public ArrayList<Producto> getProductosContactosSugeridos() {
        ArrayList<Producto> listaProductosContactosVendedor = new ArrayList<>();
        for (int i = 0; i < listaContactosVendedor.size(); i++) {
            listaProductosContactosVendedor.addAll(listaContactosVendedor.get(i).getListaProductos());
        }
        return listaProductosContactosVendedor;
    }


}
