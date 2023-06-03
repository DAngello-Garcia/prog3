package co.edu.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;
    private String contenidoMensaje;

    public Mensaje(String contenidoMensaje) {
        this.contenidoMensaje = contenidoMensaje;
    }

    public String getContenidoMensaje() {
        return contenidoMensaje;
    }

    public void setContenidoMensaje(String contenidoMensaje) {
        this.contenidoMensaje = contenidoMensaje;
    }
}
