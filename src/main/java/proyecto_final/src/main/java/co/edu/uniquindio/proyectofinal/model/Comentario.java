package co.edu.uniquindio.proyectofinal.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    private Producto producto;
    private Vendedor vendedor;
    private LocalDateTime fechaComentario;
    private String comentario;
    private String nombreVendedor;
    private String nombreProducto;


    public Comentario(Producto producto, Vendedor vendedor, LocalDateTime fechaComentario, String comentario) {
        this.producto = producto;
        this.vendedor = vendedor;
        this.fechaComentario = fechaComentario;
        this.comentario = comentario;
        this.nombreVendedor = vendedor.getNombre();
        this.nombreProducto = producto.getNombre();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDateTime getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDateTime fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setIdVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
