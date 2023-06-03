package parcial3.ejercicio2.servidor;

import parcial3.ejercicio2.servidor.model.Cliente;
import parcial3.ejercicio2.servidor.model.Gasolinera;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    String host = "localhost";
    ServerSocket serverComunicacion = null;
    ServerSocket serverTransferenciaObjeto = null;
    ObjectInputStream flujoEntradaComunicacion = null;
    ObjectOutputStream flujoSalidaObjeto = null;
    DataOutputStream flujoSalidaComunicacion;
    DataInputStream flujoEntrada;
    Cliente cliente;
    Gasolinera gasolinera;

    public Servidor() {
    }

    public void iniciarServidor() {

        try {
            serverComunicacion = new ServerSocket(8081);
            serverTransferenciaObjeto = new ServerSocket(8082);
            while(true) {
                Socket socketComunicacion = null;
                Socket socketTransferenciaObjetos = null;
                try {
                    System.out.println("Esperando cliente");
                    socketComunicacion = serverComunicacion.accept();
                    socketTransferenciaObjetos = serverTransferenciaObjeto.accept();
                    System.out.println("Conexión establecida");
                    flujoEntrada = new DataInputStream(socketTransferenciaObjetos.getInputStream());

                    if(flujoEntrada.readInt() == 1)  {
                        flujoEntradaComunicacion = new ObjectInputStream(socketComunicacion.getInputStream());
                        flujoSalidaComunicacion = new DataOutputStream(socketTransferenciaObjetos.getOutputStream());

                        recibirDatos();
                        flujoEntradaComunicacion.close();
                        flujoSalidaComunicacion.close();
                        flujoEntrada.close();
                    }
                    else {
                        flujoSalidaObjeto = new ObjectOutputStream(socketTransferenciaObjetos.getOutputStream());
                        enviarDatos();
                        flujoSalidaObjeto.close();
                    }

                    socketTransferenciaObjetos.close();
                    socketComunicacion.close();
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarDatos() throws IOException {
        gasolinera = (Gasolinera) Persistencia.cargarRecursoSerializadoXML();
        flujoSalidaObjeto.writeObject(gasolinera);
    }


    private void recibirDatos() throws IOException, ClassNotFoundException {
        gasolinera = new Gasolinera();
        gasolinera.getListaClientes().add(cliente);
        cliente = (Cliente) flujoEntradaComunicacion.readObject();
        Persistencia.guardarCliente(cliente);
        Persistencia.guardarXML(gasolinera);
        flujoSalidaComunicacion.writeUTF("Confirmado");

    }
}
