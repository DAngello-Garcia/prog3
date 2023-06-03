package parcial3.ejercicio2.cliente;
import parcial3.ejercicio2.cliente.model.Cliente;
import parcial3.ejercicio2.cliente.model.Gasolinera;
import parcial3.ejercicio2.cliente.model.TipoVehiculo;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class AppCliente {
    Socket socketComunicacion;
    Socket socketTransferenciaObjeto;
    ObjectInputStream flujoEntradaObjeto;
    ObjectOutputStream flujoSalidaObjeto;
    DataOutputStream flujoSalidaComunicacion;
    ArrayList<parcial3.ejercicio2.servidor.model.Cliente> listaClientes;
    Gasolinera gasolinera;

    public AppCliente() {
    }

    public void iniciarCliente() {
        try {
            crearConexion();


            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Tanquear\n2. Consultar");
            if(scanner.nextInt()==1) {
                flujoSalidaComunicacion = new DataOutputStream(socketComunicacion.getOutputStream());
                flujoSalidaComunicacion.writeInt(1);
                flujoSalidaObjeto = new ObjectOutputStream(socketTransferenciaObjeto.getOutputStream());
                enviarCliente();
                flujoSalidaComunicacion.close();
                flujoSalidaObjeto.close();
            } else {
                flujoEntradaObjeto = new ObjectInputStream(socketTransferenciaObjeto.getInputStream());
                pedirClientes();
                flujoEntradaObjeto.close();
            }

            socketComunicacion.close();
            socketTransferenciaObjeto.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void pedirClientes() throws IOException, ClassNotFoundException {
        gasolinera = (Gasolinera) flujoEntradaObjeto.readObject();
        listaClientes = gasolinera.getListaClientes();
        for(parcial3.ejercicio2.servidor.model.Cliente cliente: listaClientes) {
            System.out.println("Documento: "+cliente.getDocumento()+"\nTipo de vehículo: "+cliente.getTipoVehiculo().toString()+"\n Cantidad de gasolina: "+cliente.getCantidadGasolina()+"\nFecha y hora: "+cliente.getFecha().toString()+"\n");
        }
    }

    private void enviarCliente() throws IOException, ClassNotFoundException {
        Cliente cliente = new Cliente();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese documento: ");
        cliente.setDocumento(scanner.nextLine());
        System.out.println("Ingrese cantidad de gasolina: ");
        cliente.setCantidadGasolina(scanner.nextFloat());
        //System.out.println("Ingrese el tipo de vehículo en mayúsculas (AUTOMOVIL, MOTOCICLETA, CAMION): ");
        //cliente.setTipoVehiculo(TipoVehiculo.valueOf(scanner.nextLine()));
        cliente.setTipoVehiculo(TipoVehiculo.AUTOMOVIL);
        cliente.setFecha(LocalDateTime.now());
        flujoSalidaObjeto.writeObject(cliente);
    }

    private void crearConexion() throws IOException {
        socketComunicacion = new Socket("localhost", 8081);
        socketTransferenciaObjeto = new Socket("localhost", 8082);
    }
}
