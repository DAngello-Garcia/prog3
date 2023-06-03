package parcial3.ejercicio2.cliente.model;

import parcial3.ejercicio2.servidor.model.Cliente;

import java.io.Serializable;
import java.util.ArrayList;

public class Gasolinera implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Cliente> listaClientes;

    public Gasolinera() {
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}
