package co.edu.uniquindio.proyectofinal.services;

import co.edu.uniquindio.proyectofinal.model.Producto;
import co.edu.uniquindio.proyectofinal.model.Vendedor;

import java.util.ArrayList;

public interface IVendedorService {

    ArrayList<Producto> getListaProductos();
    void setListaProductos(ArrayList<Producto> listaProductos);
    ArrayList<Vendedor> getListaContactosVendedor();
    void setListaContactosVendedor(ArrayList<Vendedor> listaContactosVendedor);

}
