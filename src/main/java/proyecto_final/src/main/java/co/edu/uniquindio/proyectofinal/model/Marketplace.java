package co.edu.uniquindio.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.exceptions.*;
import co.edu.uniquindio.proyectofinal.utils.ProductoUtil;
import co.edu.uniquindio.proyectofinal.utils.VendedorUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Marketplace implements Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<Vendedor> listaVendedores = new ArrayList<>();
    ArrayList<Producto> listaProductos = new ArrayList<>();
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, Usuario usuario,
                                  int index) throws VendedorExisteException, VendedorLimiteException {
        if (buscarVendedorPorCedula(cedula).isPresent()) {
            throw new VendedorExisteException();
        }
        if (this.listaVendedores.size() > 10) {
            throw new VendedorLimiteException();
        }
        Vendedor vendedor = new Vendedor(nombre, apellido, cedula, direccion, usuario);
        this.listaVendedores.set(index, vendedor);
        this.listaUsuarios.set(index, usuario);
        vendedor.getListaContactosVendedor().add(vendedor);
        return vendedor;
    }

    public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion, Usuario usuario) throws ValorRequeridoException, VendedorNoExisteException {
        if (Objects.requireNonNull(cedulaActual).isEmpty()) {
            throw new ValorRequeridoException("cedula del vendedor");
        }
        Optional<Vendedor> vendedor = buscarVendedorPorCedula(cedulaActual);
        if (vendedor.isEmpty()) {
            throw new VendedorNoExisteException();
        }
        int indice = this.listaVendedores.indexOf(vendedor.get());
        Vendedor vendedorNuevo = new Vendedor(nombre, apellido, cedula, direccion, usuario);

        this.listaVendedores.set(indice, vendedorNuevo);
        return true;
    }

    public Producto actualizarProducto(String nombre, double precio, EstadoProducto estado, String id, Vendedor vendedor) throws ProductoNoExisteException {
        Optional<Producto> producto1 = buscarProductoPorId(id);
        if (producto1.isEmpty()) {
            throw new ProductoNoExisteException();
        }
        int indice = this.listaProductos.indexOf(producto1.get());
        Producto productoNuevo = new Producto(nombre, estado, precio, vendedor);
        this.listaProductos.set(indice, productoNuevo);
        return productoNuevo;
    }

    public Boolean eliminarVendedor(String cedula, int index) throws VendedorNoExisteException,
            ValorRequeridoException {
        boolean resultado = false;
        if (Objects.requireNonNull(cedula).isEmpty()) {
            throw new ValorRequeridoException("cedula del vendedor");
        }
        Optional<Vendedor> vendedor = buscarVendedorPorCedula(cedula);
        if (vendedor.isEmpty()) {
            throw new VendedorNoExisteException();
        } else {
            vendedor.get().getListaContactosVendedor().remove(vendedor.get());
            //this.listaVendedores.remove(vendedor.get());
            this.listaVendedores.set(index, new Vendedor("-1"));
            resultado = true;
        }
        return resultado;
    }

    public Boolean eliminarProducto(String id, int index) throws ProductoNoExisteException, ValorRequeridoException {
        boolean resultado = false;
        if (Objects.requireNonNull(id).isEmpty()) {
            throw new ValorRequeridoException("id del producto");
        }
        Optional<Producto> producto = buscarProductoPorId(id);
        if (producto.isEmpty()) {
            throw new ProductoNoExisteException();
        } else {
            this.listaVendedores.get(index).getListaProductos().remove(producto.get());
            this.listaProductos.remove(producto.get());
            resultado = true;
        }
        return resultado;
    }

    public Optional<Vendedor> buscarVendedorPorCedula(String cedula) {
        return this.listaVendedores.stream().filter(VendedorUtil.buscarPorCedula(cedula)).findFirst();
    }

    public Optional<Producto> buscarProductoPorId(String id) {
        return this.listaProductos.stream().filter(ProductoUtil.buscarPorId(id)).findFirst();
    }

    public Optional<Vendedor> buscarVendedorPorUsuario(String user) {
        return this.listaVendedores.stream().filter(VendedorUtil.buscarPorUsuario(user)).findFirst();
    }

    public Vendedor retornarVendedor(String user) throws VendedorNoExisteException {
        Vendedor vendedor = null;
        Optional<Vendedor> vendedor1 = buscarVendedorPorUsuario(user);
        if (vendedor1.isEmpty()) {
            throw new VendedorNoExisteException();
        }
        for (Vendedor v : getListaVendedores()) {
            if (v.getUsuario().getUser().equals(user))
                vendedor = v;
        }
        return vendedor;
    }

    public ArrayList<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public ArrayList<Vendedor> getListaContactosSugeridosVendedor1() {
        if (this.listaVendedores.size() > 0) {
            return this.listaVendedores.get(0).getListaContactosVendedor();
        }
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        return vendedores;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public int obtenerCampoDisponibleVendedor() {
        int i = 0;
        for (Vendedor v : listaVendedores) {
            if (v.getDireccion().equals("-1"))
                return i;
            i++;
        }
        return i;
    }

    public Usuario buscarUsuario(String user) throws UsuarioNoExtisteException {
        Usuario usuario = null;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getUser().equals(user))
                usuario = listaUsuarios.get(i);
        }
        if (usuario == null)
            throw new UsuarioNoExtisteException("El usuario no existe");
        return usuario;
    }

    public Producto crearProducto(String nombre, double precio, EstadoProducto estado, Vendedor vendedor) throws ProductoExisteException {
        Producto producto = new Producto(nombre, estado, precio, vendedor);
        this.listaProductos.add(producto);
        return producto;
    }

    public ArrayList<Producto> getProductosContactosSugeridosVendedor1() {
        if (listaVendedores.size() > 0) {
            return this.listaVendedores.get(0).getProductosContactosSugeridos();
        }
        ArrayList<Producto> productosVacios = new ArrayList<>();
        return productosVacios;
    }

    public ArrayList<Producto> getProductosVendedor1() {
        if (listaVendedores.size() > 0) {
            return this.listaVendedores.get(0).getListaProductos();
        }
        ArrayList<Producto> productosVacios = new ArrayList<>();
        return productosVacios;
    }

    //---------------------- xd---------------------- xd---------------------- xd
    public ArrayList<Vendedor> getListaContactosSugeridosVendedorN(int indice) {
        if (this.listaVendedores.size() > indice) {
            return this.listaVendedores.get(indice).getListaContactosVendedor();
        }
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        return vendedores;
    }

    public ArrayList<Producto> getProductosVendedorN(int indice) {
        if (listaVendedores.size() > indice) {
            return this.listaVendedores.get(indice).getListaProductos();
        }
        ArrayList<Producto> productosVacios = new ArrayList<>();
        return productosVacios;
    }

}
