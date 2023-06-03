package co.edu.uniquindio.proyectofinal.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Like implements Serializable {
    private static final long serialVersionUID = 1L;
    private Producto producto;
    private Vendedor vendedor;
    private LocalDateTime fechaLike;
    private String nombreProducto;
    private String nombreVendedor;

    public Like(Producto producto, Vendedor vendedor, LocalDateTime fechaLike) {
        this.producto = producto;
        this.vendedor = vendedor;
        this.fechaLike = fechaLike;
        this.nombreProducto = producto.getNombre();
        this.nombreVendedor = vendedor.getNombre();
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

    public LocalDateTime getFechaLike() {
        return fechaLike;
    }

    public void setFechaLike(LocalDateTime fechaLike) {
        this.fechaLike = fechaLike;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }
}
