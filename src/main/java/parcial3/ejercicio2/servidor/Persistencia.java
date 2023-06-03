package parcial3.ejercicio2.servidor;


import parcial3.ejercicio2.servidor.model.Cliente;
import parcial3.ejercicio2.servidor.model.Gasolinera;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class Persistencia {
    public static final String RUTA_ARCHIVO_TANQUEO = "src/main/java/ejercicio2/archivos/tanqueos.txt";
    public static final String RUTA_MODELO_XML = "src/main/java/ejercicio2/archivos/model.xml";

    public static ArrayList<String> leerArchivo() throws IOException {

        ArrayList<String> contenido = new ArrayList<String>();
        FileReader fr = new FileReader(RUTA_ARCHIVO_TANQUEO);
        BufferedReader bfr = new BufferedReader(fr);
        String linea = "";
        while ((linea = bfr.readLine()) != null) {
            contenido.add(linea);
        }
        bfr.close();
        fr.close();
        return contenido;
    }

    public static void guardarArchivo(String contenido) throws IOException {

        FileWriter fw = new FileWriter(RUTA_ARCHIVO_TANQUEO, false);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }

    public static void guardarCliente(Cliente cliente) throws IOException {
        String contenido = cliente.getDocumento()+"@@"+cliente.getTipoVehiculo()+"@@"+cliente.getCantidadGasolina()+
                "@@"+cliente.getFecha()+"\n";
        guardarArchivo(contenido);
    }

    public static void guardarXML(Gasolinera gasolinera){
        try{
            salvarRecursoSerializadoXML(RUTA_MODELO_XML,gasolinera);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException {

        XMLEncoder codificadorXML;

        codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
        codificadorXML.writeObject(objeto);
        codificadorXML.close();

    }

    public static Object cargarRecursoSerializadoXML() throws IOException {

        XMLDecoder decodificadorXML;
        Object objetoXML;

        decodificadorXML = new XMLDecoder(new FileInputStream(RUTA_MODELO_XML));
        objetoXML = decodificadorXML.readObject();
        decodificadorXML.close();
        return objetoXML;

    }
}
