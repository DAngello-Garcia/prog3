package co.edu.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;
    private Mensaje mensaje;

    public Chat(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }
}
