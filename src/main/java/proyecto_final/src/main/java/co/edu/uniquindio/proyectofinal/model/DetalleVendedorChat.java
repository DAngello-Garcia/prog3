package co.edu.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class DetalleVendedorChat implements Serializable {
    private static final long serialVersionUID = 1L;
    private Vendedor vendedor;
    private Chat chat;

    public DetalleVendedorChat(Vendedor vendedor, Chat chat) {
        this.vendedor = vendedor;
        this.chat = chat;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
